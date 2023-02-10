import styled from "styled-components";
import { FiArrowUpCircle } from "react-icons/fi";

export const Container = styled.div`
  width: 100%;
  background-color: transparent;
  position: relative;
  overflow: hidden;
  margin-top: 3rem;
`;

export const ContentWrapper = styled.div`
  position: relative;
  width: 100%;
  overflow-x: hidden;
  overflow-y: hidden;
  margin: 2rem 0;
`;

export const Section = styled.section`
  height: 100%;
  padding-top: 30vh;
  padding-bottom: 30vh;
  position: relative;
`;

export const BoardContent = styled.div`
  width: 100%;
  height: 100vh;
  background-color: transparent;
`;

export const BoardImg = styled.img`
  width: 100vw;
  height: 100%;
  opacity: 0.6;
  @media (max-width: 992px) {
    width: 140vw;
  }
`;

export const BoardText = styled.h1`
  position: absolute;
  width: 100%;
  top: 112px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  text-align: center;

  p {
    text-align: center;
  }
  @media (max-width: 992px) {
    text-align: start;
  }
`;

export const BackToTop = styled(FiArrowUpCircle)`
  position: fixed;
  right: 32px;
  bottom: 16px;
  width: 40px;
  height: 40px;
  color: var(--color-tertiary);
  background-color: transparent;
  cursor: pointer;
  @media (max-width: 992px) {
    right: 16px;
  }
`;
