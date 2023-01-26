import React from "react";
import * as S from "./style";
import TestImg from "../../../assets/img/intro/bg-intro.png";
const Thumbnail = ({
  id,
  title,
  userNickname,
  gymName,
  imgUrl,
  isLive,
  color,
}) => {
  return (
    <S.Container id={id} to={isLive ? `/streaming` : `/board/detail/${id}`}>
      <S.ThumbnailImg src={!imgUrl ? TestImg : imgUrl} />
      <S.VideoText isLive={true}>{title}</S.VideoText>
      <S.VideoText isLive={isLive}>
        <S.LiveBadge />
        {userNickname}
      </S.VideoText>

      {color && <S.Tag>{color}</S.Tag>}
      <S.Tag>{gymName}</S.Tag>
    </S.Container>
  );
};
export default Thumbnail;
