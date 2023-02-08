import { Link } from "react-router-dom";
import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  height: calc(var(--vh, 1vh) * 100);
  background-color: var(--color-dark);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Guide = styled.p`
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 30px;
  text-align: center;
  letter-spacing: -0.01em;
  color: var(--color-primary);
  margin-top: 1rem;
`;

export const SLink = styled(Link)`
  height: 24px;
  display: flex;
  align-items: center;
  font-style: normal;
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 28px;
  letter-spacing: -0.02em;
  margin-top: 1rem;
  color: var(--color-primary);

  &:hover {
    background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-fill-color: transparent;
  }
`;
