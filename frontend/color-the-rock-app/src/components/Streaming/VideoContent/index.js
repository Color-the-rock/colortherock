import React from "react";
import * as S from "./style";
const VideoContent = ({ recordingId, createdAt, duration }) => {
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
            <S.DurationWrap>{duration}</S.DurationWrap>
          </S.RowContent>

          <S.RowContent>{createdAt}</S.RowContent>
        </S.Content>
      </S.ContentWrap>
      <S.ContentWrap>
        <S.PlayButton />
        <S.DownLoadButton />
      </S.ContentWrap>
    </S.Container>
  );
};

export default VideoContent;
