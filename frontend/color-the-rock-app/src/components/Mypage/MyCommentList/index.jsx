import React, { useEffect, useState } from "react";
import { myPageApi } from "../../../api/mypage";
import Loading from "../../Common/Loading";
import MyComment from "../MyComment";
import * as S from "./style";

const MyCommentList = () => {
  const [result, setResult] = useState([]);
  const [storeId, setStoreId] = useState(-1);
  const [isLoading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    myPageApi
      .getMyCommentList(storeId)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
          let _storeId =
            _result.length === 0 ? -1 : _result[_result.length - 1].commentId;
          setStoreId(_storeId);
        }
      })
      .catch((error) => console.log(error))
      .finally(() => {
        setLoading(false);
      });
  }, []);

  return isLoading ? (
    <Loading />
  ) : (
    <S.Container>
      {result && result.length > 0 ? (
        result.map((item) => (
          <MyComment
            key={item.videoBoardId + item.commentId}
            title={item.title}
            nickname={item.nickname}
            content={item.content}
            createdDate={item.createdDate}
            videoBoardId={item.videoBoardId}
          />
        ))
      ) : (
        <S.Message>아직 등록한 댓글이 없어요:!</S.Message>
      )}
    </S.Container>
  );
};

export default MyCommentList;
