import styled from "styled-components";

// 신고 모달
export const ModalBackground = styled.div`
  position: fixed;
  top: 0px;
  left: 0px;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 1000;
`;

export const ReportModal = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 360px;
  height: 360px;
  background-color: var(--color-background);
  z-index: 100;
  padding: 1rem;
  border-radius: 20px;
`;

export const ReportTable = styled.table`
  width: 100%;
`;

export const ReportThead = styled.thead`
  width: 100%;
  text-align: center;
  background-color: var(--color-border);
`;

export const ReportTr = styled.tr`
  width: 100%;
  height: 36px;

  th,
  td {
    border: 1px solid var(--color-border);
    padding: 10px;
    font-size: 0.95rem;
    font-weight: 400;
  }
`;
