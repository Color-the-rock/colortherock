import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  left: 0px;
  right: 0px;
  top: 0px;
  bottom: 0px;
  z-index: 1000;
`;

export const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: transparent;
`;

export const ContentBox = styled.span`
  width: calc(100% - 32px);
  width: 70%;
  height: 40%;
  padding: 2rem 1rem 1rem 1rem;
  /* border: 1px solid white; */
  border-radius: 20px;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: var(--color-background);
`;

export const TitleWrap = styled.div`
  font-size: 20px;
  text-align: left;
`;

export const ComponentWrap = styled.div`
  width: 100%;
  height: 40px;
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
