import React, { useState } from "react";
import { useInput } from "../../../hooks/useInput";
import PropTypes from "prop-types";
import * as S from "./style";
import { useSelector } from "react-redux";
import boardApi from "../../../api/board";
const BoardComment = ({
  commentId,
  nickname,
  content,
  createdDate,
  getAllComments,
}) => {
  const localUser = useSelector((state) => state.users.nickName);
  const [inputValue, onChangeInputValue] = useInput(content);
  const [isReadOnly, setReadOnly] = useState(true);
  const handelOnClickDeleteButton = () => {
    boardApi
      .deleteVideoBoardComment(commentId)
      .then(() => {
        alert("댓글이 삭제되었습니다:)");
        getAllComments();
      })
      .catch((error) => console.log(error));
  };

  const handelOnClickUpdateButton = () => {
    setReadOnly(false);
  };

  const handleOnClickSaveButton = () => {
    setReadOnly(true);

    const requestBody = {
      commentId: commentId,
      content: inputValue,
    };

    boardApi
      .putVideoBoardComment(requestBody)
      .then(() => {
        alert("댓글이 수정되었습니다:)");
      })
      .catch((error) => console.log(error));
  };

  return (
    <S.Container>
      <S.ContentWrapper>
        <S.Content
          type="text"
          defaultValue={content}
          readOnly={isReadOnly}
          onChange={onChangeInputValue}
        />
        {localUser === nickname && (
          <S.ButtonWrapper>
            <S.Button
              onClick={
                isReadOnly ? handelOnClickUpdateButton : handleOnClickSaveButton
              }
            >
              {isReadOnly ? "수정" : "저장"}
            </S.Button>
            <S.Button
              onClick={
                isReadOnly ? handelOnClickDeleteButton : () => setReadOnly(true)
              }
            >
              {isReadOnly ? "삭제" : "취소"}
            </S.Button>
          </S.ButtonWrapper>
        )}
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
  content: PropTypes.string,
  nickname: PropTypes.string,
  createdDate: PropTypes.string,
  getAllComments: PropTypes.func,
};
