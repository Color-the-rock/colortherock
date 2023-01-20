import React from "react";
import * as S from "./style";
import KakaoBtn from "../../assets/img/LogIn/kakao-login.png";
import GoogleBtn from "../../assets/img/LogIn/google-login.png";
import { Mobile, Desktop } from "../../components/layout/Template"
import { KAKAO_AUTH_URL } from "./Kakao";
import { GOOGLE_AUTH_URL } from "./Google";
import Header from "../../components/layout/Header"

const Login = () => {

  

  return (
    <div>
    <Desktop>
      <Header></Header>
        <S.Container>
          <S.Title>로그인</S.Title>
          <S.ContentWrap>
              <S.LoginWrap href={KAKAO_AUTH_URL} >
                <img src={KakaoBtn} alt="Kakao login Btn" />  
              </S.LoginWrap>
              <S.LoginWrap href={GOOGLE_AUTH_URL} >
                <img src={GoogleBtn} alt="Google Loin Btn" />  
              </S.LoginWrap>
          </S.ContentWrap>
        </S.Container>
      </Desktop>
      
      <Mobile>
        <S.Container>
          <S.Title>로그인</S.Title>
          <S.LogoImg></S.LogoImg>
          <S.ContentWrap>
              <S.LoginWrap href={KAKAO_AUTH_URL} >
                  <img src={KakaoBtn} alt="Kakao login Btn" />  
              </S.LoginWrap>
              <S.LoginWrap href={GOOGLE_AUTH_URL} >
                  <img src={GoogleBtn} alt="Google Loin Btn" />  
              </S.LoginWrap>
          </S.ContentWrap>
        </S.Container>
      </Mobile>
    </div>
  );
};
export default Login;
