import React from "react";
import * as S from "./style";
const Title = ({ text, children }) => (
  <S.Text>
    {text}
    {children}
  </S.Text>
);
export default Title;
