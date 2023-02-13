import styled from "styled-components";

export const Text = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  border-bottom: 1px solid #272727;
  text-align: center;
  @media (max-width: 1070px) {
    text-align: start;
  }
`;
