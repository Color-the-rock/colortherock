import React, { useState, useEffect } from "react";
import * as S from "./style";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import CommentModal from "../../../components/Common/CommentModal";
import CommentBtn from "../../../components/Common/CommentBtn";
import { useNavigate, useParams } from "react-router";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import { useSelector } from "react-redux";
import boardApi from "../../../api/board";
import ReportModal from "../../../components/Board/ReportModal";

const BoardDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isOpenBoardSettingModal, setOpenBoardSettingModal] = useState(false);
  const [isOpenBoardReportModal, setOpenBoardReportModal] = useState(false);
  const [result, setResult] = useState({}); // 게시글 상세 정보 배열
  const userNickname = useSelector((state) => state.users.nickName);

  // 게시글 상세 조회 API
  const getBoardDetail = () => {
    boardApi
      .getBoardDetail(id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);

          if (_result.length === 0) {
            alert("해당 게시물은 신고로 인해 더 이상 접근할 수 없습니다:)");
            navigate("/board");
            return;
          }

          setResult(_result);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getBoardDetail();
  }, []);

  const handleModal = () => {
    setIsModalOpen(true);
  };

  const handleOnclickDelete = () => {
    if (!window.confirm("정말로 삭제하시겠습니까")) return;

    // 게시글 삭제 API 호출 및 처리 완료 후 페이지 이동
    boardApi
      .deleteBoardDetail(id)
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
    setOpenBoardReportModal(true);
    setOpenBoardSettingModal(false);
  };

  return (
    <S.ContainerWrap>
      <S.ArrowLeftBtnWrap>
        <ArrowLeftBtn clickHandler={() => navigate("/board")}></ArrowLeftBtn>
        <S.BoardSettingBtn
          onClick={() => setOpenBoardSettingModal((prev) => !prev)}
        />

        {isOpenBoardSettingModal && (
          <S.SettingModal>
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
      </S.ArrowLeftBtnWrap>
      <S.Container>
        <S.ContentContainer>
          {isOpenBoardReportModal && (
            <ReportModal setOpenBoardReportModal={setOpenBoardReportModal} />
          )}

          <S.ContentWrap>
            <S.Video
              controls
              autoPlay
              src={result.s3URL}
              type="video/mp4"
            ></S.Video>
            {isModalOpen ? (
              <S.CommentModalWrap isModalOpen>
                <CommentModal
                  setIsModalOpen={setIsModalOpen}
                  isModalOpen={isModalOpen}
                />
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
              </S.FalseWrap>
            )}
          </S.ContentWrap>
        </S.ContentContainer>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default BoardDetail;
