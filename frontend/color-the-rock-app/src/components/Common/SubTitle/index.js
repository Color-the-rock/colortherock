import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const SubTitle = ({ text, children, margin = "0" }) => (
  <S.Text margin={margin}>
    {text}
    {children}
  </S.Text>
);
export default SubTitle;

SubTitle.propTypes = {
  text: PropTypes.string,
  children: PropTypes.node,
  margin: PropTypes.string,
};
