#!/bin/bash

################################################################################
# Inventory Service - API Test Suite
# Organized test script for CRUD operations and business logic endpoints
################################################################################

# ============================================================================
# CONFIGURATION
# ============================================================================
BASE_URL="http://localhost:8083/api/inventory"
OUTPUT_FILE="api_test_results.md"

# Colors for terminal output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# ============================================================================
# HELPER FUNCTIONS
# ============================================================================

# Print section header
print_header() {
  echo -e "\n${BLUE}╔════════════════════════════════════════════════════╗${NC}"
  echo -e "${BLUE}║${NC} $1"
  echo -e "${BLUE}╚════════════════════════════════════════════════════╝${NC}"
}

# Execute GET request and log
test_get() {
  local name="$1"
  local endpoint="$2"
  echo "📌 $name" | tee -a "$OUTPUT_FILE"
  echo "GET $endpoint" >> "$OUTPUT_FILE"
  curl -s "$endpoint" | tee -a "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE"
  echo "---" >> "$OUTPUT_FILE"
}

# Execute POST request, log, and extract ID
test_post() {
  local name="$1"
  local endpoint="$2"
  local data="$3"
  echo "📌 $name" | tee -a "$OUTPUT_FILE"
  echo "POST $endpoint" >> "$OUTPUT_FILE"
  local response=$(curl -s -X POST "$endpoint" \
    -H "Content-Type: application/json" \
    -d "$data")
  echo "$response" | tee -a "$OUTPUT_FILE"
  local id=$(echo "$response" | grep -o '"id":"[^"]*' | cut -d'"' -f4)
  echo "Extracted ID: $id" >> "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE"
  echo "---" >> "$OUTPUT_FILE"
  echo "$id"
}

# Execute PUT request and log
test_put() {
  local name="$1"
  local endpoint="$2"
  local data="$3"
  echo "📌 $name" | tee -a "$OUTPUT_FILE"
  echo "PUT $endpoint" >> "$OUTPUT_FILE"
  curl -s -X PUT "$endpoint" \
    -H "Content-Type: application/json" \
    -d "$data" | tee -a "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE"
  echo "---" >> "$OUTPUT_FILE"
}

# Execute PATCH request and log
test_patch() {
  local name="$1"
  local endpoint="$2"
  echo "📌 $name" | tee -a "$OUTPUT_FILE"
  echo "PATCH $endpoint" >> "$OUTPUT_FILE"
  curl -s -X PATCH "$endpoint" | tee -a "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE"
  echo "---" >> "$OUTPUT_FILE"
}

# Execute DELETE request and log
test_delete() {
  local name="$1"
  local endpoint="$2"
  echo "📌 $name" | tee -a "$OUTPUT_FILE"
  echo "DELETE $endpoint" >> "$OUTPUT_FILE"
  curl -s -X DELETE "$endpoint" | tee -a "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE"
  echo "---" >> "$OUTPUT_FILE"
}

# ============================================================================
# INITIALIZATION
# ============================================================================

echo -e "${YELLOW}🚀 Starting Inventory Service API Tests${NC}"
echo "=== Inventory Service API Test ===" | tee "$OUTPUT_FILE"
echo "Date: $(date)" >> "$OUTPUT_FILE"
echo "Base URL: $BASE_URL" >> "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"

# ============================================================================
# TEST SUITE: READ OPERATIONS
# ============================================================================

print_header "READ OPERATIONS"

test_get "Test 1: GET All Inventories" "$BASE_URL"

# ============================================================================
# TEST SUITE: CREATE OPERATIONS
# ============================================================================

print_header "CREATE OPERATIONS"

PRODUCT_ID=$(test_post "Test 2: Create First Product (PROD-001)" "$BASE_URL" '{
  "productId": "PROD-001",
  "productName": "Widget Alpha",
  "quantity": 50,
  "price": 25.99,
  "location": "Warehouse A"
}')

PRODUCT_ID2=$(test_post "Test 3: Create Second Product with Low Stock (PROD-005)" "$BASE_URL" '{
  "productId": "PROD-005",
  "productName": "Low Stock Item",
  "quantity": 5,
  "price": 10.00,
  "location": "Shelf C"
}')

# ============================================================================
# TEST SUITE: READ BY ID OPERATIONS
# ============================================================================

print_header "READ BY ID OPERATIONS"

test_get "Test 4: GET Product by ID (ID: $PRODUCT_ID)" "$BASE_URL/$PRODUCT_ID"

test_get "Test 5: GET Product by Product ID (PROD-001)" "$BASE_URL/product/PROD-001"

# ============================================================================
# TEST SUITE: UPDATE OPERATIONS
# ============================================================================

print_header "UPDATE OPERATIONS"

test_patch "Test 6: PATCH - Update Stock (ID: $PRODUCT_ID, Qty: 100)" \
  "$BASE_URL/$PRODUCT_ID/stock?quantity=100"

test_put "Test 7: PUT - Update Full Record (ID: $PRODUCT_ID)" "$BASE_URL/$PRODUCT_ID" '{
  "productId": "PROD-001",
  "productName": "Widget Alpha Updated",
  "quantity": 75,
  "price": 29.99,
  "location": "Warehouse B"
}'

# ============================================================================
# TEST SUITE: BUSINESS LOGIC - LOW STOCK
# ============================================================================

print_header "BUSINESS LOGIC: LOW STOCK OPERATIONS"

test_get "Test 8: GET Low Stock Items (Before)" "$BASE_URL/low-stock"

test_get "Test 9: GET Low Stock Items (After Adding PROD-005)" "$BASE_URL/low-stock"

# ============================================================================
# TEST SUITE: VERIFICATION & FINAL STATE
# ============================================================================

print_header "VERIFICATION & FINAL STATE"

test_get "Test 10: GET All Inventories (After All Updates)" "$BASE_URL"

# ============================================================================
# TEST SUITE: DELETE OPERATIONS
# ============================================================================

print_header "DELETE OPERATIONS"

test_delete "Test 11: DELETE Product (ID: $PRODUCT_ID)" "$BASE_URL/$PRODUCT_ID"

test_get "Test 12: GET All Inventories (After Delete)" "$BASE_URL"

# ============================================================================
# COMPLETION
# ============================================================================

echo -e "\n${GREEN}✅ All tests completed!${NC}"
echo -e "${GREEN}📄 Results saved to: $OUTPUT_FILE${NC}" | tee -a "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"
echo "Test Summary:" >> "$OUTPUT_FILE"
echo "- Read Operations: 4 tests" >> "$OUTPUT_FILE"
echo "- Create Operations: 2 tests" >> "$OUTPUT_FILE"
echo "- Update Operations: 2 tests" >> "$OUTPUT_FILE"
echo "- Business Logic Tests: 2 tests" >> "$OUTPUT_FILE"
echo "- Delete Operations: 1 test" >> "$OUTPUT_FILE"
echo "- Verification: 1 test" >> "$OUTPUT_FILE"
echo "Total: 12 tests" >> "$OUTPUT_FILE"
