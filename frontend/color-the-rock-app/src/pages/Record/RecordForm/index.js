import React, { useState } from "react";
import * as S from "./style";
import { useNavigate } from "react-router-dom";

import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import UploadForm from "../../../components/Board/UploadForm";
import KakaoKeywordSearch from "../../../components/Common/KakaoKeywordSearch/SearchBar";
import RegistBtn from "../../../components/Board/RegistBtn";
import CustomSelect from "../../../components/Common/CustomSelect";
import CustomCalendar from "../../../components/Board/CustomCalendar";
import BoardRadioBtn from "../../../components/Board/BoardRadioBtn/index";
import { recordApi } from "../../../api/record";

const levelValues = [
  { key: "난이도 레벨", value: "" },
  { key: "LEVEL1", value: "level-1" },
  { key: "LEVEL2", value: "level-2" },
  { key: "LEVEL3", value: "level-3" },
  { key: "LEVEL4", value: "level-4" },
  { key: "LEVEL5", value: "level-5" },
  { key: "LEVEL6", value: "level-6" },
  { key: "LEVEL7", value: "level-7" },
];

const colorValues = [
  { key: "난이도 색상", value: "" },
  { key: "빨강", value: "red" },
  { key: "주황", value: "orange" },
  { key: "노랑", value: "yellow" },
  { key: "연두", value: "green" },
  { key: "하늘", value: "skyBlue" },
  { key: "남색", value: "indigo" },
  { key: "보리", value: "purple" },
  { key: "검정", value: "black" },
];

const RecordForm = () => {
  const navigate = useNavigate();

  const [video, setVideo] = useState();
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [location, setLocation] = useState("");
  const [selectDate, setSelectDate] = useState("");
  const [isSuccess, setIsSuccess] = useState(true);

  const clickHandler = () => {
    navigate("/record");
  };

  const submitHandler = () => {
    if (!video || !level || !color || !location || !selectDate || !isSuccess) {
      alert("빈칸채워라  등록안해준다.");
      return;
    }

    const data = {
      level,
      color,
      gymName: location,
      shootingTime: selectDate,
      isSuccess,
    };

    const json = JSON.stringify(data);
    const blob = new Blob([json], { type: "application/json" });

    const formData = new FormData();
    formData.append("uploadVideoRequest", blob);
    formData.append("newVideo", video);

    // recordApi.uploadLocalVideo(formData)
    // .then((res) => {
    //   console.log("res", res);
    //   console.log("성공");
    // })
    // .catch((err) => {
    //   console.log("err: ", err);
    //   console.log("실패");
    // });

    navigate("/record");
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentWrap>
          <S.Content>
            <S.ArrowLeftBtnWrap>
              <ArrowLeftBtn clickHandler={clickHandler}></ArrowLeftBtn>
            </S.ArrowLeftBtnWrap>
            <S.ComponenentWrap>
              <BoardSubTitle text="동영상" />
            </S.ComponenentWrap>

            <S.ComponenentWrap>
              <UploadForm video={video} setVideo={setVideo}></UploadForm>
            </S.ComponenentWrap>

            <S.ComponenentWrap>
              <BoardSubTitle text="글등록" />
            </S.ComponenentWrap>

            <S.ComponenentWrap>
              <CustomCalendar
                selectDate={selectDate}
                setSelectDate={setSelectDate}
              />
            </S.ComponenentWrap>

            <S.SelectButtonWrap>
              <S.selectBtnContent>
                <CustomSelect setter={setLevel} optionValues={levelValues} />
                <CustomSelect setter={setColor} optionValues={colorValues} />
              </S.selectBtnContent>
            </S.SelectButtonWrap>

            <S.ComponenentWrap>
              {/* 라디오 버튼 만들어보자 */}
              <BoardRadioBtn
                isPublic={isSuccess}
                setIsPublic={setIsSuccess}
              ></BoardRadioBtn>
            </S.ComponenentWrap>

            <S.ComponenentWrap>
              <KakaoKeywordSearch
                location={location}
                setLocation={setLocation}
              />
            </S.ComponenentWrap>

            <S.ComponenentWrap>
              <RegistBtn btnName="등록하기" clickHandler={submitHandler} />
            </S.ComponenentWrap>
          </S.Content>
        </S.ContentWrap>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default RecordForm;
