import React from "react";
import * as S from "./style";
import KakaoBtn from "../../assets/img/LogIn/kakao-login.png";
import GoogleBtn from "../../assets/img/LogIn/google-login.png";
import { Mobile, Desktop } from "../../components/layout/Template";
import WebLogo from "../../assets/img/common/web-logo.svg";
import JoinUs from "../../assets/img/LogIn/img-signup-join-us.png";
import Header from "../../components/layout/Header";
import { useNavigate } from "react-router-dom";
import { VscChromeClose } from "react-icons/vsc";

const Login = () => {
  const navigate = useNavigate();
  const onClickHandler = () => {
    navigate("/");
  };

  return (
    <div>
      <Desktop>
        <Header />
        <S.Container>
          <S.ContentWrap>
            <S.CloseBtnContainer>
              <S.CloseBtn></S.CloseBtn>
            </S.CloseBtnContainer>
            <S.LogoContainer>
              <S.LogoImg src={WebLogo} alt="app logo" />
              <S.SecondLogo src={JoinUs} alt="app logo" />
            </S.LogoContainer>
            <S.LoginContainer>
              <S.LoginWrap href="https://colortherock.com/oauth2/authorization/kakao">
                <img src={KakaoBtn} alt="Kakao login Btn" />
              </S.LoginWrap>
              <S.LoginWrap href="https://colortherock.com/oauth2/authorization/google">
                <img src={GoogleBtn} alt="Google Loin Btn" />
              </S.LoginWrap>
            </S.LoginContainer>
          </S.ContentWrap>
        </S.Container>
      </Desktop>

      <Mobile>
        <S.Container>
          <S.CloseBtnContainer>
            <S.CloseBtn>
              <VscChromeClose onClick={onClickHandler} />
            </S.CloseBtn>
          </S.CloseBtnContainer>
          <S.ContentWrap>
            <S.LogoContainer>
              <S.LogoImg src={WebLogo} alt="app logo"></S.LogoImg>
              <S.SecondLogo src={JoinUs} alt="app logo" />
            </S.LogoContainer>

            <S.LoginContainer>
              <S.LoginWrap href="https://colortherock.com/oauth2/authorization/kakao">
                <img src={KakaoBtn} alt="Kakao login Btn" />
              </S.LoginWrap>
              <S.LoginWrap href="https://colortherock.com/oauth2/authorization/google">
                <img src={GoogleBtn} alt="Google Loin Btn" />
              </S.LoginWrap>
            </S.LoginContainer>
          </S.ContentWrap>
        </S.Container>
      </Mobile>
    </div>
  );
};
export default Login;
