import axios from "axios";
import { useCookies } from "react-cookie";

// url 설정 수정 필요
const BASE_URL = "https://colortherock.com/api";

const instance = axios.create({
  baseURL: BASE_URL,
});

instance.defaults.headers.common["Content-Type"] = "application/json";

instance.interceptors.request.use(function (config) {
  console.log("interceptors.request");
  const accessToken =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJyZWdpc3RyYXRpb25JZCI6Imtha2FvIiwiaWQiOjIsImV4cCI6MTY3NTQyNTIzNSwiaWF0IjoxNjc1NDIxNjM1LCJlbWFpbCI6ImV4YW1wbGUxQGNvbG9ydGhlcm9jay5jb20ifQ.V7nTgZZf0NXCgWivNVj35yH_5Y9-a4UegS2kFMJxWgs";

  config.headers.Authorization = `${accessToken}`;
  return config;
});

instance.interceptors.response.use(
  // 2xx 응답이 오면 return;
  (response) => {
    console.log("interceptors check: response success");
    return response;
  },

  // error 가 오면
  async (error) => {
    console.log("interceptors check: response fail");
    // error에 담겨있는 config와 response 구조 분해 할당

    // 여기 코드 수정 error는 모두 400으로 날아옴...
    const {
      config,
      response: { status },
    } = error;

    // token 만료시 401 error
    if (status === 401) {
      const originalRequest = config;
      const refreshToken = await sessionStorage.getItem("refreshToken");

      // refreshToken이 있는 경우에만 재요청 시도
      if (refreshToken) {
        // token refresh 요청
        const token = sessionStorage.getItem("token");
        const data = await axios.post(
          `https://colortherock.com/refresh`, // token refresh api
          {
            accessToken: `Bearer ${token}`,
            refreshToken: `Bearer ${refreshToken}`,
          },
          {
            // header에 넣지?? 흠....
            "Content-Type": "application/json",
          }
        );

        const accessToken = data;
        await sessionStorage.setItem(["accessToken", accessToken]);

        originalRequest.headers.Authorization = `Bearer ${accessToken}`;
        return axios(originalRequest);
      }
    }

    console.log("response error", error);
    return Promise.reject(error);
  }
);

export const defaultInstance = instance;

// // 원본 코드(삭제 금지)
// instance.interceptors.request.use(function (config) {
//   const accessToken = sessionStorage.getItem("accessToken");

//   // 요청시 AccessToken 계속 보내주기
//   if (!accessToken) {
//     config.headers.Authorization = null;
//     return config;
//   }

//   if (config.headers && accessToken) {
//     config.headers.Authorization = `Bearer ${accessToken}`;
//     return config;
//   }
// });

// instance.interceptors.response.use(
//   // 2xx 응답이 오면 return;
//   (response) => {
//     return response;
//   },

//   // error 가 오면
//   async (error) => {
//     // error에 담겨있는 config와 response 구조 분해 할당
//     // 여기 코드 수정 error는 모두 400으로 날아옴...
//     const {
//       config,
//       response: { status },
//     } = error;

//     // token 만료시 401 error
//     if (status === 401) {
//       const [cookies, setCookie] = useCookies["refreshToken"];
//       const originalRequest = config;
//       const refreshToken = cookies;

//       // refreshToken이 있는 경우에만 재요청 시도
//       if (refreshToken) {
//         // token refresh 요청
//         const token = sessionStorage.getItem("token");
//         const data = await axios.post(
//           `https://colortherock.com/refresh`, // token refresh api
//           {
//             accessToken: `Bearer ${token}`,
//             refreshToken: `Bearer ${refreshToken}`,
//           },
//           {
//             headers: {
//               "Content-Type": "application/json",
//             },
//           }
//         );

//         const accessToken = data;
//         await sessionStorage.setItem(["accessToken", accessToken]);

//         originalRequest.headers.Authorization = `Bearer ${accessToken}`;
//         return axios(originalRequest);
//       }
//     }

//     console.log("response error", error);
//     return Promise.reject(error);
//   }
// );
