import React from "react";
import * as S from "./style";
const MyComment = ({ title, content, createdDate }) => {
  return (
    <S.Container>
      <S.Title>{title}</S.Title>
      <S.ContentWrapper>
        <S.Content>{content}</S.Content>
        <S.createdDate>{createdDate}</S.createdDate>
      </S.ContentWrapper>
    </S.Container>
  );
};

export default MyComment;
