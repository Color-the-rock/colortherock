import React from "react";
import * as S from "./style";
import { FiArrowLeft } from "react-icons/fi";
import PropTypes from "prop-types";

const ArrowLeftBtn = ({ clickHandler }) => {
  return (
    <S.Container>
      <S.IconWrap>
        <FiArrowLeft onClick={clickHandler} />
      </S.IconWrap>
    </S.Container>
  );
};

export default ArrowLeftBtn;

ArrowLeftBtn.propTypes = {
  clickHandler: PropTypes.func,
};
