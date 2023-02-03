import axios from "axios";
// url 설정 수정 필요
const BASE_URL = "https://colortherock.com/api";

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    Authorization:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJyZWdpc3RyYXRpb25JZCI6Imdvb2dsZSIsImlkIjoxLCJleHAiOjE2NzU0MDE4MzgsImlhdCI6MTY3NTM5ODIzOCwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIn0.r8AqMjup4kr-dTRNFisyMllvhvwcsWb38wUbn4APOXc",
  },
});

export const defaultInstance = instance;
