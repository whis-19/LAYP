import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 30, // Number of users
  duration: '20s'
};

export default function () {
  const baseUrl = 'https://gorest.co.in/public/v2';
  const authToken = 'e9aba9a490938e1c8939ce391aeef4b73d45a75217c313ccd6a3be20fdce0458'; 
  
  const userId = '123'; 

  const endpoint = `/users/${userId}`;
  const url = `${baseUrl}${endpoint}`;

  const response = http.del(url, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  });

  check(response, {
    
    'status is successful': (r) => r.status >= 200 && r.status < 300,
  });
}