import React, { useState } from "react";
import * as S from "./style";
import webCam from "react-webcam";

const FeedbackModal = ({ setModalOpen }) => {
  // 임시용
  const [isPublisher, setIsPublisher] = useState(true);

  const onClickHandler = () => {};

  return (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentBox>
          {/* Publisher만 보이는 버튼 =>  */}
          {isPublisher && <button onClick={onClickHandler}></button>}
          만드는 중입니다.
        </S.ContentBox>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default FeedbackModal;
