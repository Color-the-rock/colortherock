import React from "react";
import * as S from "./style";
const IntroButton = ({ text }) => (
  <S.Button>
    <S.GradientText>{text}</S.GradientText>
  </S.Button>
);

export default IntroButton;
