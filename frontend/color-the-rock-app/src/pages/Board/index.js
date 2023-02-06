import React, { useState } from "react";
import Thumbnail from "../../components/Common/Thumbnail";
import Title from "../../components/Common/Title";
import * as S from "./style";
import { HiPencil } from "react-icons/hi";
import BoardSearchBar from "../../components/Board/BoardSearch";
const dummy = [
  {
    id: 1,
    title: "실시간 클라이밍 중",
    userNickname: "공싸피",
    gymName: "강남 더 클라이밍",
    imgUrl: "",
    color: "빨강",
  },
  {
    id: 2,
    title: "실시간 클라이밍 진행",
    userNickname: "김싸피",
    gymName: "역삼 더 클라이밍",
    imgUrl: "",
    color: "노랑",
  },
  {
    id: 3,
    title: "초보 클라이밍",
    userNickname: "송싸피",
    gymName: "홍대 더 클라이밍",
    imgUrl: "",
    color: "초록",
  },
  {
    id: 4,
    title: "클라이밍 마스터",
    userNickname: "최싸피",
    gymName: "인천 더 클라이밍",
    imgUrl: "",
    color: "파랑",
  },
  {
    id: 5,
    title: "실시간 클라이밍 중",
    userNickname: "허싸피",
    gymName: "신림 더 클라이밍",
    imgUrl: "",
    color: "보라",
  },
];

const Board = () => {
  const [searchLocation, setSearchLocation] = useState("");
  return (
    <S.Container>
      <Title>완등 영상 보기</Title>
      <S.Description>완등 영상을 게시하고 피드백을 받아보세요!</S.Description>
      <BoardSearchBar
        searchLocation={searchLocation}
        setSearchLocation={setSearchLocation}
      />
      {/* list */}
      {dummy && dummy.length > 0 ? (
        <S.ThumbnailList>
          {dummy.map((item) => (
            <Thumbnail
              key={item.id}
              id={item.id}
              title={item.title}
              userNickname={item.userNickname}
              gymName={item.gymName}
              imgUrl={item.imgUrl}
              isLive={false}
              color={item.color}
            />
          ))}
        </S.ThumbnailList>
      ) : (
        <label>진행중인 방송이 없어요!</label>
      )}
      <S.LiveButton to="/board/regist">
        <HiPencil size="24px" color="#C250D6" />
      </S.LiveButton>
    </S.Container>
  );
};
export default Board;
