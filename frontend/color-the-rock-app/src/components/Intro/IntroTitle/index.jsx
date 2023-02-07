import React from "react";
import * as S from "./style";
const IntroTitle = ({ text }) => (
  <S.Title>
    {text}
    <S.Dot>.</S.Dot>
  </S.Title>
);

export default IntroTitle;
