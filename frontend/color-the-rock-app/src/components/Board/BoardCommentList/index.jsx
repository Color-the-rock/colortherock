import React, { useState, useEffect, useRef } from "react";
import BoardComment from "../BoardComment";
import PropTypes from "prop-types";
import * as S from "./style";
import boardApi from "../../../api/board";
import useInfiniteScroll from "../../../hooks/useInfiniteScroll";

const BoardCommentList = ({
  videoId,
  reset,
  setReset,
  storeId,
  setStoreId,
}) => {
  const [result, setResult] = useState([]);

  //////////////////////////////////////////////////////////

  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(1);

  useEffect(() => {
    if (reset) {
      setReset(false);
      getAllComments();
    }
    if (loading) return;
    setLoading(true);
    // Make a request to load more items
    getAllComments();
  }, [loading, page, reset]);

  // useEffect(() => {
  //   getAllComments();
  // }, [reset]);
  //////////////////////////////////////////////////////////

  const handleScroll = (e) => {
    const bottom =
      e.target.scrollHeight - e.target.scrollTop === e.target.clientHeight;
    if (bottom) setPage((prevPage) => prevPage + 1);
  };

  const getAllComments = async () => {
    await boardApi
      .getVideoBoardCommentList(storeId, videoId)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          if (storeId === -1) {
            setResult([..._result]);
            setLoading(false);
          } else {
            if (_result.length === 0) {
            } else {
              setResult((prev) => [...prev, ..._result]);
              setLoading(false);
            }
          }

          let _storId =
            _result.length === 0 ? -1 : _result[_result.length - 1].commentId;

          console.log("storeId ??? ", _storId);
          setStoreId(_storId);
          setIsFetching(false);
        }
      })
      .catch((error) => console.log(error));
  };

  const [isFetching, setIsFetching] = useInfiniteScroll(getAllComments);

  return (
    <S.Container onScroll={handleScroll}>
      {result && result.length > 0 ? (
        result.map((item) => (
          <BoardComment
            key={item.commentId}
            nickname={item.nickname}
            content={item.content}
            createdDate={item.createdDate}
          />
        ))
      ) : (
        <S.Message>아직 등록한 댓글이 없어요:!</S.Message>
      )}
      {/* {loading && <div>Loading...</div>} */}
    </S.Container>
  );
};

export default BoardCommentList;
BoardCommentList.propTypes = {
  result: PropTypes.array,
  getAllComments: PropTypes.func,
};
