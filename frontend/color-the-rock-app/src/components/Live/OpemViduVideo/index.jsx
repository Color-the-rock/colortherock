import React, { useEffect } from "react";
import * as S from "./style";
import PropTypes from "prop-types";
const OpenViduVideoComponent = ({ streamManager }) => {
  const videoRef = React.createRef();

  useEffect(() => {
    if (streamManager && !!videoRef) {
      streamManager.addVideoElement(videoRef.current);
    }
  }, [streamManager, videoRef]);

  return <S.Video autoPlay={true} ref={videoRef}></S.Video>;
};

export default OpenViduVideoComponent;

OpenViduVideoComponent.propTypes = {
  streamManager: PropTypes.object,
};
