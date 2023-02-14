import React, { useState } from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const now = Math.floor(new Date().getTime());

const VideoContent = ({
  recordingId,
  createdAt,
  duration,
  url,
  handleVideo,
}) => {
  return (
    <S.Container>
      <S.PlayButtonWrap>
        <S.DocumentText />
      </S.PlayButtonWrap>
      <S.ContentWrap>
        <S.Content>
          <S.RowContent>
            {recordingId.length < 17
              ? recordingId
              : recordingId.substring(0, 16) + "..."}
          </S.RowContent>
          <S.RowContent>
            <S.DurationWrap>{duration + "s"}</S.DurationWrap>
          </S.RowContent>

          <S.RowContent>
            {parseInt((now - createdAt) / 60000)}분 전
          </S.RowContent>
        </S.Content>
      </S.ContentWrap>
      <S.ContentWrap>
        <S.PlayButton onClick={() => handleVideo(url)} />
        <S.DownLoadButton />
      </S.ContentWrap>
    </S.Container>
  );
};

export default VideoContent;

VideoContent.propTypes = {
  recordingId: PropTypes.string.isRequired,
  createdAt: PropTypes.number.isRequired,
  duration: PropTypes.number.isRequired,
  url: PropTypes.string.isRequired,
  handleVideo: PropTypes.func.isRequired,
};
