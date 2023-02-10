import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  top: 0px;
  left: 0px;
  right: 0px;
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

export const ContentBox = styled.ul`
  width: calc(100% - 32px);
  width: 70%;
  height: 50%;
  padding: 2rem 1rem 1rem 1rem;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: var(--color-background);
`;

export const ComponentWrap = styled.li``;
