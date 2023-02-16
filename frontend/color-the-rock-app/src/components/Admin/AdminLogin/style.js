import styled from "styled-components";

export const LoginInput = styled.input`
  width: 100%;
  max-width: 320px;
  height: 40px;
  margin-bottom: 1rem;
  background: var(--color-background);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  padding: 0.5rem;
  padding-left: 1rem;
  color: var(--color-white);
`;

export const LoginButton = styled.div`
  font-size: 1.125rem;
  font-weight: 500;
  cursor: pointer;

  &:hover {
    color: var(--color-brand-primary);
  }
`;
