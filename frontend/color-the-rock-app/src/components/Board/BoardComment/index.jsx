import React from "react";
import PropTypes from "prop-types";
import * as S from "./style";
const BoardComment = ({ commentId, title, nickname, content, createdDate }) => {
  return (
    <S.Container>
      <S.Title>{title}</S.Title>
      <S.ContentWrapper>
        <S.Content>{content}</S.Content>
      </S.ContentWrapper>
      <S.ContentWrapper>
        <S.Content>{nickname}</S.Content>
        <S.createdDate>{createdDate}</S.createdDate>
      </S.ContentWrapper>
    </S.Container>
  );
};

export default BoardComment;

BoardComment.propTypes = {
  commentId: PropTypes.number,
  title: PropTypes.string,
  content: PropTypes.string,
  createdDate: PropTypes.string,
};
