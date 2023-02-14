import React from "react";
import OpenViduVideoComponent from "../OpemViduVideo";
import * as S from "./style";
import PropTypes from "prop-types";

const UserVideoComponent = ({ streamManager }) => {
  return (
    <S.VideoWrapper>
      {streamManager !== undefined ? (
        <OpenViduVideoComponent streamManager={streamManager} />
      ) : (
        <p>스트리머가 존재하지 않아요!</p>
      )}
    </S.VideoWrapper>
  );
};

export default UserVideoComponent;

UserVideoComponent.propTypes = {
  streamManager: PropTypes.object,
};
