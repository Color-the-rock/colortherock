import React, { useEffect, useState } from "react";
import Thumbnail from "../../components/Common/Thumbnail";
import Title from "../../components/Common/Title";
import * as S from "./style";
import { HiPencil } from "react-icons/hi";
import BoardSearchBar from "../../components/Board/BoardSearch";
import boardApi from "../../api/board";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import Loading from "../../components/Common/Loading";
import useInfiniteScroll from "../../hooks/useInfiniteScroll";

const Board = () => {
  const navigate = useNavigate();
  const isLogin = useSelector((state) => state.users.isLogin);
  const [result, setResult] = useState([]);
  const [storeId, setStoreId] = useState(-1);
  const [isShowRegisterModal, setShowRegisterModal] = useState(false);
  const currentOption = useSelector((state) => state.board.searchColorValue);
  const searchGymName = useSelector((state) => state.board.searchGymName);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    getBoardList();
  }, [currentOption]);

  const getBoardList = async () => {
    setIsLoading(true);
    const requestData = {
      storeId: storeId,
      color: currentOption === "색상" ? "" : currentOption,
      gymName: searchGymName,
    };

    await boardApi
      .getAllVideo(requestData)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          if (storeId === -1) {
            setResult([..._result]);
          } else {
            if (_result.length === 0) {
            } else {
              setResult((prev) => [...prev, ..._result]);
            }
          }
          if (_result.length !== 0) {
            let lastId =
              _result[_result.length - 1].videoBoardId === undefined
                ? -1
                : _result[_result.length - 1].videoBoardId;
            setStoreId(lastId);
          }
        }
      })
      .catch((error) => console.log(error))
      .finally(() => {
        setTimeout(() => setIsLoading(false), 200);
        setIsFetching(false);
      });
  };

  const handleProcessRegister = (e) => {
    const { target } = e;

    if (target.nodeName === "DIV") {
      setShowRegisterModal(false);
      return;
    }
    setShowRegisterModal(false);
  };

  const handleOnClickRegisterBoard = () => {
    if (isLogin) {
      setShowRegisterModal(true);
    } else {
      alert("로그인 후, 이용해주세요!");
    }
  };

  const handleOnClickItem = (id) => {
    if (!isLogin) {
      alert("로그인 후, 이용해주세요!");
      return;
    }

    navigate(`/board/detail/${id}`);
  };

  const [isFetching, setIsFetching] = useInfiniteScroll(getBoardList);

  return (
    <S.Container id="board-container">
      <Title>완등 영상 보기</Title>
      <S.Description>
        원하는 색상과 암장의 완등 영상을 검색해보세요!
      </S.Description>
      <BoardSearchBar getBoardList={getBoardList} setStoreId={setStoreId} />
      {isLoading && <Loading />}
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
              onClick={handleOnClickItem}
            />
          ))}
        </S.ThumbnailList>
      ) : (
        <S.Message>등록된 게시글이 없어요!</S.Message>
      )}
      <S.RegisterButton onClick={handleOnClickRegisterBoard}>
        <HiPencil size="24px" color="#C250D6" />
      </S.RegisterButton>

      {isShowRegisterModal && (
        <S.RegisterModal onClick={handleProcessRegister}>
          <S.ModalButtonWrapper>
            <S.ModalButton value="local" to="/board/form">
              <S.GradientText>내 기기에서 가져오기</S.GradientText>
            </S.ModalButton>
            <S.ModalButton value="cloud" to="/board/s3form">
              <S.GradientText>내 운동기록에서 가져오기</S.GradientText>
            </S.ModalButton>
          </S.ModalButtonWrapper>
        </S.RegisterModal>
      )}
    </S.Container>
  );
};
export default Board;
