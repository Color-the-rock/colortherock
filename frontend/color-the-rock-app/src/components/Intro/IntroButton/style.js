import { Link } from "react-router-dom";
import styled from "styled-components";

export const Button = styled(Link)`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 10.25rem;
  max-width: 600px;
  height: 50px;
  border: 2px solid transparent;
  border-radius: 40px;
  position: relative;
  background-image: linear-gradient(
      var(--color-background),
      var(--color-background)
    ),
    linear-gradient(to right, #ff6cab, #8533ff);
  background-origin: border-box;
  background-clip: content-box, border-box;
  margin-top: 1rem;
  cursor: pointer;
`;

export const GradientText = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 28px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: -0.02em;
  background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
  padding: 0.5rem 0;
  cursor: pointer;
`;
