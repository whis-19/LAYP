import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 30, 
  duration: '20s'
};

export default function () {
  const baseUrl = 'https://gorest.co.in/public/v2';
  const authToken = 'e9aba9a490938e1c8939ce391aeef4b73d45a75217c313ccd6a3be20fdce0458'; 

 
  const endpoint = '/users';  

  const url = `${baseUrl}${endpoint}`;

  const response = http.get(url, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  });

  check(response, {
    'status is 200': (r) => r.status === 200,
    'response time < 500ms': (r) => r.timings.duration < 500
  });
}