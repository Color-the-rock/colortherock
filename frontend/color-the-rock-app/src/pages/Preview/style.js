import styled from "styled-components";

export const Container = styled.div`
  position: relative;
  width: 100%;
  height: calc(var(--vh, 1vh) * 100);
  background-color: transparent;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Video = styled.video`
  height: 100%;
  object-fit: contain;
  @media (max-width: 992px) {
    width: 90%;
    height: auto;
  }
`;

export const Button = styled.button`
  width: 72px;
  font-size: 1rem;
  color: var(--color-white);
  align-self: center;
  margin-top: 1rem;
`;

export const Wrapper = styled.div`
  position: absolute;
  top: 32px;
  display: flex;
  width: 100%;
  padding: 0 1rem;
  z-index: 100;
`;
