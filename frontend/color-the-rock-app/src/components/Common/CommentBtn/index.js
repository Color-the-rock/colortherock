import React from "react";
import * as S from "./style";
import { HiDotsHorizontal } from "react-icons/hi";
const CommentBtn = ({ title, isReadOnly, onClick }) => {
  return (
    <S.Container onClick={onClick}>
      <S.InputBtn placeholder="댓글 달기" readOnly={isReadOnly}></S.InputBtn>
      <S.Ornament>
        <HiDotsHorizontal />
      </S.Ornament>
    </S.Container>
  );
};

export default CommentBtn;
