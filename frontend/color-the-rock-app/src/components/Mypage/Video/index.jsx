import React from "react";
import * as S from "./style";
import TestImg from "../../../assets/img/intro/bg-intro.png";
const Video = ({ title, color }) => {
  return (
    <S.Container>
      <S.ThumbnailImg src={TestImg} alt="썸네일 이미지" />
      <S.Text>{title}</S.Text>
      <S.Tag>{color}</S.Tag>
    </S.Container>
  );
};

export default Video;
