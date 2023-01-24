import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: transparent;
  padding: 0px 16px;
`;

export const Title = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  border-bottom: 1px solid #272727;
`;

export const LiveTag = styled.span`
  width: 3.25rem;
  height: 1.75rem;
  border-radius: 4px;
  padding: 4px;
  background-color: var(--color-badge-live);
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.75rem;
  text-align: center;
`;

export const Description = styled.div`
  font-style: normal;
  font-weight: 400;
  font-size: 0.875rem;
  line-height: 1.25rem;
  text-align: center;
  letter-spacing: -0.01em;
  margin: 1.25rem 0px;
  color: var(--color-tertiary);
`;
