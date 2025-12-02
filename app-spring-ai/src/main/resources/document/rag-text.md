My application has 3 microservices: 
pricing-service (set the price type, value and description), 
member-service (set the member), 
member-request-service (set the member request message to be added in member-service)

The price type can be: 
free { "value": 0.00, "description": "Free tier with basic access." }
half-price { "value": 49.99, "description": "Half-price tier with more features." }
full-price { "value": 99.99, "description": "Full-price tier with all features." }

"baseUrl": "http://localhost:8090/api/v1"

member-request-service endpoint:
@name=submitMemberRequest
POST {{baseUrl}}/member-requests
Content-Type: application/json
Authorization: Bearer {{accessToken}}

{
  "email": "new.prospect1@example.com",
  "serviceType": "FULL_PRICE"
}

member-service endpoints:
@name=getAllMembersByManagerId
GET {{baseUrl}}/members
Accept: application/json
Authorization: Bearer {{accessToken}}

###
@name=getMemberById
GET {{baseUrl}}/members/1
Accept: application/json
Authorization: Bearer {{accessToken}}

###
@name=createMember
POST {{baseUrl}}/members
Content-Type: application/json
Authorization: Bearer {{accessToken}}

{
  "name": "New Member1",
  "email": "new.member1@serviceapp.com",
  "birthDate": "1990-01-15",
  "serviceType": "free"
}

###
@name=updateMember
PUT {{baseUrl}}/members/1
Content-Type: application/json
Authorization: Bearer {{accessToken}}

{
  "name": "Updated Member Name",
  "email": "updated.member@serviceapp.com"
}

###
@name=deleteMember
DELETE {{baseUrl}}/members/2
Authorization: Bearer {{accessToken}}

###
@name=getAllPrices
GET {{baseUrl}}/members/prices
Accept: application/json
Authorization: Bearer {{accessToken}}

###
@name=getNewMemberRequests
GET {{baseUrl}}/members/requests
Accept: application/json
Authorization: Bearer {{accessToken}}

pricing-service endpoints:
@name=getAllPrices
GET {{baseUrl}}/prices
Accept: application/json
Authorization: Bearer {{accessToken}}

###
@name=updateFreePrice
PUT {{baseUrl}}/prices/free
Content-Type: application/json
Authorization: Bearer {{accessToken}}

{
  "value": 0.00,
  "description": "Free tier with basic access."
}

###
@name=updateHalfPrice
PUT {{baseUrl}}/prices/half-price
Content-Type: application/json
Authorization: Bearer {{accessToken}}

{
  "value": 49.99,
  "description": "Half-price tier with more features."
}

###
@name=updateFullPrice
PUT {{baseUrl}}/prices/full-price
Content-Type: application/json
Authorization: Bearer {{accessToken}}

{
  "value": 99.99,
  "description": "Full-price tier with all features."
}