import React, { useEffect } from "react";
import * as S from "./style";
import { HiDotsHorizontal, HiOutlineArrowSmUp } from "react-icons/hi";
import { useInput } from "../../../hooks/useInput";
const CommentBtn = ({ title, isReadOnly, onClick }) => {
  const [value, onChangeValue] = useInput("");

  const handleRegisterComment = () => {};

  useEffect(() => {
    console.log("isReadOnly??", isReadOnly);
  }, [isReadOnly]);

  return (
    <S.Container onClick={onClick}>
      <S.InputBtn placeholder="댓글 달기" readOnly={isReadOnly}></S.InputBtn>
      {isReadOnly ? (
        <S.Ornament>
          <HiDotsHorizontal />
        </S.Ornament>
      ) : (
        <S.SendButton onClick={handleRegisterComment}>
          <HiOutlineArrowSmUp size="1rem" color="#ffffff" />
        </S.SendButton>
      )}
    </S.Container>
  );
};

export default CommentBtn;
