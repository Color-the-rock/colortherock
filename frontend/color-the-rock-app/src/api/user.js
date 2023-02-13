import { defaultInstance } from "./utils";

// user(회원관리) API 작성
const UserApi = {
  // 닉네임 중복체크
  CheckNickname: (nickName) =>
    defaultInstance.post("/duplicateNickname", {
      nickName,
    }),

  // 회원가입 API
  SignUp: (data) => defaultInstance.post("/member/signup", data),
};

export default UserApi;
