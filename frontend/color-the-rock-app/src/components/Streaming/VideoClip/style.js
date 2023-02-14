import styled from "styled-components";
import { VscChromeClose } from "react-icons/vsc";

export const ContainerWrap = styled.div`
  position: fixed;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 2000;
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
  height: 80%;
  padding: 2rem 1rem 1rem 1rem;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background-color: var(--color-background);
  position: relative;
  border-radius: 10px;
`;

export const VideoList = styled.ul`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
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
  font-size: 1.25rem;
  z-index: 5000;
`;

export const Title = styled.div`
  position: absolute;
  left: 1.5rem;
  top: 1.5rem;
  font-size: 1.25rem;
  z-index: 5000;
  text-decoration: underline;
`;

export const Video = styled.video`
  margin-top: 2rem;
  width: auto;
  height: 90%;
  object-fit: cover;
  border-radius: 10px;
`;

export const Message = styled.label`
  font-size: 1rem;
  color: var(--color-tertiary);
`;
