import React, { useState } from "react";
import * as S from "./style";
import CommentBtn from "../CommentBtn";
import BoardCommentList from "../../Board/BoardCommentList";
import { FiX } from "react-icons/fi";
import PropTypes from "prop-types";
import boardApi from "../../../api/board";
import { useParams } from "react-router-dom";

const CommentModal = ({ setIsModalOpen, isModalOpen }) => {
  const { id } = useParams();
  const [result, setResult] = useState([]);
  const [storeId, setStoreId] = useState(-1);
  const closeModalHandler = () => {
    setIsModalOpen(false);
  };

  const getAllComments = () => {
    boardApi
      .getVideoBoardCommentList(storeId, id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);

          // let _storId =
          //   _result.length === 0 ? -1 : _result[_result.length - 1].commentId;

          // console.log("storeId ??? ", _storId);
          // setStoreId(_storId);
        }
      })
      .catch((error) => console.log(error));
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
            isReadOnly={!isModalOpen}
            getAllComments={getAllComments}
          />
        </S.CommentBtnWrap>

        <S.CommentListWrap>
          <BoardCommentList getAllComments={getAllComments} result={result} />
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
