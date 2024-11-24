# Test Cases for GoREST To-Do API

## **POST Tests (Create To-Do)**
1. **Create To-Do with valid data**: Verify successful creation with valid user ID, title, and status. (*ECP - Valid Data*)
2. **Create To-Do with missing required fields**: Ensure the API returns a 422 status when required fields (e.g., `user_id`, `title`, `status`) are missing. (*ECP - Invalid Data*)
3. **Create To-Do with duplicate title**: Validate that creating a To-Do with a duplicate title returns a 422 error. (*ECP - Negative Scenario*)
4. **Create To-Do with invalid status**: Verify that using an invalid status (e.g., "invalid") returns a 422 error. (*ECP - Invalid Value*)
5. **Create To-Do with empty title**: Ensure an empty title is rejected with a 422 status. (*BVA - Empty Value*)
6. **Create To-Do with too long title (256 characters)**: Test if a title exceeding the maximum length limit is rejected with a 422 status. (*BVA - Too Long*)
7. **Create To-Do with invalid user ID**: Confirm that using a non-existent user ID (e.g., `99999`) returns a 422 error. (*ECP - Invalid Data*)
8. **Create To-Do with empty status**: Test if an empty status value is rejected with a 422 status. (*BVA - Empty Value*)
9. **Create To-Do with invalid status (random string)**: Ensure a random string for `status` returns a 422 error. (*ECP - Invalid Value*)
10. **Create To-Do without authentication**: Validate that trying to create a To-Do without an authentication token returns a 401 error. (*ECP - Unauthorized Access*)

---

## **GET Tests (Retrieve To-Do)**
11. **Retrieve To-Do by valid ID**: Ensure the API returns correct details for an existing To-Do ID. (*ECP - Valid Data*)
12. **Retrieve To-Do by invalid ID**: Verify that a non-existent To-Do ID (e.g., `todoId` after deletion) returns a 404 status. (*ECP - Invalid Data*)

---

## **PUT Tests (Update To-Do)**
13. **Update To-Do with valid data**: Confirm successful update of To-Do with valid user ID, title, and status. (*ECP - Valid Data*)
14. **Update To-Do with missing required fields**: Validate a 422 error is returned when required fields are missing. (*ECP - Invalid Data*)
15. **Update To-Do with empty title**: Ensure an empty title is rejected with a 422 status. (*BVA - Empty Value*)
16. **Update To-Do with too long title (256 characters)**: Test if a title exceeding the maximum limit is rejected with a 422 status. (*BVA - Too Long*)
17. **Update To-Do with invalid status**: Verify a 422 error is returned for an invalid status value (e.g., "invalid"). (*ECP - Invalid Value*)
18. **Update To-Do without authentication**: Ensure updating without an authentication token returns a 401 error. (*ECP - Unauthorized Access*)
19. **Update non-existent To-Do**: Validate a 404 status is returned when attempting to update a non-existent To-Do ID. (*ECP - Invalid Data*)

---

## **DELETE Tests (Delete To-Do)**
20. **Delete To-Do by valid ID**: Confirm successful deletion of an existing To-Do. (*ECP - Valid Data*)
21. **Delete To-Do by invalid ID**: Verify a 404 status is returned for a non-existent To-Do ID. (*ECP - Invalid Data*)
22. **Delete To-Do without authentication**: Ensure deleting without an authentication token returns a 401 error. (*ECP - Unauthorized Access*)

---

## **Additional Tests for Boundary and Edge Cases**
23. **Boundary case for title length (255 characters)**: Test successful creation of a To-Do with a title exactly 255 characters long. (*BVA - Upper Bound*)
24. **Boundary case for empty body**: Ensure sending an empty JSON body in the request results in a 422 status. (*BVA - Empty Input*)
25. **Edge case for special characters in title**: Verify the API allows special characters in the title field. (*ECP - Edge Case*)
26. **Edge case for non-ASCII characters in title**: Ensure the API supports non-ASCII characters (e.g., emojis, Arabic) in the title. (*ECP - Edge Case*)
27. **Edge case for inactive user ID**: Confirm creating/updating a To-Do with an inactive user ID is rejected with a 422 status. (*ECP - Edge Case*)

---

# Performance Testing Code Descriptions

## Code 1: Fetching Users with GET Request

### Description
This script tests the performance of the **GET** `/users` endpoint, which retrieves a list of users from the GoREST API.

### Key Points
- **Options**: 
  - Virtual Users (VUs): 30 users simultaneously.
  - Duration: 20 seconds.
- **Endpoint**: `/users`.
- **Authorization**: Uses a Bearer token for authentication.
- **Validation**: 
  - Confirms that the status code is 200 (`OK`).
  - Ensures the response time is less than 500ms.

### Use Case
This test measures the API's ability to handle concurrent user requests efficiently and ensures fast response times.

---

## Code 2: Deleting a Specific User with DELETE Request

### Description
This script tests the **DELETE** `/users/{id}` endpoint by deleting a specific user with their unique ID.

### Key Points
- **Options**: 
  - Virtual Users (VUs): 30 users simultaneously.
  - Duration: 20 seconds.
- **Endpoint**: `/users/{id}` (where `{id}` is the unique user ID).
- **Authorization**: Requires a Bearer token.
- **Validation**: 
  - Confirms the response status is in the 2xx range (indicating a successful deletion).

### Use Case
This test evaluates the API's capability to handle multiple delete requests concurrently and verify that it processes deletions correctly.

---

## Code 3: Creating a User with POST Request

### Description
This script tests the **POST** `/users` endpoint to create a new user with randomized details.

### Key Points
- **Options**: 
  - Virtual Users (VUs): 20 users.
  - Duration: 15 seconds.
- **Endpoint**: `/users`.
- **Payload**: 
  - A JSON body with random values for `name`, `email`, `gender`, and `status`.
- **Authorization**: Bearer token and `Content-Type: application/json` header.
- **Validation**: 
  - Confirms that the response status is 201 (`Created`).
  - Ensures the response body includes `name` and `email` attributes.

### Use Case
This test evaluates the API's ability to handle user creation under load while ensuring valid responses and expected behavior.

---

## Code 4: Fetching Posts with GET Request

### Description
This script tests the **GET** `/posts` endpoint, which retrieves a list of posts from the GoREST API.

### Key Points
- **Options**: 
  - Virtual Users (VUs): 25 users.
  - Duration: 15 seconds.
- **Endpoint**: `/posts`.
- **Authorization**: Requires a Bearer token.
- **Validation**: 
  - Confirms the response status is 200 (`OK`).
  - Verifies the response time is less than 400ms.
  - Checks that the response body is not empty.

### Use Case
This test ensures that the API can return a valid list of posts under concurrent user requests while maintaining optimal response times.




