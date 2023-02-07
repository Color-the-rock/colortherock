import React from "react";
import { useLocation } from "react-router-dom";
import * as S from "./style";
import Logo from "../../assets/img/common/app-logo.png";
const ErrorPage = () => {
  const location = useLocation();

  return location.pathname === "/mypage" || location.pathname === "/record" ? (
    <S.Container>
      <img src={Logo} alt="logo" width="120px" />
      <S.Guide>로그인이 필요한 서비스 입니다.</S.Guide>
      <S.SLink to="/login">로그인으로 이동하기</S.SLink>
    </S.Container>
  ) : (
    <S.Container>
      <img src={Logo} alt="logo" width="120px" />
      <S.Guide>잘못된 접근입니다:(</S.Guide>
      <S.SLink to="/">메인으로 이동하기</S.SLink>
    </S.Container>
  );
};
export default ErrorPage;
