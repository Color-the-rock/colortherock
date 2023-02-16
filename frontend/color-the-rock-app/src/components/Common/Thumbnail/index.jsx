import React from "react";
import PropTypes from "prop-types";
import * as S from "./style";
import TestImg from "../../../assets/img/intro/bg-intro.png";
const Thumbnail = ({
  id,
  sessionId,
  title,
  userNickname,
  gymName,
  imgUrl,
  isLive,
  color,
  onClick,
}) => {
  return (
    <S.Container
      id={isLive ? sessionId : id}
      onClick={() => onClick(isLive ? sessionId : id)}
    >
      <S.ThumbnailImg src={!imgUrl ? TestImg : imgUrl} />
      {title && (
        <S.VideoText isLive={true}>
          {title.length < 13 ? title : title.substring(0, 12) + "..."}
        </S.VideoText>
      )}
      <S.VideoText isLive={isLive}>
        <S.LiveBadge />
        {userNickname}
      </S.VideoText>
      {color && <S.Tag>{color}</S.Tag>}
      <S.Tag>{gymName}</S.Tag>
    </S.Container>
  );
};
export default React.memo(Thumbnail);

Thumbnail.propTypes = {
  id: PropTypes.number,
  sessionId: PropTypes.string,
  title: PropTypes.string,
  userNickname: PropTypes.string,
  gymName: PropTypes.string,
  imgUrl: PropTypes.string,
  isLive: PropTypes.bool,
  color: PropTypes.string,
  onClick: PropTypes.func,
};
