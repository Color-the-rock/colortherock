import styled from "styled-components";

export const VideoList = styled.div`
  width: 100%;
  margin: 1rem auto;
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
  margin-bottom: 100px;
`;

export const Label = styled.label`
  margin-left: 0.25rem;
  font-size: 0.8rem;
  color: var(--color-tertiary);
  margin-bottom: 10rem;
`;
