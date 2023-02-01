// user(회원관리) API 작성
const requests = {

  // 소셜 로그인
  GetSocialLogin: "/auth2/authorization",
  
  // JWT 리프레시 토큰 가져오기
  GetRefleshToken: "/refresh",

  // 닉네임 중복체크
  CheckNickname: "/member/duplicateNickname",
  
  // 회원가입 마무리
  finishSignup: "/member/signup",

}


export default requests;