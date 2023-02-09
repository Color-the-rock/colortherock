import React from "react";
import * as S from "./style";
import CommentBtn from "../CommentBtn";
import BoardCommentList from "../../Board/BoardCommentList";
import { FiX } from "react-icons/fi";
import PropTypes from "prop-types";
const CommentModal = ({ setIsModalOpen, isModalOpen }) => {
  const closeModalHandler = () => {
    setIsModalOpen(false);
  };

  return (
    <S.Container>
      <S.CommentWrap>
        {/* 장식용 바 */}
        <S.OrnamentWrap>
          <S.Ornament />
        </S.OrnamentWrap>

        <S.CloseBtnWrap>
          <S.CloseButton>
            <FiX size="24px" onClick={closeModalHandler} />
          </S.CloseButton>
        </S.CloseBtnWrap>

        <S.CommentBtnWrap>
          <CommentBtn isReadOnly={!isModalOpen} />
        </S.CommentBtnWrap>

        <S.CommentListWrap>
          <BoardCommentList />
        </S.CommentListWrap>
      </S.CommentWrap>
    </S.Container>
  );
};

export default React.memo(CommentModal);

CommentModal.propTypes = {
  setIsModalOpen: PropTypes.func,
  isModalOpen: PropTypes.bool,
};
