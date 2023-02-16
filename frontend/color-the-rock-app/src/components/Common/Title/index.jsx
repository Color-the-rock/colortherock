import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const Title = ({ text, children }) => (
  <S.Text>
    {text}
    {children}
  </S.Text>
);
export default Title;

Title.propTypes = {
  text: PropTypes.string,
  children: PropTypes.node,
};
