import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  height: 35vh;
  width: 100%;
  min-width: 328px;
  max-width: 600px;
  border: 1px solid var(--color-border);
  border-radius: 20px;
  margin-bottom: 8px;
  background-color: #000000;
`;

export const UploadArea = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  background-color: var(--color-background);
  border-radius: 20px;
  z-index: 1500;
  text-align: center;
`;

export const VideoWrap = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
  display: flex;
  flex-direction: row-reverse;

  .cancelVideo {
    position: absolute;
    right: 16px;
    top: 16px;
    font-size: 1.5rem;
  }

  video {
    width: 100%;
    height: 100%;
    border-radius: 20px;
    border: none;
  }
`;

export const Input = styled.input`
  width: 100%;
  height: 100%;
  overflow: hidden;
`;
export const InputWrap = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
  background-color: transparent;

  label {
    position: absolute;
    transform: translateY(-50%);
    left: 0;
    top: 50%;
    width: 100%;
    height: auto;
    font-size: 1.5rem;
  }

  input {
    appearance: none;
    display: none;
    width: 100%;
    height: 100%;
  }
`;
