import React from "react";
import Thumbnail from "../../components/Common/Thumbnail";
import SearchBar from "../../components/Common/Search";
import Title from "../../components/Common/Title";
import * as S from "./style";
import { HiOutlineVideoCamera } from "react-icons/hi";
const dummy = [
  {
    id: 1,
    title: "실시간 클라이밍 중",
    userNickname: "공싸피",
    gymName: "강남 더 클라이밍",
    imgUrl: "",
  },
  {
    id: 2,
    title: "실시간 클라이밍 진행",
    userNickname: "김싸피",
    gymName: "역삼 더 클라이밍",
    imgUrl: "",
  },
  {
    id: 3,
    title: "초보 클라이밍",
    userNickname: "송싸피",
    gymName: "홍대 더 클라이밍",
    imgUrl: "",
  },
  {
    id: 4,
    title: "클라이밍 마스터",
    userNickname: "최싸피",
    gymName: "인천 더 클라이밍",
    imgUrl: "",
  },
  {
    id: 5,
    title: "실시간 클라이밍 중",
    userNickname: "허싸피",
    gymName: "신림 더 클라이밍",
    imgUrl: "",
  },
];

const Streaming = () => {
  return (
    <S.Container>
      <Title text="실시간 도전">
        <S.LiveTag>LIVE</S.LiveTag>
      </Title>
      <S.Description>
        도전 중인 등반을 보고 실시간으로 피드백해줘요!
      </S.Description>
      <SearchBar />
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
              isLive={true}
            />
          ))}
        </S.ThumbnailList>
      ) : (
        <label>진행중인 방송이 없어요!</label>
      )}
      <S.LiveButton to="/streaming/regist">
        <HiOutlineVideoCamera size="24px" color="#C250D6" />
      </S.LiveButton>
    </S.Container>
  );
};
export default Streaming;
