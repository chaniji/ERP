#!/bin/bash

# Base URLs
GATEWAY_URL="http://localhost:9191"
AUTH_URL="http://localhost:8081/api/auth"
EMPLOYEE_URL="http://localhost:8082/api/employees"
INVENTORY_URL="http://localhost:8083/api/inventory"
PAYROLL_URL="http://localhost:8084/api/payroll"
REPORT_URL="http://localhost:8085/graphql"

echo "------------------------------------------------"
echo "ERP System API Testing Script"
echo "------------------------------------------------"

# 1. Auth Service - Register & Login
echo -e "\n[1] Testing Auth Service (Direct)..."
REGISTER_RES=$(curl -s -X POST "$AUTH_URL/register" \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser", "password":"password123", "role":"ADMIN"}')
echo "Register Response: $REGISTER_RES"

LOGIN_RES=$(curl -s -X POST "$AUTH_URL/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser", "password":"password123"}')
echo "Login Response: $LOGIN_RES"

TOKEN=$(echo $LOGIN_RES | grep -oP '(?<="token":")[^"]*')

if [ -z "$TOKEN" ]; then
    echo "❌ Failed to get JWT token. Ensure Auth Service is running."
else
    echo "✅ JWT Token obtained: ${TOKEN:0:20}..."
fi

# 2. Employee Service
echo -e "\n[2] Testing Employee Service (Direct)..."
curl -s -X GET "$EMPLOYEE_URL" -H "Authorization: Bearer $TOKEN" | head -n 5

# 3. Inventory Service
echo -e "\n[3] Testing Inventory Service (Direct)..."
curl -s -X GET "$INVENTORY_URL" -H "Authorization: Bearer $TOKEN" | head -n 5

# 4. Payroll Service
echo -e "\n[4] Testing Payroll Service (Direct)..."
curl -s -X GET "$PAYROLL_URL" -H "Authorization: Bearer $TOKEN" | head -n 5

# 5. Report Service (GraphQL)
echo -e "\n[5] Testing Report Service (GraphQL)..."
curl -s -X POST "$REPORT_URL" \
  -H "Content-Type: application/json" \
  -d '{"query": "{ allReports { id title createdAt } }"}'

echo -e "\n\n[6] Testing Gateway (Port 9191)..."
echo "Testing Auth via Gateway: $GATEWAY_URL/auth/login"
curl -s -o /dev/null -w "Gateway Auth Status: %{http_code}\n" -X POST "$GATEWAY_URL/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser", "password":"password123"}'

echo "------------------------------------------------"
echo "Testing Complete."
