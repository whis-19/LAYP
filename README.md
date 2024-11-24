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
