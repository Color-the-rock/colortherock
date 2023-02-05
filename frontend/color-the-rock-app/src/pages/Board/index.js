import React, { useEffect, useState } from "react";
import Thumbnail from "../../components/Common/Thumbnail";
import Title from "../../components/Common/Title";
import * as S from "./style";
import { HiPencil } from "react-icons/hi";
import BoardSearchBar from "../../components/Board/BoardSearch";
import boardApi from "../../api/board";

const Board = () => {
  const [result, setResult] = useState([]);
  const [searchLocation, setSearchLocation] = useState("");

  const getBoardList = () => {
    const requestData = {
      storeId: 0,
      color: "",
      gymName: "",
    };
    boardApi
      .getAllVideo(requestData)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getBoardList();
  }, []);

  return (
    <S.Container>
      <Title>완등 영상 보기</Title>
      <S.Description>완등 영상을 게시하고 피드백을 받아보세요!</S.Description>
      <BoardSearchBar
        searchLocation={searchLocation}
        setSearchLocation={setSearchLocation}
      />
      {/* list */}
      {result && result.length > 0 ? (
        <S.ThumbnailList>
          {result.map((item) => (
            <Thumbnail
              key={item.videoBoardId}
              id={item.videoBoardId}
              title={item.title}
              userNickname={item.userNickname}
              gymName={item.gymName}
              imgUrl={item.thumbnailURL}
              isLive={false}
              color={item.color}
              createdDate={item.createdDate}
            />
          ))}
        </S.ThumbnailList>
      ) : (
        <S.Message>등록된 게시글이 없어요!</S.Message>
      )}
      <S.LiveButton to="/board/regist">
        <HiPencil size="24px" color="#C250D6" />
      </S.LiveButton>
    </S.Container>
  );
};
export default Board;
