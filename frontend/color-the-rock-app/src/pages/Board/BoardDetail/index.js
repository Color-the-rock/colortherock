import React, { useState, useEffect } from "react";
import { Desktop, Mobile } from "../../../components/layout/Template";
import * as S from "./style";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import CommentModal from "../../../components/Common/CommentModal";
import CommentBtn from "../../../components/Common/CommentBtn";
import { useNavigate } from "react-router";
import Thumbnail from "../../../components/Common/Thumbnail";
import useInfiniteScroll from "../../../hooks/useInfiniteScroll";
import Header from "../../../components/layout/Header";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import TestVideo from "../../../assets/video/test.mp4";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const BoardDetail = () => {
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isOpenBoardSettingModal, setOpenBoardSettingModal] = useState(false);
  const [result, setResult] = useState({}); // 게시글 상세 정보 객체
  const userNickname = useSelector((state) => state.user.nickName);

  const updateFuncOnScroll = () => {
    // try {
    //   setData((prev) => [...prev, ...dummy]);
    // } catch (error) {
    // } finally {
    //   setIsFetching(false);
    // }
  };

  const [isFetching, setIsFetching] = useInfiniteScroll(updateFuncOnScroll);

  const handleModal = () => {
    console.log("change");
    setIsModalOpen(true);
  };

  const handleOnclickDelete = () => {
    if (!window.confirm("정말로 삭제하시겠습니까")) return;

    // 게시글 삭제 API 호출 및 처리 완료 후 페이지 이동
  };

  const handleReportBoard = () => {
    // 게시글 신고를 위한 팝업 제공
  };

  return (
    <S.ContainerWrap>
      {isOpenBoardSettingModal && (
        <S.SettingModal>
          <S.SettingModalItem
            isSameAuthor={userNickname === result.userNickname}
          >
            <Link to={`/board/modify/${result.boardId}`}>수정</Link>
          </S.SettingModalItem>
          <S.SettingModalItem
            onClick={handleOnclickDelete}
            isSameAuthor={userNickname === result.userNickname}
          >
            삭제
          </S.SettingModalItem>
          <S.SettingModalReportItem
            onClick={handleReportBoard}
            isSameAuthor={userNickname === result.userNickname}
          >
            신고
          </S.SettingModalReportItem>
        </S.SettingModal>
      )}
      <Desktop>
        <S.HeaderWrap>
          <Header></Header>
        </S.HeaderWrap>
      </Desktop>
      <Mobile>
        <S.ArrowLeftBtnWrap>
          <ArrowLeftBtn clickHandler={() => navigate("/board")}></ArrowLeftBtn>
          <S.BoardSettingBtn
            onClick={() => setOpenBoardSettingModal((prev) => !prev)}
          />
        </S.ArrowLeftBtnWrap>
      </Mobile>

      <S.Container>
        <S.ContentContainer>
          <S.ContentWrap>
            <S.Video controls>
              <source src={TestVideo} type="video/mp4" />
            </S.Video>

            {isModalOpen ? (
              <S.CommentModalWrap isModalOpen>
                <CommentModal setIsModalOpen={setIsModalOpen} />
              </S.CommentModalWrap>
            ) : (
              <S.FalseWrap>
                <S.ComponentWrap>
                  <S.RowWrap>
                    <BoardSubTitle text="제목" />
                  </S.RowWrap>
                  <S.RowWrap>
                    <S.DetailInfo>작성자</S.DetailInfo>
                    <S.DetailInfo color="var(--color-tertiary)">
                      2023-01-29
                    </S.DetailInfo>
                  </S.RowWrap>
                </S.ComponentWrap>
                <S.CommentWrap>
                  <CommentBtn onClick={handleModal} />
                </S.CommentWrap>
                {/* <S.ComponentWrap>
                  <BoardSubTitle text="다른 완등 영상 보기" />
                </S.ComponentWrap>
                <S.ThumbnailList>
                  {data.map((item) => (
                    <Thumbnail
                      key={item.id}
                      id={item.id}
                      title={item.title}
                      userNickname={item.userNickname}
                      gymName={item.gymName}
                      imgUrl={item.imgUrl}
                      isLive={false}
                      color={item.color}
                    />
                  ))}
                </S.ThumbnailList> */}
              </S.FalseWrap>
            )}
          </S.ContentWrap>
        </S.ContentContainer>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default BoardDetail;
