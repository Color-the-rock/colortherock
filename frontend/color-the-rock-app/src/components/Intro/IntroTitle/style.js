import styled from "styled-components";

export const Title = styled.h1`
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 700;
  font-size: 24px;
  line-height: 34px;
  letter-spacing: -0.01em;
  color: var(--color-primary);
  margin: ${(props) => (props.margin ? props.margin : "1rem 0")};
`;

export const Dot = styled.span`
  color: var(--color-brand-primary);
`;
