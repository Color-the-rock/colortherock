import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  left: 0px;
  right: 0px;
  top: 0px;
  bottom: 0px;
  z-index: 1000;
  background-color: transparent;
`;

export const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
`;

export const Background = styled.div`
  position: absolute;
  top: 0;
  bottom: calc(40%);
  left: 0;
  right: 0;
  width: 100vw;
  height: 100%;
  z-index: 1000;
  opacity: 30%;
  background-color: var(--color-dark);
`;

export const ContentBox = styled.div`
  border-radius: 10px;
  background-color: var(--color-dark);
  background-color: white;
  border-radius: 20px;
  z-index: 3000;
  width: calc(100% - 32px);
  max-width: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const ComponentWrap = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 1rem;
  justify-content: center;
`;

export const SelectButtonWrap = styled.div`
  width: 100%;
  margin-bottom: 1rem;
`;

export const selectBtnContent = styled.span`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-self: center;
  grid-template-columns: 1fr 1fr;
  grid-gap: 0.5rem;
`;
