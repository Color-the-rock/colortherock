import React from 'react'
import * as S from "./style"
import { FiArrowLeft } from "react-icons/fi";

const ArrowLeftBtn = () => {

  return (
    <S.Container>
      <S.IconWrap>
        <FiArrowLeft />
      </S.IconWrap>
    </S.Container>
  )
}

export default ArrowLeftBtn;