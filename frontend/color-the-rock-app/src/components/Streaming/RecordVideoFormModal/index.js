import * as S from "./style";
import React, { useState } from "react";
import BoardRadioBtn from "../../Board/BoardRadioBtn";
import CustomSelect from "../../Common/CustomSelect";
import RegistBtn from "../../Board/RegistBtn";
import streamingApi from "../../../api/streaming";
import { useSelector } from "react-redux";
import PropTypes from "prop-types";
import Loading from "../../Common/Loading";

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
const RecordVideoFormModal = ({ sessionId, recordingId, setModalOpen }) => {
  const saveGymName = useSelector((state) => state.streaming.info.gymName);
  const [isSuccess, setIsSuccess] = useState(true);
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [isDisabled, setIsDisabled] = useState(false);
  const [isLoading, setLoading] = useState(false);
  const handleModalStateChange = () => {
    if (window.confirm("정말로 취소하시겠습니까?")) {
      setModalOpen();
    }
  };

  const registVideoToS3 = () => {
    if (!color || !level) {
      alert("모든 항목을 채워주세요.");
      return;
    }

    const data = {
      recordingId,
      isSaved: true,
      level,
      gymName: saveGymName,
      isSuccess,
      color,
    };

    setLoading(true);
    setIsDisabled(true);
    const api = async () =>
      streamingApi
        .saveRecordVideo(sessionId, data)
        .then(() => {
          setModalOpen();
          setIsDisabled(false);
        })
        .catch((err) => {
          console.log("err: ", err);
        })
        .finally(() => {
          setLoading(false);
        });
    api();
  };

  return isLoading ? (
    <Loading />
  ) : (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentBox>
          <S.ComponentWrap>
            <S.TitleWrap>
              <S.TitleWrap>영상 등록</S.TitleWrap>
            </S.TitleWrap>
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
            <RegistBtn
              btnName="등록"
              size="40px"
              clickHandler={registVideoToS3}
              disabled={isDisabled}
            />
          </S.ComponentWrap>
          <S.ComponentWrap>
            <RegistBtn
              btnName="취소"
              size="40px"
              clickHandler={handleModalStateChange}
            />
          </S.ComponentWrap>
        </S.ContentBox>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default React.memo(RecordVideoFormModal);

RecordVideoFormModal.propTypes = {
  sessionId: PropTypes.string.isRequired,
  recordingId: PropTypes.string.isRequired,
  setModalOpen: PropTypes.func.isRequired,
};
