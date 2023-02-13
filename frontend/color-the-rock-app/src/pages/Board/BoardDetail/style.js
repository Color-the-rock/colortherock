import styled, { keyframes } from "styled-components";
import { FiSettings, FiMoreVertical } from "react-icons/fi";
import { Link } from "react-router-dom";

const slideIn = keyframes`
  from {
    margin-top: 100%;
  }
  to {
    margin-top: 0%;
  }
`;

export const Settings = styled(FiSettings)`
  right: 16px;
  top: 16px;
  z-index: 2;
  font-size: 24px;
`;

export const ContainerWrap = styled.div`
  position: relative;
  width: 100%;
  height: calc(var(--vh, 1vh) * 100);
  background-color: transparent;

  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const HeaderWrap = styled.div`
  height: calc(var(--vh, 1vh) * 10);
`;

export const Container = styled.div`
  width: 100vw;
  height: calc(var(--vh, 1vh) * 90);
`;

export const ContentContainer = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
`;

export const ContentWrap = styled.div`
  width: 100%;
  height: calc(var(--vh, 1vh) * 90);
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 360px;
  max-width: 600px;
  position: relative;

  .fiSettings {
    position: absolute;
    right: 1rem;
    top: 1rem;
    z-index: 1111;
    font-size: 1.5rem;
  }
`;

export const ArrowLeftBtnWrap = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-around;
  background-color: transparent;
  max-width: 600px;
  margin: 0 auto;
  padding-left: 1rem;
`;

export const BoardSettingBtn = styled(FiMoreVertical)`
  margin-top: 1rem;
  font-size: 2rem;
  background-color: transparent;
  @media (max-width: 992px) {
    margin-right: 1rem;
  }
`;

export const VideoWrap = styled.div`
  display: block;
  position: relative;
  width: 100%;
`;

export const Video = styled.video`
  width: 100%;
  height: 37.5rem;
  object-fit: cover;
`;

export const ComponentWrap = styled.div`
  width: 100%;
  padding: 1rem;
`;

export const FalseWrap = styled.div`
  width: 100%;
  min-width: 328px;
  max-width: 600px;
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
  grid-gap: 1rem;
`;

export const CommentModalWrap = styled.div`
  width: 100%;
  height: 50vh;
  padding-top: 5px;
  background-color: transparent;
  animation-duration: 1s;
  animation-name: ${slideIn};
`;

export const CommentWrap = styled.div`
  width: 100%;
  padding-left: 1rem;
  padding-right: 1rem;
  height: 40px;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
`;

export const ThumbnailList = styled.div`
  align-self: center;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 1rem;
  padding-left: 1rem;
  padding-right: 1rem;

  @media (max-width: 1070px) {
    grid-template-columns: 1fr 1fr;
  }
`;

export const RowWrap = styled.div`
  display: flex;
  justify-content: space-between;
  padding-bottom: 0.5rem;
`;

export const SettingModal = styled.ul`
  display: flex;
  flex-direction: column;
  position: absolute;
  top: 64px;
  right: 16px;
  background-color: var(--color-background);
  z-index: 1000;
  padding: 0.25rem;
  width: 160px;
  /* height: 140px; */
  border-radius: 20px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.2);
`;

export const SettingModalItem = styled.li`
  display: ${(props) => (props.isSameAuthor ? "flex" : "none")};
  background-color: transparent;
  height: 36px;
  margin: 0.5rem 1rem;
  align-items: center;
`;

export const SettingModalReportItem = styled.li`
  display: ${(props) => (props.isSameAuthor ? "none" : "flex")};
  background-color: transparent;
  height: 36px;
  margin: 0.5rem 1rem;
  align-items: center;
`;

export const DetailInfo = styled.label`
  font-style: normal;
  font-weight: 400;
  font-size: 0.75rem;
  line-height: 1rem;
  letter-spacing: -0.005em;
  color: ${(props) => (props.color ? props.color : "var(--color-white)")};
`;

export const SLink = styled(Link)`
  width: 100%;
`;
