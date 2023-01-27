import { Calendar } from "react-calendar";
import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: transparent;
  padding: 0px 16px;
  margin-top: 5rem;
`;

export const Title = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  border-bottom: 1px solid #272727;
`;

export const TextWrapper = styled.div`
  margin: 32px 0px;
`;

export const GraphWrapper = styled.div`
  height: 300px;
  background-color: var(--color-background);
`;

export const Text = styled.p`
  width: 100%;
  text-align: center;
  font-style: normal;
  font-weight: 500;
  font-size: 1.125rem;
  line-height: 1.75rem;
  letter-spacing: -0.02em;
`;

export const GradientText = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.75rem;
  letter-spacing: -0.02em;
  background: linear-gradient(
    135deg,
    var(--color-brand-gradient-start) 0%,
    var(--color-brand-gradient-end) 100%
  );
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
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
  margin: 0;
`;

export const RadioGroup = styled.div`
  display: flex;
  margin-top: 1rem;
`;

export const VideoList = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
`;
