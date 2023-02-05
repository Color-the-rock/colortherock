import styled from "styled-components";
import bgImg from "../../../assets/img/intro/bg-intro.png";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  height: 100vh;
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
    url(${bgImg});
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  justify-content: center;
  overflow-x: hidden;
  padding: 1rem;
`;

export const Text = styled.p`
  font-style: normal;
  font-weight: 700;
  font-size: 1.5rem;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
`;
