import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const RegistBtn = ({ btnName, clickHandler, size = "50px" }) => {
  return (
    <S.Container onClick={clickHandler} size={size}>
      <S.ButtonWrap>{btnName}</S.ButtonWrap>
    </S.Container>
  );
};

export default React.memo(RegistBtn);

RegistBtn.propTypes = {
  btnName: PropTypes.string.isRequired,
  clickHandler: PropTypes.func.isRequired,
};
