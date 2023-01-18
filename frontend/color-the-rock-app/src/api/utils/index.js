import axios from "axios";

// url 설정 수정 필요
const BASE_URL = "";

const defaultApi = (url, option) => {
  const instance = axios.create({
    baseURL: BASE_URL,
    ...option,
  });
  return instance;
};

// 인증 토큰 관련 api 처리
// 로그인 처리시 변경해야할 로직
const authApi = (url, option) => {
  const accessToken = sessionStorage.getItem("");
  const instance = axios.create({
    baseURL: BASE_URL,
    headers: {
      Authorization: ``,
    },
    ...option,
  });
  return instance;
};

export const defaultInstance = defaultApi(BASE_URL);
export const AuthInstance = authApi(BASE_URL);
