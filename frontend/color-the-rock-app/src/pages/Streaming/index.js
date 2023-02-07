import React, { useEffect, useState } from "react";
import Thumbnail from "../../components/Common/Thumbnail";
import SearchBar from "../../components/Common/Search";
import Title from "../../components/Common/Title";
import * as S from "./style";
import { HiOutlineVideoCamera } from "react-icons/hi";
import streamingApi from "../../api/streaming";
import { useDispatch, useSelector } from "react-redux";
import { setOpenViduToken, setOV } from "../../stores/streaming/streamingSlice";
import { useNavigate } from "react-router";
import { OpenVidu } from "openvidu-browser";

const Streaming = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const token = useSelector((state) => state.streaming.userOpenViduToken);
  const [result, setResult] = useState([]);

  // openVidu 설정
  const joinSession = () => {
    console.log("joinSession");
    const ov = new OpenVidu();
    dispatch(setOV({ ov }));
  };

  const handleParticipateSession = () => {
    joinSession();
    streamingApi
      .participateLiveSession("ses_C1O14q5DIs")
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          dispatch(setOpenViduToken(result));
          navigate(`/streaming/live/ses_C1O14q5DIs`);
        }
      })
      .catch((error) => console.log(error));
  };

  const getAllLiveList = () => {
    streamingApi
      .getAllLiveList()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200 ", _result);
          setResult(_result);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getAllLiveList();
  }, []);

  useEffect(() => {
    console.log("참여자 토큰: ", token);
  }, [token]);

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
      {result && result.length > 0 ? (
        <S.ThumbnailList>
          {result.map((item) => (
            <Thumbnail
              key={item.id}
              id={item.id}
              title={item.title}
              userNickname={item.userNickname}
              gymName={item.gymName}
              imgUrl={item.imgUrl}
              isLive={true}
              onClick={handleParticipateSession(item.id)}
            />
          ))}
        </S.ThumbnailList>
      ) : (
        <S.Message>진행중인 방송이 없어요!</S.Message>
      )}
      <S.LiveButton to="/streaming/form">
        <HiOutlineVideoCamera size="24px" color="#C250D6" />
      </S.LiveButton>
    </S.Container>
  );
};
export default Streaming;
