import React from "react";
import * as S from "./style";
import Video from "../../Mypage/Video";
const videos = [
  {
    sources: [
      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
    ],
    thumb:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfDH3NZZuW6DeOsQGNKBvtRiNULwHKaeLhtw&usqp=CAU",
    title: "Big Buck Bunny",
    color: "빨강",
  },
  {
    sources: [
      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
    ],
    thumb:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfDH3NZZuW6DeOsQGNKBvtRiNULwHKaeLhtw&usqp=CAU",
    title: "Elephant Dream",
    color: "노랑",
  },
  {
    sources: [
      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
    ],
    thumb:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfDH3NZZuW6DeOsQGNKBvtRiNULwHKaeLhtw&usqp=CAU",
    title: "For Bigger Blazes",
    color: "초록",
  },
  {
    sources: [
      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
    ],
    thumb:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfDH3NZZuW6DeOsQGNKBvtRiNULwHKaeLhtw&usqp=CAU",
    title: "For Bigger Escape",
    color: "보라",
  },
  {
    sources: [
      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
    ],
    thumb:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfDH3NZZuW6DeOsQGNKBvtRiNULwHKaeLhtw&usqp=CAU",
    title: "For Bigger Fun",
    color: "하늘",
  },
];

const MyRecordVideoList = () => {
  return (
    <S.VideoList>
      {videos &&
        videos.map((item) => (
          <Video
            key={item.sources}
            title={item.title}
            color={item.color}
            thumb={item.thumb}
            sources={item.sources}
          />
        ))}
    </S.VideoList>
  );
};

export default MyRecordVideoList;
