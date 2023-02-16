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
import Loading from "../../../components/Common/Loading";

const levelValues = [
  { key: "난이도 레벨", value: "" },
  { key: "LEVEL1", value: "1" },
  { key: "LEVEL2", value: "2" },
  { key: "LEVEL3", value: "3" },
  { key: "LEVEL4", value: "4" },
  { key: "LEVEL5", value: "5" },
  { key: "LEVEL6", value: "6" },
  { key: "LEVEL7", value: "7" },
  { key: "LEVEL8", value: "8" },
  { key: "LEVEL9", value: "9" },
];
const colorValues = [
  { key: "난이도 색상", value: "" },
  { key: "흰색", value: "흰색" },
  { key: "빨강", value: "빨강" },
  { key: "주황", value: "주황" },
  { key: "노랑", value: "노랑" },
  { key: "연두", value: "연두" },
  { key: "초록", value: "초록" },
  { key: "하늘", value: "하늘" },
  { key: "파랑", value: "파랑" },
  { key: "남색", value: "남색" },
  { key: "보라", value: "보라" },
  { key: "핑크", value: "핑크" },
  { key: "검정", value: "검정" },
  { key: "갈색", value: "갈색" },
  { key: "회색", value: "회색" },
];

const RecordForm = () => {
  const navigate = useNavigate();

  const [video, setVideo] = useState();
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [location, setLocation] = useState("");
  const [selectDate, setSelectDate] = useState("");
  const [isSuccess, setIsSuccess] = useState(true);
  const [isLoading, setLoading] = useState(false);

  const clickHandler = () => {
    navigate("/record");
  };

  const submitHandler = () => {
    if (!video || !level || !color || !location || !selectDate) {
      alert("모든 항목을 채워주세요.");
      return;
    }

    setLoading(true);

    const data = {
      shootingDate: selectDate,
      level,
      color,
      gymName: location,
      isSuccess,
    };

    const formData = new FormData();
    const blob = new Blob([JSON.stringify(data)], { type: "application/json" });

    formData.append("uploadVideoRequest", blob);
    formData.append("newVideo", video);

    recordApi
      .uploadLocalVideo(formData)
      .then((res) => {
        navigate("/record");
      })
      .catch((err) => {
        console.log("err: ", err);
      })
      .finally(() => setLoading(false));
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
              <UploadForm
                isLoading={isLoading}
                video={video}
                setVideo={setVideo}
              ></UploadForm>
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
          {isLoading && <Loading />}
        </S.ContentWrap>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default RecordForm;
