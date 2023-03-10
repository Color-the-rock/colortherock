import styled from "styled-components";

export const Video = styled.video`
  object-fit: cover;
  width: 100%;
  height: calc(var(--vh, 1vh) * 100);
  color: #fff;
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  border-radius: var(--ov-video-radius);
  background-color: #000;
`;
