import axios from "axios";

// url 설정 수정 필요
const BASE_URL = "https://www.colortherock.com/api";

const defaultApi = (option) => {
  const instance = axios.create({
    baseURL: BASE_URL,
    ...option,
  });
  return instance;
};

defaultApi().defaults.headers.common["Content-Type"] = "application/json";

defaultApi().interceptors.request.use(function (config) {
  const accessToken = sessionStorage.getItem("accessToken");

  // 요청시 AccessToken 계속 보내주기
  if (!accessToken) {
    config.headers.Authorization = null;
    // config.headers.refreshToken = null;
    return config;
  }

  if (config.headers && accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
    // config.headers.refreshToken = `Bearer ${refreshToken}`;
    return config;
  }
});

defaultApi().interceptors.response.use(
  // 2xx 응답이 오면 return;
  (response) => {
    return response;
  },

  // error 가 오면
  async (error) => {
    // error에 담겨있는 config와 response 구조 분해 할당
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

export const defaultInstance = defaultApi();
