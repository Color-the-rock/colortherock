import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
import RegistBtn from "../../../components/Board/RegistBtn";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import Thumbnail from "../../../components/Common/Thumbnail";
import CustomCalendar from "../../../components/Board/CustomCalendar";
import { recordApi } from "../../../api/record";
import Title from "../../../components/Common/Title";

const dummy = [
  {
    videoId: 1,
    title: "실시간 클라이밍 중",
    userNickname: "공싸피",
    gymName: "강남 더 클라이밍",
    thumbnailURL: "",
    color: "빨강",
    level: 1,
  },
  {
    videoId: 2,
    title: "실시간 클라이밍 진행",
    userNickname: "김싸피",
    gymName: "역삼 더 클라이밍",
    thumbnailURL: "",
    color: "노랑",
    level: 1,
  },
  {
    videoId: 3,
    title: "초보 클라이밍",
    userNickname: "송싸피",
    gymName: "홍대 더 클라이밍",
    thumbnailURL: "",
    color: "초록",
    level: 1,
  },
  {
    videoId: 4,
    title: "클라이밍 마스터",
    userNickname: "최싸피",
    gymName: "인천 더 클라이밍",
    thumbnailURL: "",
    color: "파랑",
    level: 1,
  },
  {
    videoId: 5,
    title: "실시간 클라이밍 중",
    userNickname: "허싸피",
    gymName: "신림 더 클라이밍",
    thumbnailURL: "",
    color: "보라",
    level: 1,
  },
];

const UploadS3Form = () => {
  const [result, setResult] = useState([]);
  const [selectDate, setSelectDate] = useState("");

  const navigate = useNavigate();

  // 날짜가 바뀔 때마다 영상이 바뀌도록....
  useEffect(() => {
    if (!selectDate) return;

    const params = {
      videoId: 1,
      shootingDate: selectDate,
      isSuccess: true,
    };
    recordApi
      .getAllRecordVideo(params)
      .then((res) => {
        console.log("영상불러오기 성공");
        console.log(res.data.result);
        setResult(res.data.result);
      })
      .catch((err) => {
        console.log("영상불러오기 실패");
      });
  }, [selectDate]);

  const clickHandler = () => {
    navigate("/board");
  };

  // 영상 클릭시 confirm 창 띄우고 확인을 누르면 라우터에 state를 담아서 보낸다.
  const onClick = (value) => {
    if (window.confirm("선택하신 영상을 등록하시겠습니까?")) {
      console.log(value);
      navigate("/board/form", {
        state: {
          state: true,
          id: value,
        },
      });
    }
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <ArrowLeftBtn clickHandler={clickHandler}></ArrowLeftBtn>
        <Title>완등 영상 등록</Title>
        {/* <S.TitleWrap>원하시는 날짜의 영상을 선택해주세요.</S.TitleWrap> */}

        <S.CalendarWrap>
          <CustomCalendar
            placeholder="원하시는 날짜를 선택해주세요."
            selectDate={selectDate}
            setSelectDate={setSelectDate}
          />
        </S.CalendarWrap>
        {result && result.length > 0 ? (
          <S.ThumbnailList>
            {result.map((item) => (
              <Thumbnail
                key={item.videoId}
                id={item.videoId}
                onClick={onClick}
                gymName={item.gymName}
                imgUrl={item.thumbnailURL}
                isLive={false}
                color={item.color}
              />
            ))}
          </S.ThumbnailList>
        ) : (
          <S.Message>등록된 게시글이 없어요!</S.Message>
        )}
      </S.Container>
    </S.ContainerWrap>
  );
};

export default UploadS3Form;
