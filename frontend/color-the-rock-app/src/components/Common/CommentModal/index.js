import React, { useState } from "react";
import * as S from "./style";
import CommentBtn from "../CommentBtn";
import BoardCommentList from "../../Board/BoardCommentList";
import { FiX } from "react-icons/fi";
import PropTypes from "prop-types";
import { useParams } from "react-router-dom";

const CommentModal = ({ setIsModalOpen, isModalOpen }) => {
  const { id } = useParams();
  const [storeId, setStoreId] = useState(-1);
  const [reset, setReset] = useState(false);

  const closeModalHandler = () => {
    setIsModalOpen(false);
  };

  return (
    <S.Container>
      <S.CommentWrap>
        <S.OrnamentWrap>
          <S.Ornament />
        </S.OrnamentWrap>
        <S.CloseBtnWrap>
          <S.CloseButton>
            <FiX size="24px" onClick={closeModalHandler} />
          </S.CloseButton>
        </S.CloseBtnWrap>

        <S.CommentBtnWrap>
          <CommentBtn
            setReset={setReset}
            setStoreId={setStoreId}
            isReadOnly={!isModalOpen}
          />
        </S.CommentBtnWrap>

        <S.CommentListWrap>
          <BoardCommentList
            videoId={id}
            setStoreId={setStoreId}
            storeId={storeId}
            reset={reset}
            setReset={setReset}
          />
        </S.CommentListWrap>
      </S.CommentWrap>
    </S.Container>
  );
};

export default CommentModal;

CommentModal.propTypes = {
  setIsModalOpen: PropTypes.func,
  isModalOpen: PropTypes.bool,
};
