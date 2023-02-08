import React from "react";
import IntroColorTheRockMobile from "../../../assets/img/intro/img-intro-colortherock-mobile.png";
import IntroMobileDT from "../../../assets/img/intro/bg-intro-desktop.png";
import IntroMobileBG from "../../../assets/img/intro/bg-intro.png";
import * as S from "./style";
import { Mobile, Desktop } from "../../layout/Template";
const Content = () => {
  return (
    <>
      <Mobile>
        <S.Container bg={IntroMobileBG}>
          <img src={IntroColorTheRockMobile} alt="intro-logo" width="280px" />
          <S.Text>컬러더락과 함께 클라이밍 레벨을 채워봐요!</S.Text>
        </S.Container>
      </Mobile>
      <Desktop>
        <S.Container bg={IntroMobileDT}>
          <img src={IntroColorTheRockMobile} alt="intro-logo" width="280px" />
          <S.Text>컬러더락과 함께 클라이밍 레벨을 채워봐요!</S.Text>
        </S.Container>
      </Desktop>
    </>
  );
};

export default Content;
