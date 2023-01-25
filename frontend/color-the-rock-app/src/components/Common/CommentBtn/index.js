import React from 'react'
import * as S from "./style"
import { HiDotsHorizontal } from "react-icons/hi";
const CommentBtn = ({title, readOnly}) => {
  return (
    <S.Container>
      <S.InputBtn placeholder='댓글 달기' props={readOnly}></S.InputBtn>
      <S.Ornament><HiDotsHorizontal/></S.Ornament>
    </S.Container>
  )
}

export default CommentBtn;
