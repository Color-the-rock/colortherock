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
import Loading from "../../components/Common/Loading";
import useInfiniteScroll from "../../hooks/useInfiniteScroll";

const Streaming = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const isLogin = useSelector((state) => state.users.isLogin);
  const [result, setResult] = useState([]);
  const [isLoading, setLoading] = useState(false);
  const [storeId, setStoreId] = useState(-1);
  const [searchValue, setSearchValue] = useState("");

  // openVidu 설정
  const joinSession = () => {
    console.log("joinSession");
    const ov = new OpenVidu();
    dispatch(setOV({ ov }));
  };

  const handleParticipateSession = (sessionId) => {
    if (!isLogin) {
      alert("로그인이 필요한 서비스입니다:)");
      return;
    }

    setLoading(true);
    joinSession();
    streamingApi
      .participateLiveSession(sessionId)
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          dispatch(setOpenViduToken(result));
          navigate(`/streaming/live`);
        }
      })
      .catch(() => {
        alert("이미 종료된 방송입니다.");
        navigate(`/streaming`);
      })
      .finally(() => {
        setTimeout(() => setLoading(false), 200);
      });
  };

  const getAllLiveList = async () => {
    setLoading(true);
    await streamingApi
      .getAllLiveList(storeId, searchValue)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          if (storeId === -1) {
            setResult([..._result]);
          } else {
            if (_result.length === 0) {
            } else {
              setResult((prev) => [...prev, ..._result]);
            }
          }
          if (_result.length !== 0) {
            let lastId =
              _result[_result.length - 1].id === undefined
                ? -1
                : _result[_result.length - 1].id;
            setStoreId(lastId);
          }
        }
      })
      .catch((error) => console.log(error))
      .finally(() => {
        setTimeout(() => setLoading(false), 200);
        setIsFetching(false);
      });
  };

  const handleOnClickCreateLive = () => {
    if (isLogin) {
      navigate("/streaming/form");
    } else {
      alert("로그인 후, 이용해주세요!");
    }
  };

  useEffect(() => {
    setStoreId(-1);
    getAllLiveList();
  }, [searchValue]);

  const [isFetching, setIsFetching] = useInfiniteScroll(getAllLiveList);

  return (
    <S.Container>
      <Title text="실시간 도전">
        <S.LiveTag>LIVE</S.LiveTag>
      </Title>
      <S.Description>
        도전 중인 등반을 보고 실시간으로 피드백해줘요!
      </S.Description>
      <SearchBar
        setSearchValue={setSearchValue}
        getAllLiveList={getAllLiveList}
      />
      {isLoading && <Loading />}
      {result && result.length > 0 ? (
        <S.ThumbnailList>
          {result.map((item) => (
            <Thumbnail
              key={item.sessionId}
              sessionId={item.sessionId}
              title={item.title}
              userNickname={item.memberName}
              gymName={item.gymName}
              imgUrl={item.thumbnailUrl}
              isLive={true}
              participantNum={item.participantNum}
              onClick={handleParticipateSession}
            />
          ))}
        </S.ThumbnailList>
      ) : (
        <S.Message>진행중인 방송이 없어요!</S.Message>
      )}
      <S.LiveButton onClick={handleOnClickCreateLive}>
        <HiOutlineVideoCamera size="24px" color="#C250D6" />
      </S.LiveButton>
    </S.Container>
  );
};
export default Streaming;
