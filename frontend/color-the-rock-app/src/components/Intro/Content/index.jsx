import React from "react";
import IntroColorTheRockMobile from "../../../assets/img/intro/img-intro-colortherock-mobile.png";
import * as S from "./style";

const Content = () => {
  return (
    <S.Container>
      <img src={IntroColorTheRockMobile} alt="intro-logo" width="280px" />
      <S.Text>컬러더락과 함께 클라이밍 레벨을 채워봐요!</S.Text>
    </S.Container>
  );
};

export default Content;
