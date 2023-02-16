import React from "react";
import PropTypes from "prop-types";
import * as S from "./style";

const MyComment = ({ title, content, createdDate, videoBoardId }) => {
  return (
    <S.Container to={`/board/detail/${videoBoardId}`}>
      <S.Title>{title}</S.Title>
      <S.ContentWrapper>
        <S.Content>{content}</S.Content>
        <S.createdDate>{createdDate}</S.createdDate>
      </S.ContentWrapper>
    </S.Container>
  );
};

export default MyComment;

MyComment.propTypes = {
  videoBoardId: PropTypes.number,
  title: PropTypes.string,
  content: PropTypes.string,
  createdDate: PropTypes.string,
};
