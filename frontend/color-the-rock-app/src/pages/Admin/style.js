import styled from "styled-components";

export const NoAdmin = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
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
