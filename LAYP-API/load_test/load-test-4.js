import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  vus: 25, // Number of Virtual Users
  duration: "15s", // Test duration
};

const baseUrl = "https://gorest.co.in/public/v2";
const authToken =
  "e9aba9a490938e1c8939ce391aeef4b73d45a75217c313ccd6a3be20fdce0458";

export default function () {
  const url = `${baseUrl}/posts`;

  const response = http.get(url, {
    headers: {
      Authorization: `Bearer ${authToken}`,
    },
  });

  check(response, {
    "GET /posts - status is 200": (r) => r.status === 200,
    "GET /posts - response time < 400ms": (r) => r.timings.duration < 400,
    "GET /posts - has content": (r) => r.body && r.body.length > 0,
  });

  sleep(1); // Simulate user pause between requests
}
