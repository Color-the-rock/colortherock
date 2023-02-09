import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import boardApi from "../../../api/board";
import BoardComment from "../BoardComment";
import * as S from "./style";
const BoardCommentList = () => {
  const [result, setResult] = useState([]);
  const [storeId, setStoreId] = useState(-1);

  const { id } = useParams();

  const getAllComments = () => {
    boardApi
      .getVideoBoardCommentList(storeId, id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
          setStoreId(_result[_result.length - 1].commentId);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getAllComments();
  }, []);

  return (
    <S.Container>
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
    </S.Container>
  );
};

export default BoardCommentList;
