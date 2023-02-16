import styled, { keyframes, css } from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
  background-color: transparent;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  margin-top: 10rem;
  @media (max-width: 992px) {
    justify-content: flex-start;
    align-items: flex-start;
    margin-top: 1rem;
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
  padding: 0 1rem;
`;

export const BoardImg = styled.img`
  width: 100vw;
  height: 50vh;
  opacity: 0.6;
  @media (max-width: 768px) {
    width: 320vw;
    height: 40vh;
  }
`;

export const Wrapper = styled.div`
  height: 100vh;
  background-color: transparent;
`;
export const ThumbnailContainer = styled.div`
  width: 100%;
  height: 100vh;
  overflow: hidden;
  margin-top: 2rem;
`;

export const ContentWrapper = styled.div`
  background-color: transparent;
`;

const rotateBooks = keyframes`
    0% {
      background-position: 0px;
    }
    100% {
      background-position: -3000px;
    }
`;

const rotateMovies = keyframes`
    0% {
      background-position: -3000px;
    }
    100% {
      background-position: 0px;
    }
`;

export const Books = styled.div`
  height: 300px;
  margin-bottom: -100px;
  background-position: 0;
  background: url(${(props) => (props.contents ? props.contents : "")}) 0/1000px
    repeat-x;
  background-size: 1200px;
  background-position: 0 0;
  margin: 0.1rem 0;
  animation: ${(props) =>
    props.type === "book"
      ? css`
          ${rotateBooks} 100s linear infinite
        `
      : css`
          ${rotateMovies} 100s linear infinite
        `};
`;
