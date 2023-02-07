import React, { useEffect, createRef } from "react";
import Canvas from "../Canvas";
import * as S from "./style";

export const OVVideo = ({ streamManager }) => {
  const videoRef = createRef();

  useEffect(() => {
    if (streamManager && !!videoRef) {
      streamManager.addVideoElement(videoRef.current);
    }
  });

  return (
    <>
      <video />
      <Canvas videoRef={videoRef} />
    </>
  );
};
