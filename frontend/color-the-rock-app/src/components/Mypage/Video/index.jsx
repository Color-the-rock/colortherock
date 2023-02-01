import React from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
const Video = ({ thumb, title, color, sources }) => {
  const navigate = useNavigate();
  const handleOnClickThumbNail = () => {
    console.log("handleOnClickThumbNail()... ");
    navigate(`/preview?source=${sources[0]}`);
  };
  return (
    <S.Container onClick={handleOnClickThumbNail}>
      <S.ThumbnailImg src={thumb} alt="썸네일 이미지" />
      <S.Text>{title}</S.Text>
      <S.Tag>{color}</S.Tag>
    </S.Container>
  );
};

export default Video;
