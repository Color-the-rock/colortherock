import React from "react";
import * as S from "./style";
import TestImg from "../../../assets/img/intro/bg-intro.png";
const RecordVideo = ({ id, title, imgUrl }) => {
  return <S.ThumbnailImg src={!imgUrl ? TestImg : imgUrl} />;
};
export default RecordVideo;
