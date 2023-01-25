import React from "react";
import * as S from "./style";

const SubTitle = ({ text, margin = "0" }) => (
  <S.Text margin={margin}>{text}</S.Text>
);
export default SubTitle;
