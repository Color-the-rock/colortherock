import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const IntroButton = ({ path, text }) => (
  <S.Button to={path}>
    <S.GradientText>{text}</S.GradientText>
  </S.Button>
);

export default IntroButton;

IntroButton.protoTypes = {
  path: PropTypes.path,
  text: PropTypes.text,
};
