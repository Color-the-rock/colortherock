import React from "react";
import PropTypes from "prop-types";
import * as S from "./style";
const AdminTitle = ({ text }) => <S.Title>{text}</S.Title>;
export default AdminTitle;

AdminTitle.propTypes = {
  text: PropTypes.string,
};
