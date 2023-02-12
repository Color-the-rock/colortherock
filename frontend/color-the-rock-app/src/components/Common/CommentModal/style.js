import styled from "styled-components";

export const Container = styled.div`
  transition: 1000;
  position: relative;
  left: 0;
  top: 0;
  width: 100%;
  height: 50vh;
  background-color: var(--color-border);
  border-radius: 10px 10px 0 0;
  background: #272727;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.7);
`;

export const CommentWrap = styled.div`
  height: 100%;
`;

export const OrnamentWrap = styled.div`
  height: 0.75rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Ornament = styled.div`
  width: 20vw;
  height: 3px;
  border-radius: 1px;
  background-color: white;
  border: 1px solid white;
`;

export const CloseBtnWrap = styled.div`
  margin: 0.25rem 1rem 0.25rem 1rem;
  display: flex;
  justify-content: space-between;
`;

export const CommentBtnWrap = styled.div`
  margin: 1rem;
  height: 2.25rem;
`;

export const CommentListWrap = styled.div`
  padding: 0 1rem;
  background: var(--color-background);
  overflow-y: scroll;
`;

export const CloseButton = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-end;
`;
