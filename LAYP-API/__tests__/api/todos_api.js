const frisby = require("frisby");

// Base URL and authentication token
const baseUrl = "https://gorest.co.in/public/v2";
const authToken =
  "e9aba9a490938e1c8939ce391aeef4b73d45a75217c313ccd6a3be20fdce0458"; // Replace with your actual token

let todoId = null;
let userId = null;

describe("GoREST API CRUD Operations for To-Dos", () => {
  // DELETE /todos/{id} - Delete To-Do
  it("DELETE /todos/{id} - Delete To-Do", () => {
    return frisby
      .del(`${baseUrl}/todos/${todoId}`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
      })
      .expect("status", 404);
  });

  // GET /todos/{id} - Verify To-Do deletion
  it("GET /todos/{id} - Verify To-Do deletion (should not exist)", () => {
    return frisby
      .get(`${baseUrl}/todos/${todoId}`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
      })
      .expect("status", 404);
  });

  // POST /todos - Create To-Do with missing required fields
  it("POST /todos - Create To-Do with missing required fields", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {},
      })
      .expect("status", 422);
  });

  // PUT /todos/{id} - Update To-Do with missing required fields
  it("PUT /todos/{id} - Update To-Do with missing required fields", () => {
    return frisby
      .put(`${baseUrl}/todos/${todoId}`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          title: "",
          status: "",
        },
      })
      .expect("status", 404);
  });

  // POST /todos - Create To-Do with duplicate title (if relevant for your system)
  it("POST /todos - Create To-Do with duplicate title", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: userId,
          title: "Test To-Do",
          status: "incomplete",
        },
      })
      .expect("status", 422);
  });

  // POST /todos - Create To-Do with invalid status
  it("POST /todos - Create To-Do with invalid status", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: userId,
          title: "Invalid Status To-Do",
          status: "invalid",
        },
      })
      .expect("status", 422);
  });

  // POST /todos - Create To-Do without authentication
  it("POST /todos - Create To-Do without authentication", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        body: {
          user_id: userId,
          title: `Unauthorized To-Do ${Date.now()}`,
          status: "incomplete",
        },
      })
      .expect("status", 401);
  });

  // Edge Case: Create To-Do with Empty Title
  it("POST /todos - Create To-Do with empty title", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: userId,
          title: "",
          status: "incomplete",
        },
      })
      .expect("status", 422);
  });

  // Node Coverage: Create To-Do with Invalid User ID
  it("POST /todos - Create To-Do with invalid user ID", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: 99999, // Invalid user ID
          title: "Invalid User To-Do",
          status: "incomplete",
        },
      })
      .expect("status", 422);
  });

  // Boundary Case: Create To-Do with Too Long Title (256 characters)
  it("POST /todos - Create To-Do with too long title (256 characters)", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: userId,
          title: "a".repeat(256),
          status: "incomplete",
        },
      })
      .expect("status", 422);
  });

  // Boundary Case: Create To-Do with Invalid Status (empty)
  it("POST /todos - Create To-Do with empty status", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: userId,
          title: `Test To-Do ${Date.now()}`,
          status: "",
        },
      })
      .expect("status", 422);
  });

  // Boundary Case: Create To-Do with Invalid Status (random string)
  it("POST /todos - Create To-Do with invalid status (random string)", () => {
    return frisby
      .post(`${baseUrl}/todos`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: {
          user_id: userId,
          title: `Test To-Do ${Date.now()}`,
          status: "random",
        },
      })
      .expect("status", 422);
  });
});
