import React from "react";
import * as S from "./style";
const Video = ({ thumb, title, color, sources }) => {
  console.log(thumb);
  return (
    <S.Container poster={thumb} controls>
      <source src={sources[0]} type="video/mp4" />
    </S.Container>
    //  <S.ThumbnailImg src={thumb} alt="썸네일 이미지" />
    //  <S.Text>{title}</S.Text>
    //  <S.Tag>{color}</S.Tag>
  );
};

export default Video;
