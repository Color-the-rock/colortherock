import React, { useState, useEffect } from "react";
import { Desktop, Mobile } from "../../layout/Template";
import * as S from "./style";
import streamingApi from "../../../api/streaming";
import VideoContent from "../VideoContent";

const dummy = [
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "1111",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "2222",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "3333",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "3333",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "1111",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "2222",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "3333",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "3333",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "1111",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "2222",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "3333",
    duration: "12s",
  },
  {
    recordingId: "bandicam 2023-01-18 14-09-52-616",
    createdAt: "3333",
    duration: "12s",
  },
];

const VideoClip = ({ sessionId, setModalOpen }) => {
  const [isPublisher, setIsPublisher] = useState(true);
  const [result, setResult] = useState(dummy);

  useEffect(() => {
    streamingApi
      .getRecordList(sessionId)
      .then((res) => {
        console.log("res: ", res);
        setResult(res);
      })
      .catch((err) => {
        console.log("err: ", err);
      });
  }, []);

  const onClickHandler = () => {
    setModalOpen();
  };

  return (
    <>
      <S.ContainerWrap>
        <S.Container>
          <S.ContentBox>
            <S.ChromeClose onClick={onClickHandler} />
            <S.Title>Recodings</S.Title>
            {result && result.length > 0 ? (
              <S.VideoList>
                {result.map((item) => (
                  <S.VideoWrap>
                    <VideoContent
                      key={item.createdAt}
                      createdAt={item.createdAt}
                      recordingId={item.recordingId}
                      duration={item.duration}
                    />
                  </S.VideoWrap>
                ))}
              </S.VideoList>
            ) : (
              <div>영상 정보가 없습니다.</div>
            )}
          </S.ContentBox>
        </S.Container>
      </S.ContainerWrap>
    </>
  );
};

export default VideoClip;
