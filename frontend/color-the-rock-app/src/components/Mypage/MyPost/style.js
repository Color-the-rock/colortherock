import styled from "styled-components";

export const VideoList = styled.div`
  max-width: 600px;
  display: grid;
  gap: 1rem;
  align-self: center;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  @media (max-width: 1070px) {
    grid-template-columns: 1fr 1fr 1fr;
  }
`;
