import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import Thumbnail from "../../../components/Common/Thumbnail";
import CustomCalendar from "../../../components/Board/CustomCalendar";
import { recordApi } from "../../../api/record";
import Title from "../../../components/Common/Title";
import moment from "moment";

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
        <S.CalendarWrap>
          <CustomCalendar
            placeholder={moment(new Date()).format("YYYY.MM.DD.")}
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
