import React, { useEffect, useState } from "react";
import { adminApi } from "../../api/admin";
import AdminReportModal from "../../components/Admin/AdminReportModal";
import AdminLogin from "../../components/Admin/AdminLogin";
import AdminTitle from "../../components/Admin/AdminTitle";
import * as S from "./style";

const Admin = () => {
  const [isAdmin, setAdmin] = useState(false);
  const [result, setResult] = useState([]);
  const [isOpenModal, setOpenModal] = useState(false);
  const [reportDetail, setReportDetail] = useState([]);

  // 관리자 숨김 영상 목록 조회
  const getAllHiddenBoardList = () => {
    adminApi
      .getHiddenVideoBoardList()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          setResult(_result);
        }
      })
      .catch((error) => console.log("[getHiddenVideoBoardList] : ", error));
  };

  // 관리자 영상 삭제
  const handleDeleteBoard = (id) => {
    adminApi
      .deleteVideoBoard(id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          getAllHiddenBoardList();
        }
      })
      .catch((error) => console.log("[deleteVideoBoard] : ", error));
  };

  // 관리자 영상 숨김 해제
  const handleToggleBoard = (id) => {
    adminApi
      .toggleHiddenVideoBoard(id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          getAllHiddenBoardList();
        }
      })
      .catch((error) => console.log("[toggleHiddenVideoBoard] : ", error));
  };

  // 관리자 신고 내역 조회
  const getReportDetail = (id) => {
    setOpenModal(true);

    adminApi
      .getReportDetail(id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          setReportDetail(_result);
          setOpenModal(true);
        }
      })
      .catch((error) => console.log("[getHiddenVideoBoardList] : ", error));
  };

  useEffect(() => {
    if (!isAdmin) return;
    getAllHiddenBoardList();
  }, [isAdmin]);

  return !isAdmin ? (
    <S.NoAdmin>
      <AdminTitle text="ADMIN" />
      <AdminLogin setAdmin={setAdmin} />
    </S.NoAdmin>
  ) : (
    <S.Container>
      <AdminTitle text="숨김영상목록" />

      {isOpenModal && (
        <AdminReportModal
          reportDetail={reportDetail}
          setReportDetail={setReportDetail}
          setOpenModal={setOpenModal}
        />
      )}

      <S.HiddenVideoBoardList>
        {result && result.length > 0 ? (
          result.map((item) => (
            <S.BoardItem key={item.videoBoardId} id={item.videoBoardId}>
              <S.BoardTitle onClick={() => getReportDetail(item.videoBoardId)}>
                {item.title}
              </S.BoardTitle>
              <S.ButtonWrapper>
                <S.Button onClick={() => handleDeleteBoard(item.videoBoardId)}>
                  삭제
                </S.Button>
                <S.Button onClick={() => handleToggleBoard(item.videoBoardId)}>
                  숨김해제
                </S.Button>
              </S.ButtonWrapper>
            </S.BoardItem>
          ))
        ) : (
          <S.Message>숨겨진 비디오 내역이 없습니다:)</S.Message>
        )}
      </S.HiddenVideoBoardList>
    </S.Container>
  );
};

export default Admin;
