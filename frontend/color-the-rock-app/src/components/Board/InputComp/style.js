import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  height: 40px;
  color: var(--color-secondary);
  background-color: var(--color-background);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  max-width: 600px;
  opacity: ${(props) =>
    props.opacity !== "100" ? `${props.opacity}%` : "100%"};
`;
export const InputContent = styled.input`
  border: none;
  width: 100%;
  height: 100%;
  padding: 0 1rem;
  border-radius: 20px;
  background-color: var(--color-background);
  color: var(--color-tertiary);
  letter-spacing: -0.01em;
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 1.5rem;
`;
