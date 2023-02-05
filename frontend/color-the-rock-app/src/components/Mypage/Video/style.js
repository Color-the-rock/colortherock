import styled from "styled-components";

export const Container = styled.div`
  max-width: 6.75rem;
  min-height: 10.75rem;
  border-radius: 10px;
  background-color: var(--color-background);
`;

export const ThumbnailImg = styled.img`
  width: 100%;
  min-height: 10.188rem;
  border-radius: 10px;
`;

export const Text = styled.label`
  display: block;
  font-style: normal;
  font-weight: 700;
  font-size: 0.75rem;
  line-height: 1rem;
  letter-spacing: -0.005em;
  margin: 0.5rem 0px;
`;
export const Tag = styled.label`
  font-style: normal;
  font-weight: 400;
  font-size: 0.75rem;
  line-height: 1rem;
  border-radius: 10px;
  padding: 0.15rem 0.75rem;
  background-color: var(--color-background);
`;
