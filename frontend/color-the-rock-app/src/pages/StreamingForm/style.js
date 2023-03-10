import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  height: calc(var(--vh, 1vh) * 100);
  background-color: transparent;
  padding-bottom: 1rem;
  overflow-x: hidden;
  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const ContentWrap = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
`;

export const Content = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  padding: 0 1rem;
  background-color: transparent;

  @media (min-width: 992px) {
    width: 600px;
  }
  @media (max-width: 991px) {
    width: 100vw;
    height: calc(var(--vh, 1vh) * 100);
    min-width: 360px;
    max-width: 600px;
  }
  .webcam {
    width: auto;
    height: calc(100% - 80px);
  }

  .camera {
    position: absolute;
    right: 1.4rem;
    bottom: 9.5rem;
    z-index: 1;
    width: 2.5rem;
    height: 2.5rem;
    background-color: rgba(255, 255, 255, 0.2);
    border-radius: 50%;
    padding: 0.4rem;
    text-align: center;
  }

  .switching-camera {
    position: absolute;
    right: 1.4rem;
    bottom: 6rem;
    z-index: 1;
    width: 2.5rem;
    height: 2.5rem;
    background-color: rgba(255, 255, 255, 0.2);
    border-radius: 50%;
    padding: 0.5rem;
    text-align: center;
  }
`;

export const CaptureWrap = styled.div`
  position: absolute;
  border: 1px solid var(--color-border);
  border-radius: 10px;
  left: 1rem;
  top: calc(100% - 296px);

  img {
    width: auto;
    height: 180px;
    border-radius: 10px;
  }
`;

export const OverlapContent = styled.div`
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  padding: 2rem;
`;

export const ArrowLeftBtnWrap = styled.span`
  z-index: 1;
  font-size: 2rem;
`;

export const TitleWrap = styled.div`
  z-index: 1;
  border-radius: 20px;
`;

export const ComponenentWrap = styled.div`
  width: 100%;
  margin-top: 1rem;
  background-color: transparent;
`;

export const AddPadding = styled.div`
  /* padding-left: 1rem;
  padding-right: 1rem; */
`;

export const IconWrap = styled.span`
  align-items: center;
`;

export const SettingComponentWrap = styled.span`
  width: 100%;
  display: flex;
  align-items: center;
`;

export const CameraWrap = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
`;
