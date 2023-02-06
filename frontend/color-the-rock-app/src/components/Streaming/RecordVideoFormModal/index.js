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
import React, { useState, useEffect } from "react";
import BoardRadioBtn from "../../Board/BoardRadioBtn";
import CustomSelect from "../../Common/CustomSelect";
import UploadForm from "../../Board/UploadForm";
import RegistBtn from "../../Board/RegistBtn";
import { registfuc } from "../../../api/streaming";
import { Form, useNavigate } from "react-router-dom";
import BoardApi from "../../../api/board";

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

const RecordVideoFormModal = ({ video = null, setModalOpen }) => {
  const [isSuccess, setIsSuccess] = useState(true);
  const [level, setLevel] = useState("빨강");
  const [color, setColor] = useState("초록");
  console.log("level: ", level);
  console.log("color: ", color);
  const handleModalStateChange = () => {
    if (window.confirm("정말로 취소하시겠습니까?")) {
      setModalOpen((prev) => !prev);
    }
  };

  const registVideoToS3 = () => {
    let formData = new FormData();
    const localSuccessVideoUploadRequest = {
      color,
      level,
      isSuccess,
    };
    formData.append("newVideo", "file");
    formData.append(
      "localSuccessVideoUploadRequest",
      JSON.stringify(localSuccessVideoUploadRequest)
    );

    console.log("잘만든건가?");
    console.log("formData file: ", formData.get("newVideo"));

    console.log(
      "formData data: ",
      formData.get("localSuccessVideoUploadRequest")
    );

    const data = {
      storeId: 0,
      color: "red",
      gymName: "bouldering",
    };

    BoardApi.postRegisterLocalVideo(FormData)
      .then(() => {
        console.log("성공");
        setModalOpen((prev) => !prev);
      })
      .catch((err) => {
        console.log("실패");
        console.log("err: ", err);
        console.log("formData: ", formData);
      });
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentBox>
          <S.ComponentWrap>
            <UploadForm video={video}></UploadForm>
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
