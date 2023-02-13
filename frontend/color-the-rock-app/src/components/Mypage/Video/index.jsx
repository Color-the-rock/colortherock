import React from "react";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
const Video = ({ id, thumbnailURL, gymName, level, color }) => {
  const navigate = useNavigate();
  const handleOnClickThumbNail = () => {
    navigate(`/preview?videoId=${id}`);
  };
  return (
    <S.Container onClick={handleOnClickThumbNail}>
      <S.ThumbnailImg src={thumbnailURL} alt="썸네일 이미지" />
      <S.Text>{gymName}</S.Text>
      <S.Tag>{color}</S.Tag>
      <S.Tag>{level}</S.Tag>
    </S.Container>
  );
};

export default Video;

Video.propTypes = {
  id: PropTypes.number,
  thumbnailURL: PropTypes.string,
  gymName: PropTypes.string,
  level: PropTypes.number,
  color: PropTypes.string,
};
