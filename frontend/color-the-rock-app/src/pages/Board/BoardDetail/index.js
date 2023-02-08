import React, { useState, useEffect } from "react";
import { Desktop, Mobile } from "../../../components/layout/Template";
import * as S from "./style";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import CommentModal from "../../../components/Common/CommentModal";
import CommentBtn from "../../../components/Common/CommentBtn";
import { useNavigate, useParams } from "react-router";
import Thumbnail from "../../../components/Common/Thumbnail";
import useInfiniteScroll from "../../../hooks/useInfiniteScroll";
import Header from "../../../components/layout/Header";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import TestVideo from "../../../assets/video/test.mp4";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import boardApi from "../../../api/board";

const BoardDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isOpenBoardSettingModal, setOpenBoardSettingModal] = useState(false);
  const [result, setResult] = useState({}); // 게시글 상세 정보 배열
  const [commentList, setCommentList] = useState({}); // 게시글 댓글 정보 배열
  const userNickname = useSelector((state) => state.users.nickName);
  //const [isFetching, setIsFetching] = useInfiniteScroll(updateFuncOnScroll);

  const updateFuncOnScroll = () => {
    // try {
    //   setData((prev) => [...prev, ...dummy]);
    // } catch (error) {
    // } finally {
    //   setIsFetching(false);
    // }
  };

  // 게시글 상세 조회 API
  const getBoardDetail = () => {
    console.log("param id: ", id);
    boardApi
      .getBoardDetail(id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
        }
      })
      .catch((error) => console.log(error));
  };

  // 게시글 댓글 조회 API
  const getBoardCommentList = () => {
    console.log("param id: ", id);

    boardApi
      .getVideoBoardCommentList(0, id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setCommentList(_result);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getBoardDetail();
    getBoardCommentList();
  }, []);

  const handleModal = () => {
    console.log("change");
    setIsModalOpen(true);
  };

  const handleOnclickDelete = () => {
    if (!window.confirm("정말로 삭제하시겠습니까")) return;

    // 게시글 삭제 API 호출 및 처리 완료 후 페이지 이동
    boardApi
      .deleteBoardDetail()
      .then(({ data: { status } }) => {
        if (status === 200) {
          alert("정상적으로 게시글이 삭제되었습니다.");
        } else if (status === 404) {
          alert("해당 글을 찾을 수 없습니다.");
        }
        navigate("/board");
      })
      .catch((error) => console.log(error));
  };

  const handleReportBoard = () => {
    // 게시글 신고를 위한 팝업 제공
  };

  return (
    <S.ContainerWrap>
      {isOpenBoardSettingModal && (
        <S.SettingModal>
          <S.SettingModalItem isSameAuthor={userNickname === result.nickname}>
            <Link to={`/board/modify/${result.videoBoardId}`}>수정</Link>
          </S.SettingModalItem>
          <S.SettingModalItem
            onClick={handleOnclickDelete}
            isSameAuthor={userNickname === result.nickname}
          >
            삭제
          </S.SettingModalItem>
          <S.SettingModalReportItem
            onClick={handleReportBoard}
            isSameAuthor={userNickname === result.nickname}
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
            <S.Video controls autoPlay>
              <source
                src="https://dhw80hz67vj6n.cloudfront.net/20230203_091428077.mp4"
                type="video/mp4"
              />
            </S.Video>
            {isModalOpen ? (
              <S.CommentModalWrap isModalOpen>
                <CommentModal setIsModalOpen={setIsModalOpen} />
              </S.CommentModalWrap>
            ) : (
              <S.FalseWrap>
                <S.ComponentWrap>
                  <S.RowWrap>
                    <BoardSubTitle text={result.title} />
                  </S.RowWrap>
                  <S.RowWrap>
                    <S.DetailInfo>{result.nickname}</S.DetailInfo>
                    <S.DetailInfo color="var(--color-tertiary)">
                      {result.createdDate}
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
