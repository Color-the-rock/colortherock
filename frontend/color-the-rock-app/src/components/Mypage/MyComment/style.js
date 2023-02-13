import { Link } from "react-router-dom";
import styled from "styled-components";

export const Container = styled(Link)`
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: var(--color-background);
  margin: 1rem 0px;
  padding: 1rem;
  border-radius: 10px;
`;
export const Title = styled.h1`
  font-style: normal;
  font-weight: 500;
  font-size: 1rem;
  line-height: 1.5rem;
  letter-spacing: -0.01em;
  color: var(--color-secondary);
`;
export const ContentWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
export const Content = styled.label`
  font-style: normal;
  font-weight: 400;
  font-size: 0.75rem;
  line-height: 1.5rem;
  letter-spacing: -0.01em;
  color: var(--color-secondary);
`;
export const createdDate = styled.label`
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;
  letter-spacing: -0.005em;
  color: var(--color-tertiary);
`;
