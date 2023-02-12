import React, { useEffect, useState } from "react";
import { adminApi } from "../../api/admin";
import { useInput } from "../../hooks/useInput";
import * as S from "./style";

const Admin = () => {
  const [isAdmin, setAdmin] = useState(true);
  const [result, setResult] = useState([]);
  const [adminId, onChangeAdminId] = useInput("");
  const [adminPw, onChangeAdminPw] = useInput("");
  const [isOpenModal, setOpenModal] = useState(false);
  const [reportDetail, setReportDetail] = useState([]);
  // 관리자 로그인
  const handleAdminLogin = () => {
    if (!adminId || !adminPw) {
      alert("아이디와 비밀번호를 입력해주세요!");
      return;
    }

    const admin = {
      id: adminId,
      password: adminPw,
    };

    adminApi
      .loginAdmin(admin)
      .then(({ data }) => {
        console.log("관리자 로그인 성공", data.result);
        sessionStorage.setItem("adminToken", data.result);
        setAdmin(true);
      })
      .catch((error) => console.log("관리자 로그인 실패"));
  };

  // 관리자 숨김 영상 목록 조회
  const getAllHiddenBoardList = () => {
    adminApi
      .getHiddenVideoBoardList()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[getHiddenVideoBoardList] : ", _result);
          setResult(_result);
        }
      })
      .catch((error) => console.log("[getHiddenVideoBoardList] : ", error));
  };

  // 관리자 신고 내역 조회
  const getReportDetail = (id) => {
    setOpenModal(true);

    adminApi
      .getReportDetail(id)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[getHiddenVideoBoardList] : ", _result);
          setReportDetail(_result);
          setOpenModal(true);
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
          console.log("[deleteVideoBoard] : ", _result);
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
          console.log("[toggleHiddenVideoBoard] : ", _result);
          getAllHiddenBoardList();
        }
      })
      .catch((error) => console.log("[toggleHiddenVideoBoard] : ", error));
  };

  useEffect(() => {
    if (!isAdmin) return;
    getAllHiddenBoardList();
  }, [isAdmin]);

  return !isAdmin ? (
    <S.NoAdmin>
      <S.Title>ADMIN PAGE</S.Title>
      <S.LoginInput
        type="text"
        placeholder="ID"
        value={adminId}
        onChange={onChangeAdminId}
      />
      <S.LoginInput
        type="password"
        placeholder="Password"
        value={adminPw}
        onChange={onChangeAdminPw}
      />
      <S.LoginButton onClick={handleAdminLogin}>로그인</S.LoginButton>
    </S.NoAdmin>
  ) : (
    <S.Container>
      <S.Title>숨김영상목록</S.Title>

      {isOpenModal && (
        <S.ModalBackground onClick={() => setOpenModal(false)}>
          <S.ReportModal>
            <S.Title>신고 내역</S.Title>
            <div style={{ width: "100%", height: "272px", overflow: "auto" }}>
              <S.ReportTable>
                <S.ReportThead>
                  <S.ReportTr>
                    <th>사용자ID</th>
                    <th>신고유형</th>
                  </S.ReportTr>
                </S.ReportThead>
                <tbody>
                  {reportDetail &&
                    reportDetail.length > 0 &&
                    reportDetail.map((report, index) => (
                      <S.ReportTr key={report.memberId + index}>
                        <th>{report.memberId}</th>
                        <th>{report.category}</th>
                      </S.ReportTr>
                    ))}
                </tbody>
              </S.ReportTable>
            </div>
          </S.ReportModal>
        </S.ModalBackground>
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
