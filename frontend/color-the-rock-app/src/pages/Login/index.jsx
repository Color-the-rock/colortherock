import React from "react";
import * as S from "./style";
import KakaoBtn from "../../assets/img/LogIn/kakao-login.png";
import GoogleBtn from "../../assets/img/LogIn/google-login.png";
import { Mobile, Desktop } from "../../components/layout/Template";
// import { KAKAO_AUTH_URL } from "./Kakao";
// import { GOOGLE_AUTH_URL } from "./Google";
import AppLogo from "../../assets/img/common/app-logo.png";
import Header from "../../components/layout/Header";
import { useNavigate } from "react-router-dom";
import { VscChromeClose } from "react-icons/vsc";
import AppLogo2 from "../../assets/img/common/web-logo.png";
import axios from "axios";

const Login = () => {
  const navigate = useNavigate();
  const onClickHandler = () => {
    navigate("/");
  };

  const LoginHandler = () => {
    axios({
      method: "get",
      url: "https://colortherock.com/oauth2/authorization/kakao",
    })
      .then((res) => {
        console.log("res: ", res);
        console.log("성공");
      })
      .catch((err) => {
        console.log("err: ", err);
        console.log("실패");
      });
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
              <S.LogoImg src={AppLogo} alt="app logo"></S.LogoImg>
              <S.SecondLogo>
                {`Color the Rock,\nColor your Rock!`}
                {/* <div>Color the Rock,</div>
                <div>Color your Rock!</div> */}
              </S.SecondLogo>
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
              <S.LogoImg src={AppLogo} alt="app logo"></S.LogoImg>
              <S.SecondLogo>{`Color the Rock,\nColor your Rock!`}</S.SecondLogo>
              <button
                onClick={LoginHandler}
                style={{
                  color: "white",
                  height: "20px",
                  width: "30px",
                }}
              >
                버튼
              </button>
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
