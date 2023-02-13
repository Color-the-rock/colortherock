import styled from "styled-components";
export const FooterContainer = styled.footer`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 120px;
  background-color: var(--color-background);
`;

export const CopyRight = styled.label`
  color: var(--color-tertiary);
  font-style: normal;
  font-weight: 400;
  font-size: 1rem;
  line-height: 1.5rem;
`;
