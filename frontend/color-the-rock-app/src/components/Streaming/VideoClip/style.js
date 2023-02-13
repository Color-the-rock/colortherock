import styled from "styled-components";
import { VscChromeClose } from "react-icons/vsc";

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

export const ContentBox = styled.div`
  width: calc(100% - 32px);
  /* width: 70%; */
  height: 80%;
  padding: 2rem 1rem 1rem 1rem;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* background-color: var(--color-background); */
  background-color: white;
  position: relative;

  border-radius: 10px;
`;

export const VideoList = styled.ul`
  width: 100%;
  color: black;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  /* overflow: scroll; */
  margin-top: 2rem;
  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const VideoWrap = styled.li`
  width: 100%;
`;

export const ChromeClose = styled(VscChromeClose)`
  position: absolute;
  right: 1.5rem;
  top: 1.5rem;
  font-size: 1.5rem;
  z-index: 5000;
  color: black;
`;

export const Title = styled.div`
  position: absolute;
  left: 1.5rem;
  top: 1.5rem;
  font-size: 1.5rem;
  z-index: 5000;
  text-decoration: underline;
  color: black;
`;
