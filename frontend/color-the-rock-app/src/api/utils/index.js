import axios from "axios";
import { useCookies } from "react-cookie";

// url 설정 수정 필요
const BASE_URL = "https://colortherock.com/api";

const instance = axios.create({
  baseURL: BASE_URL,
});

instance.defaults.headers.common["Content-Type"] = "application/json";
instance.defaults.headers["Content-Type"] = "application/json";
// // 원본 코드(삭제 금지)
instance.interceptors.request.use(function (config) {
  console.log("interceptor request");
  const accessToken = sessionStorage.getItem("accessToken");

  // 요청시 AccessToken 계속 보내주기
  if (!accessToken) {
    config.headers.Authorization = null;
    return config;
  }

  if (config.headers && accessToken) {
    config.headers.Authorization = `${accessToken}`;
    return config;
  }
});

instance.interceptors.response.use(
  // console.log("interceptors response"),
  // 2xx 응답이 오면 return;
  (response) => {
    console.log("interceptor response 200");
    return response;
  },

  // error 가 오면
  async (error) => {
    // error에 담겨있는 config와 response 구조 분해 할당
    // 여기 코드 수정 error는 모두 400으로 날아옴...
    const {
      config,
      response: { status },
    } = error;

    // token 만료시 401 error
    if (status === 401) {
      console.log("401 error 토큰 재발급");
      const [cookies, setCookie] = useCookies(["refreshToken"]);
      const originalRequest = config;
      const refreshToken = cookies;

      // refreshToken이 있는 경우에만 재요청 시도
      if (refreshToken) {
        // token refresh 요청
        const token = sessionStorage.getItem("token");
        const data = await axios.post(
          `https://colortherock.com/refresh`, // token refresh api
          {
            accessToken: `${token}`,
            refreshToken: `${refreshToken}`,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
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
