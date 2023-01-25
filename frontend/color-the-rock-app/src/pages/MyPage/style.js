import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: transparent;
  padding: 0px 16px;
  display: flex;
  flex-direction: column;
  overflow-x: hidden;
`;

export const Title = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  border-bottom: 1px solid #272727;
`;

export const NicknameInput = styled.input`
  min-width: 100px;
  height: 2.5rem;
  font-style: normal;
  font-size: 1.125rem;
  line-height: 1.75rem;
  letter-spacing: -0.02em;
  outline: none;
  border: 1px solid var(--color-border);
  background-color: var(--color-background);
  border-radius: 20px;
  padding: 0px 1rem;
  color: var(--color-white);
`;

export const NickName = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 1.4rem;
  line-height: 1.75rem;
  letter-spacing: -0.02em;
  margin-right: 0.25rem;
  background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
`;
export const Text = styled.label`
  font-style: normal;
  font-weight: 500;
  font-size: 1rem;
  line-height: 1.5rem;
  letter-spacing: -0.01em;
  margin-right: 0.5rem;
`;
export const TextButton = styled.button`
  font-style: normal;
  font-weight: 400;
  font-size: 0.875rem;
  line-height: 1.5rem;
  text-align: center;
  letter-spacing: -0.01em;
  color: var(--color-secondary);
`;
export const ButtonWrapper = styled.div`
  width: 3.375rem;
  display: flex;
  justify-content: space-between;
`;
export const InfoWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1rem 0px;
`;

export const RadioLabel = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.625rem;
  margin-bottom: 1rem;
  color: ${(props) =>
    props.checked ? "var(--color-white)" : "var(--color-tertiary)"};

  &:first-child {
    margin-right: 1rem;
  }
`;
export const RadioButton = styled.input`
  appearance: none;
`;

export const RadioGroup = styled.div`
  display: flex;
  margin-top: 1rem;
`;
