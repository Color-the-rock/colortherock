import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const SubTitle = ({ text }) => <S.Text>{text}</S.Text>;

SubTitle.propTypes = {
  text: PropTypes.string,
};

export default SubTitle;
