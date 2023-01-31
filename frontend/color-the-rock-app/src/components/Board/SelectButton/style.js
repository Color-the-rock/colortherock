import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  height: 40px;
  color: var(--color-secondary);
  background-color: var(--color-background);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  max-width: 300px;
`;

export const Select = styled.select`
  display: flex;
  border: none;
  width: 100%;
  height: 100%;
  padding: 16px;
  background-color: var(--color-background);
  color: var(--color-tertiary);
  letter-spacing: -0.01em;
  font-family: 'Noto Sans KR';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 1.5rem;
`;