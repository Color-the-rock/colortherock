import React from "react";
import Video from "../Video";
import * as S from "./style";
const MyPost = () => {
  const data = [
    {
      id: 1,
      title: "제목입니다!1",
      color: "빨강",
    },
    {
      id: 2,
      title: "제목입니다!3",
      color: "파랑",
    },
    {
      id: 3,
      title: "제목입니다!4",
      color: "노랑",
    },
    {
      id: 4,
      title: "제목입니다!4",
      color: "초록",
    },
    {
      id: 5,
      title: "제목입니다!5",
      color: "보라",
    },
  ];
  return (
    <S.VideoList>
      {data &&
        data.map((item) => (
          <Video key={item.id} title={item.title} color={item.color} />
        ))}
    </S.VideoList>
  );
};

export default MyPost;
