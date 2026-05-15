=== Inventory Service API Test ===
Date: Fri May 15 03:40:13 PM IST 2026
Base URL: http://localhost:8083/api/inventory

📌 Test 1: GET All Inventories
GET http://localhost:8083/api/inventory
[{"id":"6a06ef05ab02e271a7df351c","productId":"PROD-001","productName":"Widget A","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:31:41.033","updateAt":"2026-05-15T15:31:41.033","instock":true},{"id":"6a06f0018de610f75803a917","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:35:53.046","updateAt":"2026-05-15T15:35:53.046","instock":true},{"id":"6a06f03d8de610f75803a919","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:36:53.262","updateAt":"2026-05-15T15:36:53.262","instock":true}]
---
📌 Test 2: Create First Product (PROD-001)
POST http://localhost:8083/api/inventory
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
Extracted ID: 6a06f1058de610f75803a91a

---
📌 Test 3: Create Second Product with Low Stock (PROD-005)
POST http://localhost:8083/api/inventory
{"id":"6a06f1058de610f75803a91b","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:40:13.668509478","updateAt":"2026-05-15T15:40:13.668509478","instock":true}
Extracted ID: 6a06f1058de610f75803a91b

---
📌 Test 4: GET Product by ID (ID: 📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a)
GET http://localhost:8083/api/inventory/📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a

---
📌 Test 5: GET Product by Product ID (PROD-001)
GET http://localhost:8083/api/inventory/product/PROD-001
{"statuscode":404,"message":"INTERNAL SERVER ERROR","currentTime":"2026-05-15T15:40:13.699951071"}
---
📌 Test 6: PATCH - Update Stock (ID: 📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a, Qty: 100)
PATCH http://localhost:8083/api/inventory/📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a/stock?quantity=100

---
📌 Test 7: PUT - Update Full Record (ID: 📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a)
PUT http://localhost:8083/api/inventory/📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a

---
📌 Test 8: GET Low Stock Items (Before)
GET http://localhost:8083/api/inventory/low-stock
[{"id":"6a06f0018de610f75803a917","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:35:53.046","updateAt":"2026-05-15T15:35:53.046","instock":true},{"id":"6a06f03d8de610f75803a919","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:36:53.262","updateAt":"2026-05-15T15:36:53.262","instock":true},{"id":"6a06f1058de610f75803a91b","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:40:13.668","updateAt":"2026-05-15T15:40:13.668","instock":true}]
---
📌 Test 9: GET Low Stock Items (After Adding PROD-005)
GET http://localhost:8083/api/inventory/low-stock
[{"id":"6a06f0018de610f75803a917","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:35:53.046","updateAt":"2026-05-15T15:35:53.046","instock":true},{"id":"6a06f03d8de610f75803a919","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:36:53.262","updateAt":"2026-05-15T15:36:53.262","instock":true},{"id":"6a06f1058de610f75803a91b","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:40:13.668","updateAt":"2026-05-15T15:40:13.668","instock":true}]
---
📌 Test 10: GET All Inventories (After All Updates)
GET http://localhost:8083/api/inventory
[{"id":"6a06ef05ab02e271a7df351c","productId":"PROD-001","productName":"Widget A","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:31:41.033","updateAt":"2026-05-15T15:31:41.033","instock":true},{"id":"6a06f0018de610f75803a917","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:35:53.046","updateAt":"2026-05-15T15:35:53.046","instock":true},{"id":"6a06f03d8de610f75803a919","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:36:53.262","updateAt":"2026-05-15T15:36:53.262","instock":true},{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648","updateAt":"2026-05-15T15:40:13.648","instock":true},{"id":"6a06f1058de610f75803a91b","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:40:13.668","updateAt":"2026-05-15T15:40:13.668","instock":true}]
---
📌 Test 11: DELETE Product (ID: 📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a)
DELETE http://localhost:8083/api/inventory/📌 Test 2: Create First Product (PROD-001)
{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648902579","updateAt":"2026-05-15T15:40:13.648902579","instock":true}
6a06f1058de610f75803a91a

---
📌 Test 12: GET All Inventories (After Delete)
GET http://localhost:8083/api/inventory
[{"id":"6a06ef05ab02e271a7df351c","productId":"PROD-001","productName":"Widget A","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:31:41.033","updateAt":"2026-05-15T15:31:41.033","instock":true},{"id":"6a06f0018de610f75803a917","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:35:53.046","updateAt":"2026-05-15T15:35:53.046","instock":true},{"id":"6a06f03d8de610f75803a919","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:36:53.262","updateAt":"2026-05-15T15:36:53.262","instock":true},{"id":"6a06f1058de610f75803a91a","productId":"PROD-001","productName":"Widget Alpha","quantity":50,"price":25.99,"location":"Warehouse A","createdAt":"2026-05-15T15:40:13.648","updateAt":"2026-05-15T15:40:13.648","instock":true},{"id":"6a06f1058de610f75803a91b","productId":"PROD-005","productName":"Low Stock Item","quantity":5,"price":10.0,"location":"Shelf C","createdAt":"2026-05-15T15:40:13.668","updateAt":"2026-05-15T15:40:13.668","instock":true}]
---
[0;32m📄 Results saved to: api_test_results.md[0m

Test Summary:
- Read Operations: 4 tests
- Create Operations: 2 tests
- Update Operations: 2 tests
- Business Logic Tests: 2 tests
- Delete Operations: 1 test
- Verification: 1 test
Total: 12 tests
