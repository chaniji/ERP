#!/bin/bash

# Configuration
BASE_URL="http://localhost:8083/api/inventory"
CONTENT_TYPE="Content-Type: application/json"

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}===========================================${NC}"
echo -e "${BLUE}   Inventory Service API Test Suite        ${NC}"
echo -e "${BLUE}===========================================${NC}"

# Function to print section headers
print_header() {
    echo -e "\n${YELLOW}>>> $1${NC}"
}

# 1. Create a new Inventory item (POST)
print_header "Testing POST /api/inventory (Create Item)"
CREATE_RESPONSE=$(curl -s -X POST "$BASE_URL" \
    -H "$CONTENT_TYPE" \
    -d '{
        "productId": "PROD-999",
        "productName": "Test Product",
        "quantity": 50,
        "price": 199.99,
        "location": "Warehouse-A"
    }')

echo "$CREATE_RESPONSE"

# Extract ID using python or grep/sed (fallback if jq is not installed)
if command -v jq >/dev/null 2>&1; then
    ITEM_ID=$(echo "$CREATE_RESPONSE" | jq -r '.id')
    PRODUCT_ID=$(echo "$CREATE_RESPONSE" | jq -r '.productId')
else
    ITEM_ID=$(echo "$CREATE_RESPONSE" | grep -oP '"id":"\K[^"]+')
    PRODUCT_ID=$(echo "$CREATE_RESPONSE" | grep -oP '"productId":"\K[^"]+')
fi

if [ -z "$ITEM_ID" ] || [ "$ITEM_ID" == "null" ]; then
    echo -e "${RED}Failed to create item or extract ID${NC}"
    # exit 1
else
    echo -e "${GREEN}Created item with ID: $ITEM_ID${NC}"
fi

# 2. Get all inventory (GET)
print_header "Testing GET /api/inventory (Get All)"
curl -s -X GET "$BASE_URL" -H "$CONTENT_TYPE" | head -c 200
echo -e "\n... (truncated)"

# 3. Get inventory by ID (GET)
print_header "Testing GET /api/inventory/$ITEM_ID (Get by ID)"
curl -s -X GET "$BASE_URL/$ITEM_ID" -H "$CONTENT_TYPE"

# 4. Get inventory by Product ID (GET)
print_header "Testing GET /api/inventory/product/$PRODUCT_ID (Get by Product ID)"
curl -s -X GET "$BASE_URL/product/$PRODUCT_ID" -H "$CONTENT_TYPE"

# 5. Update inventory (PUT)
print_header "Testing PUT /api/inventory/$ITEM_ID (Update Item)"
curl -s -X PUT "$BASE_URL/$ITEM_ID" \
    -H "$CONTENT_TYPE" \
    -d '{
        "productId": "PROD-999",
        "productName": "Updated Product",
        "quantity": 75,
        "price": 249.99,
        "location": "Warehouse-B"
    }'

# 6. Update stock (PATCH)
print_header "Testing PATCH /api/inventory/$ITEM_ID/stock (Update Stock)"
curl -s -X PATCH "$BASE_URL/$ITEM_ID/stock?quantity=100" -H "$CONTENT_TYPE"

# 7. Get low stock items (GET)
print_header "Testing GET /api/inventory/low-stock"
curl -s -X GET "$BASE_URL/low-stock" -H "$CONTENT_TYPE"

# 8. Delete inventory (DELETE)
print_header "Testing DELETE /api/inventory/$ITEM_ID"
curl -s -X DELETE "$BASE_URL/$ITEM_ID" -H "$CONTENT_TYPE"

# 9. Verify deletion (GET)
print_header "Testing GET /api/inventory/$ITEM_ID (Verify Deletion)"
curl -s -I -X GET "$BASE_URL/$ITEM_ID" -H "$CONTENT_TYPE" | head -n 1

echo -e "\n${BLUE}===========================================${NC}"
echo -e "${BLUE}          Test Suite Completed             ${NC}"
echo -e "${BLUE}===========================================${NC}"
