import React, { useEffect, useState } from "react";
import { myPageApi } from "../../../api/mypage";
import Video from "../Video";
import * as S from "./style";

const MyPost = () => {
  const [result, setResult] = useState([]);
  useEffect(() => {
    myPageApi
      .getMyBoardList()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
        }
      })
      .catch((error) => console.log(error));
  }, []);

  return result && result.length > 0 ? (
    result.map((item) => (
      <S.VideoList>
        <Video
          key={item.sources}
          title={item.title}
          color={item.color}
          thumb={item.thumb}
          sources={item.sources}
        />
      </S.VideoList>
    ))
  ) : (
    <S.Message>아직 작성한 게시글이 없어요!</S.Message>
  );
};

export default MyPost;
