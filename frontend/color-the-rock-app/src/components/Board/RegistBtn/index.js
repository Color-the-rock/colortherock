import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

function RegistBtn({ btnName, clickHandler }) {
  return (
    <S.Container onClick={clickHandler}>
      <S.ButtonWrap>{btnName}</S.ButtonWrap>
    </S.Container>
  );
}

RegistBtn.propTypes = {
  btnName: PropTypes.string.isRequired,
  clickHandler: PropTypes.func.isRequired,
};

export default React.memo(RegistBtn);
