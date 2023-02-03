import React, { useCallback, useState, useRef } from "react";
import * as S from "./style";
import Webcam from "react-webcam";
import { HiOutlineCamera } from "react-icons/hi2";

const ModifyRoomSettingModal = ({ handleModalStateChange }) => {
  const webcamRef = useRef(null);
  const [imgSrc, setImgSrc] = useState(null);

  const handleCapture = useCallback(() => {
    const imageSrc = webcamRef.current.getScreenshot();
    setImgSrc(imageSrc);
  }, [webcamRef, setImgSrc]);

  return (
    <S.ContainerWrap>
      <S.Container>
        {/* 모달 밖 영역 */}
        <S.Background onClick={handleModalStateChange}></S.Background>

        {/* 모달 안 영역 */}
        <S.ContentBox>
          <Webcam
            className="webcam"
            audio={false}
            ref={webcamRef}
            screenshotFormat="image/jpeg"
          />
          <HiOutlineCamera
            className="camera"
            size="32px"
            onClick={handleCapture}
          />
          {imgSrc && (
            <S.CaptureWrap>
              <img src={imgSrc} alt="capture" />
            </S.CaptureWrap>
          )}
        </S.ContentBox>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default ModifyRoomSettingModal;
