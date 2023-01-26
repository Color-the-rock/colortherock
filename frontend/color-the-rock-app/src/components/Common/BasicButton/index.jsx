import React from "react";
import * as S from "./style";
const BasicButton = ({ text, link }) => <S.Button to={link}>{text}</S.Button>;
export default BasicButton;
