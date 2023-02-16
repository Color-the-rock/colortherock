import { motion } from "framer-motion";
import styled from "styled-components";

export const Container = styled(motion.div)`
  display: flex;
  width: 100vw;
  justify-content: center;
  align-items: center;
  height: calc(var(--vh, 1vh) * 100);
  background-color: transparent;
  overflow: hidden;
  padding: 1rem;
  @media (max-width: 992px) {
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
  }
`;

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  @media (max-width: 992px) {
    align-items: flex-start;
  }
`;

export const ImgWrapper = styled.div`
  width: 100%;
  max-width: 794px;
  height: 800px;
  margin-left: 2rem;
  background-color: transparent;
  overflow: hidden;

  @media (max-width: 992px) {
    margin-left: 0;
    margin-top: 2rem;
  }
`;

export const Text = styled.p`
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 24px;
  letter-spacing: -0.01em;
  color: var(--color-tertiary);
`;

export const ImgAnimation = styled(motion.div)`
  width: 100%;
  height: 100%;
  position: relative;
  background-color: transparent;
  bottom: 0px;
`;

export const ImgBox = styled(motion.div)`
  width: 441px;
  height: 600px;
  background-image: ${(props) => (props.bg ? `url(${props.bg})` : "#ffffff")};
  background-repeat: no-repeat;
  background-position: center center;
  background-size: contain;
  border-radius: 20px;
  position: absolute;
  left: ${(props) => (props.left ? `${props.left}px` : "0")};
  bottom: ${(props) => (props.bottom ? `${props.bottom}px` : "0")};
  z-index: ${(props) => (props.depth ? props.depth : "0")};
`;

export const ImgBoxMobile = styled(motion.div)`
  position: absolute;
  width: 36%;
  min-width: 200px;
  height: 500px;
  background-color: ${(props) => (props.bg ? props.bg : "#ffffff")};
  border-radius: 20px;
  border-bottom-left-radius: 0px;
  border-bottom-right-radius: 0px;
  right: ${(props) => (props.right ? `${props.right}%` : "0")};
  bottom: 0px;
  &:last-child {
    border-top-right-radius: 0px;
  }
`;
