import styled from "styled-components";

export const VideoList = styled.div`
  width: 100%;
  margin: 0 auto;
  max-width: 358px;
  display: grid;
  gap: 1rem;
  align-self: center;
  grid-template-columns: 1fr 1fr 1fr;
`;

export const Description = styled.div`
  width: 240px;
  font-style: normal;
  font-weight: 400;
  font-size: 0.875rem;
  line-height: 1.25rem;
  letter-spacing: -0.01em;
  color: var(--color-tertiary);
`;
