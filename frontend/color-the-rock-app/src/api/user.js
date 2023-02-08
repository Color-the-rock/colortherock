import { defaultInstance } from "./utils";

// user(회원관리) API 작성
const UserApi = {
  // 소셜 로그인
  // GetSocialLogin: "/auth2/authorization",

  // JWT 리프레시 토큰 가져오기
  // GetRefleshToken: "/refresh",

  // 닉네임 중복체크
  CheckNickname: (nickName) =>
    defaultInstance.post("/duplicateNickname", {
      nickName,
    }),
  // CheckNickname: (nickName) => {
  //   defaultInstance.post("/duplicateNickname", nickName, {
  //     headers: {
  //       "Content-Type": "application/json",
  //     },
  //   });
  // },
  // 회원가입 API
<<<<<<< HEAD
  SignUp: (data) =>
    defaultInstance.post("/member/signup", data, {
      headers: {
        "Content-Type": "application/json",
      },
    }),
=======
  SignUp: (data) => defaultInstance.post("/member/signup", data),
>>>>>>> d09182074696f4a40ad8d14f935f554e9da80254
};

export default UserApi;
