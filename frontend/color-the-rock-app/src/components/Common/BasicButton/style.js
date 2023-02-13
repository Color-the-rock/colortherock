import styled from "styled-components";
import { Link } from "react-router-dom";

export const Button = styled(Link)`
  width: 100%;
  max-width: 600px;
  height: 3.375rem;
  background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
  border-radius: 50px;
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 3.375rem;
  text-align: center;
  letter-spacing: -0.02em;
  color: var(--color-white);
  margin-top: 2rem;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;
