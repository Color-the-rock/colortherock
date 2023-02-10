/*
  RecordVideoFormModal
  : 실시간 스트리밍 중에 영상을 녹화한 후 띄워지는 Modal
  
  props
  : video => 녹화한 영상
  : handleModalStateChange => Modal on/off

  할 일
  : 등록요청
  : video validation check
  : 상태 관리
*/

import * as S from "./style";
import React, { useState } from "react";
import BoardRadioBtn from "../../Board/BoardRadioBtn";
import CustomSelect from "../../Common/CustomSelect";
import RegistBtn from "../../Board/RegistBtn";

import streamingApi from "../../../api/streaming";
import InputComp from "../../../components/Board/InputComp";
import { useSelector } from "react-redux";

const levelValues = [
  { key: "난이도 레벨", value: "" },
  { key: "LEVEL1", value: "1" },
  { key: "LEVEL2", value: "2" },
  { key: "LEVEL3", value: "3" },
  { key: "LEVEL4", value: "4" },
  { key: "LEVEL5", value: "5" },
  { key: "LEVEL6", value: "6" },
  { key: "LEVEL7", value: "7" },
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
const RecordVideoFormModal = ({ sessionId, recordingId, setModalOpen }) => {
  const saveTitle = useSelector((state) => state.streaming.info.title);
  const saveGymName = useSelector((state) => state.streaming.info.gymName);
  const [isSuccess, setIsSuccess] = useState(true);
  const [title, setTitle] = useState(saveTitle);
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");

  // test 용 : video는 props로 받아와야함.

  const handleModalStateChange = () => {
    if (window.confirm("정말로 취소하시겠습니까?")) {
      setModalOpen();
    }
  };

  const registVideoToS3 = () => {
    if (!color || !level || !isSuccess) {
      alert("모든 항목을 채워주세요.");
      return;
    }

    const data = {
      recordingId,
      isSaved: true,
      level,
      title,
      gymName: saveGymName,
      isSuccess,
      color,
    };
    console.log("---------------------------------");
    console.log(data);
    console.log("---------------------------------");

    streamingApi
      .saveRecordVideo(sessionId, JSON.stringify(data))
      .then(() => {
        console.log("성공");
        setModalOpen();
      })
      .catch((err) => {
        console.log("실패");
        console.log("err: ", err);
      });
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentBox>
          <S.ComponentWrap>
            <div>영상 등록</div>
          </S.ComponentWrap>
          <S.ComponentWrap>
            <InputComp
              title={title}
              handleChange={setTitle}
              placeholder="제목을 입력해주세요."
            />
          </S.ComponentWrap>
          <S.SelectButtonWrap>
            <S.selectBtnContent>
              <CustomSelect setter={setLevel} optionValues={levelValues} />
              <CustomSelect setter={setColor} optionValues={colorValues} />
            </S.selectBtnContent>
          </S.SelectButtonWrap>

          <S.ComponentWrap>
            <BoardRadioBtn isPublic={isSuccess} setIsPublic={setIsSuccess} />
          </S.ComponentWrap>
          <S.ComponentWrap>
            <RegistBtn btnName="등록" clickHandler={registVideoToS3} />
          </S.ComponentWrap>
          <S.ComponentWrap>
            <RegistBtn btnName="취소" clickHandler={handleModalStateChange} />
          </S.ComponentWrap>
        </S.ContentBox>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default React.memo(RecordVideoFormModal);
