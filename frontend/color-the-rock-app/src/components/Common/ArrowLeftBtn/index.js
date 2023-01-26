import React from 'react'
import * as S from "./style"
import { FiArrowLeft } from "react-icons/fi";

const ArrowLeftBtn = ({clickHandler}) => {

  return (
    <S.Container>
      <S.IconWrap onClick={clickHandler}>
        <FiArrowLeft />
      </S.IconWrap>
    </S.Container>
  )
}

export default ArrowLeftBtn;