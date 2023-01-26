import React from "react";
import MyComment from "../MyComment";
import * as S from "./style";
const MyCommentList = () => {
  const data = [
    {
      id: 1,
      title: "나는야 블루왕",
      content: "우와, 정말 신기하네요!",
      createdDate: "2023-01-18",
    },
    {
      id: 2,
      title: "오늘 강남 더클라이밍",
      content: "우와, 너무 대단해요:)",
      createdDate: "2023-01-20",
    },
    {
      id: 3,
      title: "레벨3 완등 완료!",
      content: "우와, 그거 정말 멋지네요!",
      createdDate: "2023-01-22",
    },
    {
      id: 4,
      title: "오늘도 강남",
      content: "우와, 너무 대단해요:)",
      createdDate: "2023-01-23",
    },
    {
      id: 5,
      title: "레벨3 완료!",
      content: "우와, 그거 정말 멋지네요!",
      createdDate: "2023-01-22",
    },
  ];
  return (
    <S.Container>
      {data && data.length > 0 ? (
        data.map((item) => (
          <MyComment
            key={item + item.id}
            title={item.title}
            content={item.content}
            createdDate={item.createdDate}
          />
        ))
      ) : (
        <label>아직 등록한 댓글이 없어요:!</label>
      )}
    </S.Container>
  );
};

export default MyCommentList;
