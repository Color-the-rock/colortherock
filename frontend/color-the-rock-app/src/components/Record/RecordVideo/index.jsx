import React from "react";
import * as S from "./style";
import TestImg from "../../../assets/img/intro/bg-intro.png";
const RecordVideo = ({ id, title, tag, imgUrl }) => {
  return (
    <S.VideoContainer id={id}>
      <S.ThumbnailImg src={!imgUrl ? TestImg : imgUrl} />
      <S.Title>{title}</S.Title>
      <S.Tag>{tag}</S.Tag>
    </S.VideoContainer>
  );
};
export default RecordVideo;
