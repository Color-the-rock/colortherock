import styled from "styled-components";

export const VideoList = styled.div`
  width: 100%;
  margin: 0 auto;
  display: grid;
  align-self: center;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  background-color: transparent;
  @media (max-width: 992px) {
    grid-template-columns: 1fr 1fr 1fr;
    row-gap: 1rem;
  }
`;

export const Message = styled.p`
  width: 100%;
  text-align: center;
`;
