import React, { useState } from "react";
import { useInput } from "../../../hooks/useInput";
import PropTypes from "prop-types";
import * as S from "./style";
import { useSelector } from "react-redux";
import boardApi from "../../../api/board";
const BoardComment = ({ commentId, title, nickname, content, createdDate }) => {
  const localUser = useSelector((state) => state.users.nickName);
  const [inputValue, onChangeInputValue] = useInput(content);
  const [isReadOnly, setReadOnly] = useState(true);
  const handelOnClickDeleteButton = () => {
    boardApi
      .deleteBoardDetail(commentId)
      .then(({ date: { status } }) => {
        if (status === 200) {
          alert("댓글이 삭제되었습니다:)");
        }
      })
      .catch((error) => console.log(error));
  };

  const handelOnClickUpdateButton = () => {
    setReadOnly(true);
    // boardApi
    //   .putVideoBoardComment({ commentId, inputValue })
    //   .then(({ date: { status } }) => {
    //     if (status === 200) {
    //       alert("댓글이 삭제되었습니다:)");
    //     }
    //   })
    //   .catch((error) => console.log(error));
  };
  return (
    <S.Container>
      <S.ContentWrapper>
        <S.Title>{title}</S.Title>
        {localUser === nickname && (
          <S.ButtonWrapper>
            <S.Button onClick={handelOnClickDeleteButton}>수정</S.Button>
            <S.Button onClick={handelOnClickUpdateButton}>삭제</S.Button>
          </S.ButtonWrapper>
        )}
      </S.ContentWrapper>

      <S.ContentWrapper>
        <S.Content type="text" defaultValue={content} readOnly={isReadOnly} />
      </S.ContentWrapper>
      <S.ContentWrapper>
        <S.UserNickName>{nickname}</S.UserNickName>
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
