import { useNavigate } from "react-router-dom";
import React, { useState, useRef, useCallback } from "react";
import Webcam from "react-webcam";
import * as S from "./style";
import { FiArrowLeft } from "react-icons/fi";
import InputComp from "../../components/Board/InputComp";
// import ArrowLeftBtn from "../../components/Common/ArrowLeftBtn"
import BoardSubTitle from "../../components/Board/BoardSubTitle";
import SearchBar from "../../components/Common/KakaoKeywordSearch/SearchBar";
import RegistBtn from "../../components/Board/RegistBtn";
import BoardRadioBtn from "../../components/Board/BoardRadioBtn";
import { FiChevronDown } from "react-icons/fi";
import { FiChevronUp } from "react-icons/fi";
import { HiOutlineCamera } from "react-icons/hi2";
import { OpenVidu } from "openvidu-browser";
import { useDispatch } from "react-redux";
import { setOV } from "../../stores/streaming/streamingSlice";

// ------------- test ----------------------//
import RecordVideoFormModal from "../../components/Streaming/RecordVideoFormModal";
import ModifyRoomSettingModal from "../../components/Streaming/ModifyRoomSettingModal";
import FeedbackModal from "../../components/Streaming/FeedbackModal";
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

const videoConstraints = {
  width: { min: 480 },
  heigth: { min: 720 },
  appectRatio: 0.6666666667,
  facingMode: "user",
};

const StreamingForm = () => {
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

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const webcamRef = useRef(null);
  const [imgSrc, setImgSrc] = useState(null);
  const [onSetting, setOnSetting] = useState(true);

  // live 생성시 보내줘야 하는 데이터
  const [isPublic, setIsPublic] = useState(true);
  const [title, setTitle] = useState("");
  const [gymName, setGymName] = useState("");

  const clickHandler = () => {
    navigate("/streaming");
  };

  // setting 창 열기/닫기
  const ChangeSettingMode = () => {
    setOnSetting((prev) => !prev);
  };

  const submitHandler = () => {
    const data = {
      isPublic,
      gymName,
      title,
    };

    // ----------------------------------------------- //
    //api /api/live 요청 보내기
    // 올바른 응답일 떄는 joinSsession 및 라이브 페이지로 이동...
    // ---------------------------------------------- //

    joinSession();
    navigate("/streaming/live/1");
  };

  // openVidu 설정
  const joinSession = () => {
    console.log("joinSession");
    const ov = new OpenVidu();
    dispatch(setOV({ ov }));
  };

  // 캡처
  const handleCapture = useCallback(() => {
    const imageSrc = webcamRef.current.getScreenshot();
    setImgSrc(imageSrc);
  }, [webcamRef, setImgSrc]);

  return (
    <S.Container>
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

      <S.ContentWrap>
        <S.Content>
          <Webcam
            className="webcam"
            audio={false}
            ref={webcamRef}
            screenshotFormat="image/jpeg"
            videoConstraints={videoConstraints}
          />
          <HiOutlineCamera
            size="32px"
            className="camera"
            onClick={handleCapture}
          />
          {imgSrc && (
            <S.CaptureWrap>
              <img src={imgSrc} alt="capture" />
            </S.CaptureWrap>
          )}
          <S.ComponenentWrap>
            <RegistBtn btnName="방송 시작" clickHandler={submitHandler} />
          </S.ComponenentWrap>

          {/* 방송 설정 (카메라 위에 올릴 컴포넌트들) */}
          <S.OverlapContent>
            <S.ArrowLeftBtnWrap onClick={clickHandler}>
              <FiArrowLeft />
            </S.ArrowLeftBtnWrap>

            <S.ComponenentWrap>
              <S.SettingComponentWrap onClick={ChangeSettingMode}>
                <BoardSubTitle text="방송 설정" />
                {onSetting ? (
                  <FiChevronDown size="26px" />
                ) : (
                  <FiChevronUp size="26px" />
                )}
              </S.SettingComponentWrap>
            </S.ComponenentWrap>

            {onSetting && (
              <S.AddPadding>
                <S.ComponenentWrap>
                  <BoardRadioBtn
                    isPublic={isPublic}
                    setIsPublic={setIsPublic}
                    firstText="공개"
                    SecondText="비공개"
                    opacity="70"
                  />
                </S.ComponenentWrap>
                <S.ComponenentWrap>
                  <InputComp
                    title={title}
                    setTitle={setTitle}
                    placeholder="제목을 입력해주세요."
                    opacity="70"
                  />
                </S.ComponenentWrap>
                <S.ComponenentWrap>
                  <SearchBar
                    location={gymName}
                    setLocation={setGymName}
                    opacity="70"
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
              </S.AddPadding>
            )}
          </S.OverlapContent>
        </S.Content>
      </S.ContentWrap>
    </S.Container>
  );
};

export default StreamingForm;
