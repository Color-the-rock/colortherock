import React, { useEffect, useState } from "react";
import * as S from "./style";
import { HiDotsHorizontal, HiOutlineArrowSmUp } from "react-icons/hi";
import { useInput } from "../../../hooks/useInput";
import boardApi from "../../../api/board";
import { useParams } from "react-router-dom";
import PropTypes from "prop-types";
const CommentBtn = ({ isReadOnly, onClick, getAllComments }) => {
  const [value, setValue] = useState("");
  const { id } = useParams();
  const handleRegisterComment = () => {
    const requestBody = {
      videoBoardId: id,
      content: value,
    };

    console.log("re", requestBody);

    boardApi
      .postVideoBoardComment(requestBody)
      .then(({ data: { status } }) => {
        if (status === 200) {
          console.log("[postVideoBoardComment] statusCode : 200");
          getAllComments();
          setValue("");
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    console.log("isReadOnly??", isReadOnly);
  }, [isReadOnly]);

  return (
    <S.Container onClick={onClick}>
      <S.InputBtn
        placeholder="댓글 달기"
        onChange={(e) => setValue(e.target.value)}
        value={value}
        readOnly={isReadOnly}
      />
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
CommentBtn.propTypes = {
  isReadOnly: PropTypes.bool,
  onClick: PropTypes.func,
  getAllComments: PropTypes.func,
};
