import http from 'k6/http';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/2.4.0/dist/bundle.js";
import { check } from 'k6';
import { Rate } from 'k6/metrics';

const errorRate = new Rate('errorRate');

export const options = {
  vus: 50,
  duration: '30s',
};

export default function () {
  const response = http.get('http://localhost:8080/v1/api/bank/tech-talk-parallel');
  //172 53  - Virtual Threads
  //184 103 - Async
  check(response, { 'status was 200': (response) => response.status === 200 });
  errorRate.add(response.status >= 400);
}

export function handleSummary(data) {
  return {
    "summary.html": htmlReport(data),
  };
}
