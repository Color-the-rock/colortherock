import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  left: 0px;
  right: 0px;
  top: 0px;
  bottom: 0px;
  display: flex;
  justify-content: center;
  z-index: 1000;
  background-color: var(--color-dark);
`;

export const Container = styled.div`
  position: relative;
  width: 100vw;
  height: 100vh;
  max-width: 600px;
`;

export const Background = styled.div`
  width: 100vw;
  height: 100vh;
  z-index: 1000;
  opacity: 30%;
  background-color: var(--color-dark);
`;

export const ContentBox = styled.div`
  position: absolute;
  left: 1rem;
  right: 1rem;
  top: 1rem;
  bottom: 1rem;
  padding: 1rem;
  border-radius: 10px;
  max-width: 600px;
  background-color: var(--color-dark);
  z-index: 3000;
  max-width: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .webcam {
    width: auto;
    /* max-width: 600px; */
    height: calc(100% - 100px);
  }

  .camera {
    position: absolute;
    right: 1rem;
    top: calc(100% - 148px);
    z-index: 1;
  }
`;

export const ComponentWrap = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 1rem;
  justify-content: center;
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
