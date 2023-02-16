import React from "react";
import * as S from "./style";
import Video from "../../Mypage/Video";
import { useSelector } from "react-redux";

const MyRecordVideoList = () => {
  const videos = useSelector((state) => state.record.videos);

  return (
    <>
      {videos && videos.length > 0 && (
        <S.Label>총 {videos.length}개의 영상이 기록되었습니다:)</S.Label>
      )}
      <S.VideoList>
        {videos && videos.length > 0 ? (
          videos.map((item) => (
            <Video
              key={item.videoId}
              id={item.videoId}
              thumbnailURL={item.thumbnailURL}
              level={item.level}
              color={item.color}
              gymName={item.gymName}
            />
          ))
        ) : (
          <S.Description>선택한 날짜에 기록된 영상이 없어요:) </S.Description>
        )}
      </S.VideoList>
    </>
  );
};

export default MyRecordVideoList;
