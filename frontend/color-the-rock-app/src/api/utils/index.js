import axios from "axios";
// url 설정 수정 필요
const BASE_URL = "https://colortherock.com/api";

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    Authorization:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJyZWdpc3RyYXRpb25JZCI6Imtha2FvIiwiaWQiOjIsImV4cCI6MTY3NTQxNDcwNiwiaWF0IjoxNjc1NDExMTA2LCJlbWFpbCI6ImV4YW1wbGUxQGNvbG9ydGhlcm9jay5jb20ifQ.OvrTHhvczCSpkmk4oJnkXEmbITkWYecpPHFSFHrvFV4",
  },
});

export const defaultInstance = instance;
