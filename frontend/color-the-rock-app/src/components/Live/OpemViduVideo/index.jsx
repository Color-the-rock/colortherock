import React, { useEffect } from "react";

function OpenViduVideoComponent({ streamManager }) {
  const videoRef = React.createRef();
  useEffect(() => {
    if (streamManager && !!videoRef) {
      streamManager.addVideoElement(videoRef.current);
    }
  }, [streamManager, videoRef]);

  return (
    <div>
      <div>흠</div>
      <video autoPlay={true} ref={videoRef}></video>
    </div>
  );
}

export default OpenViduVideoComponent;
