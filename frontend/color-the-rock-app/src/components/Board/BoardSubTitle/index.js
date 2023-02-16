import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const SubTitle = ({ text }) => <S.Text>{text}</S.Text>;

export default SubTitle;

SubTitle.propTypes = {
  text: PropTypes.string,
};
