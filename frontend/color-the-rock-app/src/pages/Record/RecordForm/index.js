import React, { useState } from "react";
import * as S from "./style";
import { Desktop, Mobile } from "../../../components/layout/Template";
import Header from "../../../components/layout/Header";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import UploadForm from "../../../components/Board/UploadForm";
import KakaoKeywordSearch from "../../../components/Common/KakaoKeywordSearch/SearchBar";
import RegistBtn from "../../../components/Board/RegistBtn";
import { useNavigate } from "react-router-dom";
import CustomSelect from "../../../components/Common/CustomSelect";
import CustomCalendar from "../../../components/Board/CustomCalendar";

import "react-datepicker/dist/react-datepicker.css";
import "react-datepicker/dist/react-datepicker-cssmodules.css";

import { ko } from "date-fns/esm/locale";
import BoardRadioBtn from "../../../components/Board/BoardRadioBtn/index";

// ------------- test ----------------------//
import RecordVideoFormModal from "../../../components/Streaming/RecordVideoFormModal";
import ModifyRoomSettingModal from "../../../components/Streaming/ModifyRoomSettingModal";
import FeedbackModal from "../../../components/Streaming/FeedbackModal";
// ----------------------------------------------------------------- //

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
  const [title, setTitle] = useState();
  const [location, setLocation] = useState("");
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  // const [startDate, setStartDate] = useState(new Date());
  const [selectDate, setSelectDate] = useState("");
  const [isSuccess, setIsSuccess] = useState(true);

  /////////////////////////// test ////////////////////////////////
  const [modalOpen, setModalOpen] = useState(false);
  const [modalOpen2, setModalOpen2] = useState(false);
  const [modalOpen3, setModalOpen3] = useState(false);

  const handleModalStateChange = () => {
    setModalOpen((prev) => !prev);
  };

  const handleModalStateChange2 = () => {
    setModalOpen2((prev) => !prev);
  };

  const handleModalStateChange3 = () => {
    setModalOpen3((prev) => !prev);
  };
  //////////////////////////////////////////////////////////////////

  console.log("selectDate: ", selectDate);
  console.log(typeof selectDate);
  const clickHandler = () => {
    navigate("/record");
  };

  const submitHandler = () => {
    navigate("/record");
  };

  const check = () => {
    const val = document.getElementById("dd");
    console.log(val.value);
  };
  const handler = (e) => {
    // console.log(e.target.value);
    setSelectDate(e.target.value);
    // setStartDate(e.target.value);
    console.log(selectDate);
  };

  return (
    <S.ContainerWrap>
      {/* ---------------------모달 테스트 중입니다.-------------------- */}
      {modalOpen && (
        <RecordVideoFormModal
          onClick={handleModalStateChange}
          setModalOpen={setModalOpen}
        />
      )}
      {modalOpen2 && (
        <FeedbackModal
          onClick={handleModalStateChange2}
          setModalOpen={setModalOpen2}
        />
      )}
      {modalOpen3 && (
        <RecordVideoFormModal
          onClick={handleModalStateChange3}
          setModalOpen={setModalOpen3}
        />
      )}

      {/* ------------------------------------------------------------- */}
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
              <UploadForm></UploadForm>
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
                <CustomSelect optionValues={levelValues} />
                <CustomSelect optionValues={colorValues} />
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
              <button
                onClick={handleModalStateChange}
                style={{
                  fontSize: "20px",
                  color: "white",
                  border: "1px solid white",
                  margin: "1rem",
                }}
              >
                영상 등록 모달
              </button>
              <button
                onClick={handleModalStateChange2}
                style={{
                  fontSize: "20px",
                  color: "white",
                  border: "1px solid white",
                  margin: "1rem",
                }}
              >
                영상 수정 모달
              </button>
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
