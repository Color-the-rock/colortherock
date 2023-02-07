import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: calc(var(--vh, 1vh) * 100);
  background-color: transparent;
  overflow-x: hidden;
  padding: 1rem;
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

export const Section = styled.section`
  height: 100%;
  padding: 1rem 0;
`;

export const BoardImg = styled.img`
  width: 280vw;
  height: 100%;
  opacity: 0.6;
`;
