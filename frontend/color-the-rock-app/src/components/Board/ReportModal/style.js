import styled from "styled-components";
import { FiX } from "react-icons/fi";
export const ModalBackground = styled.div`
  width: 100%;
  height: 100%;
  z-index: 100;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const ModalContainer = styled.div`
  position: relative;
  width: 260px;
  height: 240px;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 1rem;
  background-color: var(--color-background);
`;

export const CloseButton = styled(FiX)`
  width: 1.6rem;
  height: 1.6rem;
  color: var(--color-primary);
  align-self: flex-end;
`;
export const Title = styled.h1`
  position: absolute;
  top: 8px;
  left: 16px;
  font-style: normal;
  font-weight: 500;
  font-size: 1rem;
  line-height: 1.25rem;
  letter-spacing: -0.01em;
  color: var(--color-primary);
  margin: 1rem 0;
`;
export const ReportList = styled.ul`
  margin-top: 1rem;
  list-style: none;
`;
export const ReportItem = styled.li`
  width: 100%;
  height: 2rem;
  display: flex;
  align-items: center;
  margin: 0.5rem 0;
`;

export const RadioButton = styled.input`
  appearance: none;
  width: 1rem;
  height: 1rem;
  background-color: var(--color-border);
  border-radius: 50%;
  margin-right: 1rem;
  &:checked {
    background-color: var(--color-brand-primary);
    border: 2px solid var(--color-brand-shade);
  }
`;
export const RadioLabel = styled.label`
  font-style: normal;
  font-weight: 400;
  font-size: 1rem;
  line-height: 1.25rem;
  letter-spacing: -0.01em;
  color: var(--color-secondary);
`;

export const Button = styled.button`
  position: absolute;
  bottom: 16px;
  right: 16px;
  font-style: normal;
  font-weight: bold;
  font-size: 1rem;
  line-height: 1rem;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: -0.02em;
  background: ${(props) =>
    props.disabled === "disabled"
      ? "var(--color-tertiary)"
      : "linear-gradient(135deg, #ff6cab 0%, #8533ff 100%)"};
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
  cursor: pointer;
`;

export const GradientText = styled.label``;
