import React from "react";
import * as S from "./style";
const IntroButton = ({ path, text }) => (
  <S.Button path={path}>
    <S.GradientText>{text}</S.GradientText>
  </S.Button>
);

export default IntroButton;
