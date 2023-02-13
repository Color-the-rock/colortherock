import React, { useEffect, useState } from "react";
import * as S from "./style";
import CommentBtn from "../CommentBtn";
import BoardCommentList from "../../Board/BoardCommentList";
import { FiX } from "react-icons/fi";
import PropTypes from "prop-types";
import boardApi from "../../../api/board";
import { useParams } from "react-router-dom";
import useInfiniteScroll from "../../../hooks/useInfiniteScroll";

const CommentModal = ({ setIsModalOpen, isModalOpen }) => {
  const { id } = useParams();
  // const [result, setResult] = useState([]);
  const [storeId, setStoreId] = useState(-1);
  const [reset, setReset] = useState(false);

  const closeModalHandler = () => {
    setIsModalOpen(false);
  };

  // const getAllComments = () => {
  //   console.log("그렇군요.");

  //   return boardApi
  //     .getVideoBoardCommentList(storeId, id)
  //     .then(({ data: { status, result: _result } }) => {
  //       if (status === 200) {
  //         console.log("statusCode : 200", _result);
  //         if (storeId === -1) {
  //           setResult([..._result]);
  //         } else {
  //           setResult((prev) => [...prev, ..._result]);
  //         }

  //         let _storId =
  //           _result.length === 0 ? -1 : _result[_result.length - 1].commentId;

  //         console.log("storeId ??? ", _storId);
  //         setStoreId(_storId);
  //         setIsFetching(false);
  //       }
  //     })
  //     .catch((error) => console.log(error));
  // };

  // useEffect(() => {
  //   getAllComments();
  // }, [reset]);

  // const [isFetching, setIsFetching] = useInfiniteScroll(getAllComments);

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
