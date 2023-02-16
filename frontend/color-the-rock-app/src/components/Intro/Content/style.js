import styled from "styled-components";
import bgImg from "../../../assets/img/intro/bg-intro.png";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 100vw;
  height: calc(var(--vh, 1vh) * 100);
  background: ${(props) => (props.bg ? `url(${props.bg})` : `url(${bgImg})`)};
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  justify-content: center;
  overflow-x: hidden;
  padding: 1rem;
  padding-right: 10rem;
  @media (max-width: 992px) {
    align-items: center;
    padding-right: 1rem;
  }
`;

export const Text = styled.p`
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 500;
  font-size: 18px;
  line-height: 28px;
  letter-spacing: -0.02em;
  color: var(--color-primary);
  margin-top: 1rem;
  margin-right: 15rem;
  @media (max-width: 992px) {
    margin-right: 0;
  }
`;
