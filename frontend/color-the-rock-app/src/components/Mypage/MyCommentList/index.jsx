import React, { useEffect, useState } from "react";
import { myPageApi } from "../../../api/mypage";
import MyComment from "../MyComment";
import * as S from "./style";
const MyCommentList = () => {
  const [result, setResult] = useState([]);

  useEffect(() => {
    console.log("mypage API");
    myPageApi
      .getMyCommentList("79")
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
        }
      })
      .catch((error) => console.log(error));
  }, []);

  return (
    <S.Container>
      {result && result.length > 0 ? (
        result.map((item) => (
          <MyComment
            key={item.videoBoardId + item.commentId}
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

export default MyCommentList;
