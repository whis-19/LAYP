const frisby = require('frisby');

// Base URL and authentication token
const baseUrl = 'https://gorest.co.in/public/v2';
const authToken = 'e9aba9a490938e1c8939ce391aeef4b73d45a75217c313ccd6a3be20fdce0458'; // Replace with your actual token

let userId = null;

describe('GoREST API CRUD Operations for Users', () => {

  // POST /users - Create a valid user
  it('POST /users - Create a valid user', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Test User',
        gender: 'male',
        email: `testuser${Date.now()}@example.com`,
        status: 'active',
      },
    })
      .expect('status', 201)
      .then((response) => {
        const json = response.json;
        userId = json.id; 
        expect(userId).toBeDefined();
      });
  });

  // GET /users/{id} - Retrieve user details
  it('GET /users/{id} - Retrieve user details', () => {
    return frisby.get(`${baseUrl}/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    })
      .expect('status', 200)
      .expect('json', 'id', userId)
      .expect('json', 'name', 'Test User');
  });

  // PUT /users/{id} - Update user details
  it('PUT /users/{id} - Update user details', () => {
    return frisby.put(`${baseUrl}/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Updated Test User',
        gender: 'male',
        email: `updateduser${Date.now()}@example.com`,
        status: 'active',
      },
    })
      .expect('status', 200)
      .expect('json', 'name', 'Updated Test User');
  });

  // GET /users/{id} - Retrieve updated user details
  it('GET /users/{id} - Retrieve updated user details', () => {
    return frisby.get(`${baseUrl}/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    })
      .expect('status', 200)
      .expect('json', 'name', 'Updated Test User');
  });

  // DELETE /users/{id} - Delete user
  it('DELETE /users/{id} - Delete user', () => {
    return frisby.del(`${baseUrl}/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    })
      .expect('status', 204);
  });

  // GET /users/{id} - Verify user deletion
  it('GET /users/{id} - Verify user deletion (should not exist)', () => {
    return frisby.get(`${baseUrl}/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    })
      .expect('status', 404); 
  });

  // POST /users - Create user with missing required fields
  it('POST /users - Create user with missing required fields', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {},
    })
      .expect('status', 422);
  });

  // PUT /users/{id} - Update user with missing fields
  it('PUT /users/{id} - Update user with missing fields', () => {
    return frisby.put(`${baseUrl}/users/${userId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: '', 
        gender: 'male',
        email: '', 
        status: 'active',
      },
    })
      .expect('status', 404); 
  });

  // POST /users - Create user with duplicate email
  it('POST /users - Create user with duplicate email', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Duplicate Email User',
        gender: 'male',
        email: `duplicateemail@example.com`, 
        status: 'active',
      },
    })
      .expect('status', 422);  
  });

  // POST /users - Create user with invalid email format
  it('POST /users - Create user with invalid email format', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Invalid Email User',
        gender: 'male',
        email: 'invalid-email-format', 
        status: 'active',
      },
    })
      .expect('status', 422); 
  });

  // POST /users - Create user without authentication
  it('POST /users - Create user without authentication', () => {
    return frisby.post(`${baseUrl}/users`, {
      body: {
        name: 'Unauthorized User',
        gender: 'male',
        email: `unauthorizeduser${Date.now()}@example.com`,
        status: 'active',
      },
    })
      .expect('status', 401); 
  });



  // Node Coverage: Create User with Inactive Status
  it('POST /users - Create user with inactive status', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Inactive User',
        gender: 'female',
        email: `inactiveuser${Date.now()}@example.com`,
        status: 'inactive',
      },
    })
      .expect('status', 201); 
  });




  // Boundary Case: Create User with Too Long Name (256 characters)
  it('POST /users - Create user with too long name (256 characters)', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'a'.repeat(256), 
        gender: 'male',
        email: `longboundaryuser${Date.now()}@example.com`,
        status: 'active',
      },
    })
      .expect('status', 422); 
  });

  // Boundary Case: Create User with Minimum Length Email (5 characters)
  it('POST /users - Create user with minimum length email', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Min Email User',
        gender: 'female',
        email: 'a@b.c', 
        status: 'active',
      },
    })
      .expect('status', 422); 
  });

  // Boundary Case: Create User with Maximum Length Email (320 characters)
  it('POST /users - Create user with maximum length email (320 characters)', () => {
    return frisby.post(`${baseUrl}/users`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: {
        name: 'Max Email User',
        gender: 'male',
        email: 'a'.repeat(318) + '@example.com', 
        status: 'active',
      },
    })
      .expect('status', 422); 
  });
});
