import React from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
const Video = ({ id, thumbnailURL, gymName, level, color }) => {
  const navigate = useNavigate();
  const handleOnClickThumbNail = () => {
    console.log("handleOnClickThumbNail()... ");
    navigate(`/preview?videoId=${id}`);
  };
  return (
    <S.Container onClick={handleOnClickThumbNail}>
      <S.ThumbnailImg src={thumbnailURL} alt="썸네일 이미지" />
      <S.Tag>{gymName}</S.Tag>
      <S.Tag>{color}</S.Tag>
      <S.Tag>{level}</S.Tag>
    </S.Container>
  );
};

export default Video;
