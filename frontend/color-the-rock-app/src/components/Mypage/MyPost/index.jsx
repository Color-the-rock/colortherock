import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { myPageApi } from "../../../api/mypage";
import Video from "../Video";
import * as S from "./style";

const MyPost = () => {
  const [result, setResult] = useState([]);
  const navigate = useNavigate();
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

  const handleOnClickVideo = (id) => {
    navigate(`/board/detail/${id}`);
  };

  return result && result.length > 0 ? (
    <S.VideoList>
      {result.map((item, index) => (
        <Video
          id={item.videoBoardId}
          key={item.videoBoardId + index}
          title={item.title}
          color={item.color}
          gymName={item.gymName}
          createdDate={item.createdDate}
          thumbnailURL={item.thumbnailURL}
          isMyPage={true}
          onClick={() => handleOnClickVideo(item.videoBoardId)}
        />
      ))}
    </S.VideoList>
  ) : (
    <S.Message>아직 작성한 게시글이 없어요!</S.Message>
  );
};

export default MyPost;
