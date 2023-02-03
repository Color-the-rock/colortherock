import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: transparent;

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
  @media (min-width: 992px) {
    width: 600px;
  }
  @media (max-width: 991px) {
    width: 100vw;
    height: 100vh;
    min-width: 360px;
    max-width: 600px;
  }
  .webcam {
    width: auto;
    max-width: 600px;
    height: calc(100% - 100px);
  }

  .camera {
    position: absolute;
    right: 1rem;
    top: calc(100% - 148px);
    z-index: 1;
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
  padding: 1rem;
`;

export const ArrowLeftBtnWrap = styled.span`
  /* position: absolute; */
  /* left: 16px; */
  /* top: 16px; */
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
`;

export const AddPadding = styled.div`
  padding-left: 1rem;
  padding-right: 1rem;
`;

export const IconWrap = styled.span`
  /* display: flex; */
  align-items: center;
`;

export const SettingComponentWrap = styled.span`
  width: 100%;
  display: flex;
  align-items: center;
`;
