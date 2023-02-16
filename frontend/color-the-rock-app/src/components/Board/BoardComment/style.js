import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: var(--color-background);
  margin: 1rem 0px;
  padding: 0.5rem;
  border-radius: 10px;
`;

export const ContentWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: transparent;
`;
export const Content = styled.input`
  width: 100%;
  font-style: normal;
  font-weight: 400;
  font-size: 0.75rem;
  line-height: 1.5rem;
  letter-spacing: -0.01em;
  color: var(--color-secondary);
  background-color: transparent;
  border: none;
`;

export const UserNickName = styled.label`
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

export const ButtonWrapper = styled.span`
  display: flex;
  align-self: center;
  background-color: transparent;
`;

export const Button = styled.button`
  min-width: 35px;
  text-align: end;
  color: var(--color-white);
  &:first-child {
    margin-right: 0.25rem;
  }
`;
