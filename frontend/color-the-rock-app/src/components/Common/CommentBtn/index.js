import React, { useState } from "react";
import * as S from "./style";
import { HiDotsHorizontal, HiOutlineArrowSmUp } from "react-icons/hi";
import boardApi from "../../../api/board";
import { useParams } from "react-router-dom";
import PropTypes from "prop-types";

const CommentBtn = ({
  setReset,
  setStoreId,
  onClick,
  isReadOnly,
  isLive = false,
}) => {
  const { id } = useParams();
  const [value, setValue] = useState("");

  const handleRegisterComment = () => {
    if (value === "") return;
    const requestBody = {
      videoBoardId: id,
      content: value,
    };

    boardApi
      .postVideoBoardComment(requestBody)
      .then(({ data: { status } }) => {
        if (status === 200) {
          setValue("");
          setStoreId(-1);
          setReset(true);
        }
      })
      .catch((error) => console.log(error));
  };
  const handleRegisterComment2 = (e) => {
    if (e.keyCode !== 13) return;
    if (e.target.value === "") return;
    const requestBody = {
      videoBoardId: id,
      content: value,
    };

    boardApi
      .postVideoBoardComment(requestBody)
      .then(({ data: { status } }) => {
        if (status === 200) {
          setValue("");
          setStoreId(-1);
          setReset((prev) => !prev);
        }
      })
      .catch((error) => console.log(error));
  };

  return (
    <S.Container onClick={onClick}>
      <S.InputBtn
        placeholder={isLive ? "채팅하기" : "댓글달기"}
        onChange={(e) => setValue(e.target.value)}
        value={value}
        readOnly={isReadOnly}
        onKeyDown={handleRegisterComment2}
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
  setStoreId: PropTypes.func,
  setReset: PropTypes.func,
  isLive: PropTypes.bool,
};
