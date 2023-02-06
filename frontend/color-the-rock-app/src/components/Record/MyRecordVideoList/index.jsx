import React, { useEffect, useState } from "react";
import * as S from "./style";
import Video from "../../Mypage/Video";
import { recordApi } from "../../../api/record";
import { useSelector } from "react-redux";
import moment from "moment";

const MyRecordVideoList = ({ isSuccess = true }) => {
  const [videos, setVideos] = useState([]);
  const date = useSelector((state) => state.record.date);

  const getVideoListByCalendar = () => {
    // requestBody
    const data = {
      videoId: videos.length > 0 ? videos[videos.length - 1].videoId : 0,
      shootingDate: moment(date).format("YYYY-MM-DD"),
      isSuccess: isSuccess === "success" ? true : false,
    };

    // call API
    recordApi
      .getAllRecordVideo(data)
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          console.log("[getRecordVideo()] statusCode : 200 ", result);
          setVideos(result);
        }
      })
      .catch((error) => console.log("error :", error));
  };

  useEffect(() => {
    getVideoListByCalendar();
  }, []);

  return (
    <S.VideoList>
      {videos && videos.length > 0 ? (
        videos.map((item) => (
          <Video
            key={item.sources}
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
  );
};

export default MyRecordVideoList;
