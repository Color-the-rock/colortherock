import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
import RegistBtn from "../../../components/Board/RegistBtn";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import Thumbnail from "../../../components/Common/Thumbnail";
import CustomCalendar from "../../../components/Board/CustomCalendar";
import { recordApi } from "../../../api/record";

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

const UploadS3Form = () => {
  const [data, setData] = useState([]);
  const [selectDate, setSelectDate] = useState("");

  const navigate = useNavigate();

  // 날짜가 바뀔 때마다 영상이 바뀌도록....
  useEffect(() => {
    if (!selectDate) return;

    const params = {
      videoId: -1,
      shootingDate: selectDate,
      isSuccess: true,
    };
    recordApi
      .getAllRecordVideo(params)
      .then((res) => {
        console.log("영상불러오기 성공");
        setData(res);
      })
      .catch((err) => {
        console.log("영상불러오기 실패");
      });
  }, [selectDate]);

  const clickHandler = () => {
    navigate("/board");
  };

  // 영상 클릭시 confirm 창 띄우고 확인을 누르면 라우터에 state를 담아서 보낸다.
  const onClick = (e) => {
    if (window.confirm("선택하신 영상을 등록하시겠습니까?")) {
      // 확인을 누른 후, state를 내려보내준다.
      // navigate("/board/regist", { state: true, id: e.target.value });
    }
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <ArrowLeftBtn clickHandler={clickHandler}></ArrowLeftBtn>
        {/* <S.TitleWrap>영상을 선택해주세요.</S.TitleWrap> */}
        {/* <div>영상을 선택해주세요.</div> */}
        <S.CalendarWrap>
          <CustomCalendar
            placeholder="원하시는 날짜를 선택해주세요."
            selectDate={selectDate}
            setSelectDate={setSelectDate}
          />
        </S.CalendarWrap>
        {data && (
          <S.ThumbnailList>
            {data.map((item) => (
              <Thumbnail
                key={item.id}
                onClick={onClick}
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
        )}
        {/* <S.ComponentWrap>
              <RegistBtn btnName="등록하기" clickHandler={submitHandler} />
            </S.ComponentWrap> */}
      </S.Container>
    </S.ContainerWrap>
  );
};

export default UploadS3Form;
