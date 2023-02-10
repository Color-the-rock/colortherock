import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";
const IntroTitle = ({ text, margin }) => (
  <S.Title margin={margin}>
    {text}
    <S.Dot>.</S.Dot>
  </S.Title>
);

export default IntroTitle;

IntroTitle.propTypes = {
  text: PropTypes.string,
  margin: PropTypes.string,
};
