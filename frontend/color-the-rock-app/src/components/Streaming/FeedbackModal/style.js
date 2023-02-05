import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  z-index: 1000;
  background-color: white;
`;

export const Container = styled.div`
  position: relative;
  width: 100vw;
  height: 100%;
  max-width: 100vh;
`;

export const Background = styled.div`
  width: 100vw;
  height: 100%;
`;
