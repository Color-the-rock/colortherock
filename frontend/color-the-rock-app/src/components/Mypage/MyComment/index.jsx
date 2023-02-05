import React from "react";
import PropTypes from "prop-types";
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

MyComment.prototype = {
  title: PropTypes.string,
  content: PropTypes.string,
  createdDate: PropTypes.string,
};
