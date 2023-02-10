import React, { useState, useEffect } from "react";
import { Desktop, Mobile } from "../../layout/Template";
import * as S from "./style";
import streamingApi from "../../../api/streaming";

const VideoClip = ({ sessionId, setModalOpen }) => {
  const [isPublisher, setIsPublisher] = useState(true);
  const [data, setData] = useState([]);

  useEffect(() => {
    streamingApi
      .getRecordList(sessionId)
      .then((res) => {
        console.log("res: ", res);
        setData(res);
      })
      .catch((err) => {
        console.log("err: ", err);
      });
  });

  const onClickHandler = () => {
    setModalOpen();
  };

  return (
    <>
      <S.ContainerWrap>
        <S.Container>
          <S.ContentBox>
            {isPublisher && <button onClick={onClickHandler}>나 뭐야</button>}
            만드는 중입니다.
          </S.ContentBox>
        </S.Container>
      </S.ContainerWrap>
    </>
  );
};

export default VideoClip;
