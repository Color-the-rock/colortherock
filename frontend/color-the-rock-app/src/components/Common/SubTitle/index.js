import React from "react";
import * as S from "./style";

const SubTitle = ({ text, children, margin = "0" }) => (
  <S.Text margin={margin}>
    {text}
    {children}
  </S.Text>
);
export default SubTitle;
