import styled from "styled-components";
import { VscChromeClose } from "react-icons/vsc";
export const ContainerWrap = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1000;
  background-color: black;
  opacity: 0.3;
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
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;

  /* #canvas {
    overflow: hidden;
    opacity: 0.7;
    width: 100%;
    height: 100%;
    z-index: 4000;
  } */
`;

export const Canvas = styled.canvas`
  overflow: hidden;
  opacity: 0.7;
  width: 100%;
  height: 100%;
  z-index: 4000;
`;

export const ChromeClose = styled(VscChromeClose)`
  position: absolute;
  right: 1rem;
  top: 1rem;
  font-size: 2rem;
  z-index: 5000;
`;
