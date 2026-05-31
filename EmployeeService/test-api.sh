#!/bin/bash

# Configuration
BASE_URL="http://localhost:8082/api"
CONTENT_TYPE="Content-Type: application/json"

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}===========================================${NC}"
echo -e "${BLUE}    Employee Service API Test Suite        ${NC}"
echo -e "${BLUE}===========================================${NC}"

# Function to print section headers
print_header() {
    echo -e "\n${YELLOW}>>> $1${NC}"
}

# --- DEPARTMENT TESTS ---

UNIQUE_SUFFIX=$(date +%s)
DEPT_NAME="Dept-$UNIQUE_SUFFIX"

print_header "Testing POST /api/departments (Create Department)"
DEPT_RESPONSE=$(curl -s -X POST "$BASE_URL/departments" \
    -H "$CONTENT_TYPE" \
    -d "{
        \"name\": \"$DEPT_NAME\",
        \"description\": \"Test Department Description\"
    }")
echo "$DEPT_RESPONSE"

if command -v jq >/dev/null 2>&1; then
    DEPT_ID=$(echo "$DEPT_RESPONSE" | jq -r '.id')
else
    DEPT_ID=$(echo "$DEPT_RESPONSE" | grep -oP '"id":\s*\K\d+')
fi

# Fallback if creation failed (e.g., uniqueness constraint)
if [ -z "$DEPT_ID" ] || [ "$DEPT_ID" == "null" ]; then
    echo -e "${RED}Failed to create department. Attempting to fetch an existing one...${NC}"
    EXISTING_DEPTS=$(curl -s -X GET "$BASE_URL/departments" -H "$CONTENT_TYPE")
    if command -v jq >/dev/null 2>&1; then
        DEPT_ID=$(echo "$EXISTING_DEPTS" | jq -r '.[0].id')
    else
        DEPT_ID=$(echo "$EXISTING_DEPTS" | grep -oP '"id":\s*\K\d+' | head -n 1)
    fi
fi

if [ -z "$DEPT_ID" ] || [ "$DEPT_ID" == "null" ]; then
    echo -e "${RED}Could not find any department ID. Tests will fail.${NC}"
else
    echo -e "${GREEN}Using Department ID: $DEPT_ID${NC}"
fi

print_header "Testing GET /api/departments (Get All)"
curl -s -X GET "$BASE_URL/departments" -H "$CONTENT_TYPE"

print_header "Testing GET /api/departments/$DEPT_ID (Get by ID)"
curl -s -X GET "$BASE_URL/departments/$DEPT_ID" -H "$CONTENT_TYPE"

# --- EMPLOYEE TESTS ---

print_header "Testing POST /api/employees (Create Employee)"
# Note: Using escaped double quotes inside the -d string
EMP_RESPONSE=$(curl -s -X POST "$BASE_URL/employees" \
    -H "$CONTENT_TYPE" \
    -d "{
        \"firstName\": \"John\",
        \"lastName\": \"Doe\",
        \"email\": \"john.doe.$UNIQUE_SUFFIX@example.com\",
        \"salary\": 75000.0,
        \"joinDate\": \"$(date +%Y-%m-%d)\",
        \"departmentId\": $DEPT_ID
    }")
echo "$EMP_RESPONSE"

if command -v jq >/dev/null 2>&1; then
    EMP_ID=$(echo "$EMP_RESPONSE" | jq -r '.id')
else
    EMP_ID=$(echo "$EMP_RESPONSE" | grep -oP '"id":\s*\K\d+')
fi

if [ -z "$EMP_ID" ] || [ "$EMP_ID" == "null" ]; then
    echo -e "${RED}Failed to create employee or extract ID${NC}"
else
    echo -e "${GREEN}Created employee with ID: $EMP_ID${NC}"
fi

print_header "Testing GET /api/employees (Get All - Paged)"
curl -s -X GET "$BASE_URL/employees?page=0&size=5" -H "$CONTENT_TYPE"

print_header "Testing GET /api/employees/$EMP_ID (Get by ID)"
if [ ! -z "$EMP_ID" ] && [ "$EMP_ID" != "null" ]; then
    curl -s -X GET "$BASE_URL/employees/$EMP_ID" -H "$CONTENT_TYPE"
else
    echo "Skipping... no EMP_ID"
fi

print_header "Testing GET /api/employees/departments/$DEPT_ID (Get by Dept ID)"
curl -s -X GET "$BASE_URL/employees/departments/$DEPT_ID" -H "$CONTENT_TYPE"

print_header "Testing PUT /api/employees/$EMP_ID (Update Employee)"
if [ ! -z "$EMP_ID" ] && [ "$EMP_ID" != "null" ]; then
    curl -s -X PUT "$BASE_URL/employees/$EMP_ID" \
        -H "$CONTENT_TYPE" \
        -d "{
            \"firstName\": \"John\",
            \"lastName\": \"Updated\",
            \"email\": \"john.updated.$UNIQUE_SUFFIX@example.com\",
            \"salary\": 85000.0,
            \"joinDate\": \"$(date +%Y-%m-%d)\",
            \"departmentId\": $DEPT_ID
        }"
else
    echo "Skipping... no EMP_ID"
fi

# --- CLEANUP ---

if [ ! -z "$EMP_ID" ] && [ "$EMP_ID" != "null" ]; then
    print_header "Testing DELETE /api/employees/$EMP_ID"
    curl -s -X DELETE "$BASE_URL/employees/$EMP_ID" -H "$CONTENT_TYPE"
fi

# Only delete department if we created it (indicated by name match if we really wanted to be sure, 
# but for now we'll just skip delete if it's likely a shared one)
# For this script, we'll try to delete it.
if [ ! -z "$DEPT_ID" ] && [ "$DEPT_ID" != "null" ]; then
    print_header "Testing DELETE /api/departments/$DEPT_ID"
    curl -s -X DELETE "$BASE_URL/departments/$DEPT_ID" -H "$CONTENT_TYPE"
fi

echo -e "\n${BLUE}===========================================${NC}"
echo -e "${BLUE}          Test Suite Completed             ${NC}"
echo -e "${BLUE}===========================================${NC}"
