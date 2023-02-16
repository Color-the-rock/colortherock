import styled from "styled-components";

export const Container = styled.div`
  max-width: 600px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  z-index: 10;
  height: 40px;
  background-color: var(--color-background);
  color: var(--color-secondary);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  opacity: ${(props) =>
    props.opacity !== "100" ? `${props.opacity}%` : "100%"};
`;

export const InputWrap = styled.div``;

export const InputContent = styled.input`
  border: none;
  width: 100%;
  height: 100%;
  padding-left: 16px;
  background-color: var(--color-background);
  color: var(--color-tertiary);
  letter-spacing: -0.01em;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 1.5rem;
  border-radius: 20px;
`;
