import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";
const BasicButton = ({ text, link }) => <S.Button to={link}>{text}</S.Button>;
export default BasicButton;

BasicButton.propTypes = {
  text: PropTypes.string,
  link: PropTypes.string,
};
