import React, { useState, useEffect } from "react";
import * as S from "./style";
import streamingApi from "../../../api/streaming";
import VideoContent from "../VideoContent";
import PropTypes from "prop-types";

const VideoClip = ({ sessionId, setModalOpen }) => {
  const [result, setResult] = useState([]);
  const [playVideo, setPlayVideo] = useState(false);
  const [URL, setURL] = useState();

  useEffect(() => {
    streamingApi
      .getRecordList(sessionId)
      .then((res) => {
        setResult((prev) => [...prev, ...res.data.result]);
      })
      .catch((err) => {
        console.log("err: ", err);
      });
  }, []);

  const onClickHandler = () => {
    setModalOpen();
  };

  const handleVideo = (url) => {
    console.log("클릭!");
    setPlayVideo((prev) => !prev);
    setURL(url);
  };

  return (
    <>
      <S.ContainerWrap>
        <S.Container>
          {!playVideo ? (
            <S.ContentBox>
              <S.ChromeClose onClick={onClickHandler} />
              <S.Title>녹화영상목록</S.Title>
              {result && result.length > 0 ? (
                <S.VideoList>
                  {result.map((item, idx) => (
                    <S.VideoWrap key={idx}>
                      <VideoContent
                        createdAt={item.createdAt}
                        recordingId={item.recordingId}
                        duration={item.duration}
                        url={item.url}
                        handleVideo={handleVideo}
                      />
                    </S.VideoWrap>
                  ))}
                </S.VideoList>
              ) : (
                <S.VideoList>
                  <S.Message>영상 정보가 없습니다.</S.Message>
                </S.VideoList>
              )}
            </S.ContentBox>
          ) : (
            <S.ContentBox>
              <S.ChromeClose onClick={() => handleVideo("")} />
              <S.Video src={URL} controls muted />
            </S.ContentBox>
          )}
        </S.Container>
      </S.ContainerWrap>
    </>
  );
};

export default VideoClip;

VideoClip.propTypes = {
  sessionId: PropTypes.string.isRequired,
  setModalOpen: PropTypes.func.isRequired,
};
