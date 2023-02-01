import React from "react";
import OpenViduVideoComponent from "../OpemViduVideo";
import * as S from "./style";

function UserVideoComponent({ streamManager }) {
  const getNicknameTag = () => {
    return JSON.parse(streamManager.stream.connection.data).clientData;
  };

  return (
    <S.VideoWrapper>
      {streamManager !== undefined ? (
        <OpenViduVideoComponent streamManager={streamManager} />
      ) : (
        <S.GuideText>streamManager is undefined</S.GuideText>
      )}
    </S.VideoWrapper>
  );
}

export default UserVideoComponent;
