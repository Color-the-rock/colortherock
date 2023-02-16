import React from "react";
import PropTypes from "prop-types";
import * as S from "./style";
import AdminTitle from "../AdminTitle";

const AdminReportModal = ({ reportDetail, setOpenModal, setReportDetail }) => {
  return (
    <S.ModalBackground onClick={() => setOpenModal(false)}>
      <S.ReportModal>
        <AdminTitle text="신고 내역" />
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
  );
};

export default AdminReportModal;

AdminReportModal.propTypes = {
  reportDetail: PropTypes.array,
  setOpenModal: PropTypes.func,
  setReportDetail: PropTypes.func,
  getAllHiddenBoardList: PropTypes.func,
};
