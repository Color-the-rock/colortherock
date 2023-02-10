import React, { useEffect } from "react";
import BoardComment from "../BoardComment";
import PropTypes from "prop-types";
import * as S from "./style";
const BoardCommentList = ({ result, getAllComments }) => {
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
BoardCommentList.propTypes = {
  result: PropTypes.array,
  getAllComments: PropTypes.func,
};
