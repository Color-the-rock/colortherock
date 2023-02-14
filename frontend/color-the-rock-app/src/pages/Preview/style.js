import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  height: 100vh;
  background-color: transparent;
  padding: 0 16px;
  margin-top: 5rem;
  margin-bottom: 1rem;
  overflow-x: hidden;
  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const Video = styled.video`
  width: 100%;
  height: calc(var(--vh, 1vh) * 100);
  object-fit: cover;
`;
