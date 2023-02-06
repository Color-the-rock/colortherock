import React, { useEffect } from "react";
import Thumbnail from "../../components/Common/Thumbnail";
import SearchBar from "../../components/Common/Search";
import Title from "../../components/Common/Title";
import * as S from "./style";
import { HiOutlineVideoCamera } from "react-icons/hi";
import streamingApi from "../../api/streaming";
import { useDispatch, useSelector } from "react-redux";
import { setOpenViduToken } from "../../stores/streaming/streamingSlice";
import { useNavigate } from "react-router";
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
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const token = useSelector((state) => state.streaming.userOpenViduToken);

  const test = () => {
    streamingApi
      .participateLiveSession("ses_MfqaNOvd1w")
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          dispatch(setOpenViduToken(result));
          navigate(`/streaming/live/ses_MfqaNOvd1w`);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    console.log("참여자 토큰: ", token);
  }, [token]);

  return (
    <S.Container>
      <Title text="실시간 도전">
        <S.LiveTag onClick={test}>LIVE</S.LiveTag>
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
      <S.LiveButton to="/streaming/form">
        <HiOutlineVideoCamera size="24px" color="#C250D6" />
      </S.LiveButton>
    </S.Container>
  );
};
export default Streaming;
