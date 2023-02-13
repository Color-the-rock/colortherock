import styled from "styled-components";

export const NoAdmin = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.h1`
  width: 100%;
  text-align: center;
  font-size: 1.125rem;
  font-weight: bold;
  margin-bottom: 2rem;
`;
export const LoginInput = styled.input`
  width: 100%;
  max-width: 320px;
  height: 40px;
  margin-bottom: 1rem;
  background: var(--color-background);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  padding: 0.5rem;
  padding-left: 1rem;
  color: var(--color-white);
`;

export const LoginButton = styled.div`
  font-size: 1.125rem;
  font-weight: 500;
  cursor: pointer;

  &:hover {
    color: var(--color-brand-primary);
  }
`;

export const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100vh;
  background-color: var(--color-dark);
  padding: 1rem;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const HiddenVideoBoardList = styled.ul`
  width: 100%;
  max-width: 600px;
  height: 100%;
  background-color: var(--color-background);
  overflow-y: scroll;
`;

export const BoardItem = styled.li`
  display: flex;
  margin: 1rem;
  background-color: transparent;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  font-size: 1rem;
  font-weight: 500;
  border-bottom: 1px dashed var(--color-border);
  cursor: pointer;
`;

export const BoardTitle = styled.h1`
  font-size: 1rem;
  font-weight: bold;
`;

export const ButtonWrapper = styled.div`
  display: flex;
  justify-content: space-around;
  background-color: transparent;
`;

export const Button = styled.button`
  width: 60px;
  height: 36px;
  color: var(--color-primary);
  background-color: transparent;
  margin: 0 0.25rem;
`;

export const Message = styled.p`
  width: 100%;
  text-align: center;
  margin-top: 2rem;
  color: var(--color-tertiary);
`;

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
