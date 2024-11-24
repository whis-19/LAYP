import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  vus: 20, // Number of Virtual Users
  duration: "15s", // Test duration
};

const baseUrl = "https://gorest.co.in/public/v2";
const authToken =
  "e9aba9a490938e1c8939ce391aeef4b73d45a75217c313ccd6a3be20fdce0458";

export default function () {
  const url = `${baseUrl}/users`;

  const payload = JSON.stringify({
    name: `Test User ${Math.random().toString(36).substring(7)}`, // Random name
    gender: "female",
    email: `test${Math.random().toString(36).substring(7)}@example.com`, // Random email
    status: "active",
  });

  const headers = {
    Authorization: `Bearer ${authToken}`,
    "Content-Type": "application/json",
  };

  const response = http.post(url, payload, { headers });

  check(response, {
    "POST /users - status is 201": (r) => r.status === 201,
    "POST /users - response contains name": (r) =>
      JSON.parse(r.body).name !== undefined,
    "POST /users - response contains email": (r) =>
      JSON.parse(r.body).email !== undefined,
  });

  sleep(1); // Simulate user pause between requests
}
