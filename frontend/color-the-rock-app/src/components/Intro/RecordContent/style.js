import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  width: 100vw;
  flex-direction: row-reverse;
  justify-content: center;
  align-items: center;
  height: calc(var(--vh, 1vh) * 100);
  background-color: transparent;
  overflow-x: hidden;
  padding: 1rem;
  @media (max-width: 992px) {
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
  }
`;

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
`;
export const ImgWrapper = styled.div`
  width: 100%;
  max-width: 794px;
  height: 800px;
  margin-right: 2rem;
  background-color: var(--color-background);
  @media (max-width: 992px) {
    margin-right: 0;
    margin-top: 2rem;
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
