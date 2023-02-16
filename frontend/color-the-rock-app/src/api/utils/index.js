import axios from "axios";
import { useCookies } from "react-cookie";

const BASE_URL = "https://colortherock.com/api";

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

instance.interceptors.request.use(function (config) {
  console.log("interceptor request");
  const accessToken = sessionStorage.getItem("accessToken");

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
  (response) => {
    console.log("interceptor response 200");
    return response;
  },

  async (error) => {
    const {
      config,
      response: { status },
    } = error;

    // statusCode 401 : 만료된 토큰
    if (status === 401) {
      const [cookies, setCookie] = useCookies(["refreshToken"]);
      const originalRequest = config;
      const refreshToken = cookies;

      // 토큰 재발급을 위한 요청
      if (refreshToken) {
        const token = sessionStorage.getItem("accessToken");
        const data = await axios.post(
          `https://colortherock.com/refresh`,
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

    console.log("[api] refreshToken 발급 : ", error);
    return Promise.reject(error);
  }
);

export const defaultInstance = instance;
