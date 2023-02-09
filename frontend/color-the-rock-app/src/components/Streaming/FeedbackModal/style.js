import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1000;
  background-color: red;
`;

export const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  /* background-color: transparent; */
`;

export const ContentBox = styled.div`
  width: calc(100% - 32px);
  max-width: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* background-color: transparent; */

  #canvas {
    overflow: hidden;
    opacity: 0.7;
    width: 100%;
    height: 100%;
    z-index: 5000;
  }
`;
