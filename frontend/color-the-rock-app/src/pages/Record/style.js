import { Calendar } from "react-calendar";
import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: transparent;
  padding: 0px 16px;
`;

export const Title = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
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

export const SubTitle = styled.h2`
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.625rem;
  margin-top: 2.25rem;
  margin-bottom: 1rem;
`;

export const RecordCalendar = styled(Calendar)`
  width: 100% !important;
  font-family: "Noto Sans KR";
  background-color: var(--color-background) !important;
  border: none !important;
  border-radius: 10px;
  color: #fff !important;
`;
