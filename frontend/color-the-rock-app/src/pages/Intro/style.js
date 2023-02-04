import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  overflow-y: scroll;
  background-color: transparent;
  position: relative;
`;

export const ContentWrapper = styled.div`
  position: relative;
  width: 100%;
  overflow-x: hidden;
  overflow-y: hidden;
`;

export const Section = styled.section`
  height: 100%;
  padding-top: 30vh;
  padding-bottom: 30vh;
  position: relative;
`;

export const Parallax = styled.div`
  overflow: hidden;
  letter-spacing: -2px;
  line-height: 0.8;
  margin: 0;
  white-space: nowrap;
  display: flex;
  flex-wrap: nowrap;

  .scroller {
    font-weight: 600;
    text-transform: uppercase;
    font-size: 9rem;
    font-family: "Poppins";
    font-style: italic;
    display: flex;
    white-space: nowrap;
    display: flex;
    flex-wrap: nowrap;
    margin-bottom: 2rem;
    @media (max-width: 992px) {
      font-size: 7rem;
    }
  }

  span {
    display: block;
    margin-right: 30px;
  }
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
  top: 128px;
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
  @media (max-width: 1070px) {
    text-align: start;
  }
`;
