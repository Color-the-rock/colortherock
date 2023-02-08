import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
  background-color: transparent;
  overflow: hidden;
  padding: 1rem;
  justify-content: center;
  align-items: center;
  @media (max-width: 992px) {
    justify-content: flex-start;
    align-items: flex-start;
  }
`;

export const Text = styled.p`
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 24px;
  letter-spacing: -0.01em;
  color: var(--color-tertiary);
`;

export const BoardImg = styled.img`
  width: 100vw;
  height: auto;
  opacity: 0.6;
  @media (max-width: 992px) {
    width: 320vw;
  }
`;

export const Wrapper = styled.div`
  margin-top: 2rem;
  background-color: transparent;
`;
