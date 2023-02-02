import styled from "styled-components";
import { motion } from "framer-motion";

export const InputWrapper = styled.div`
  display: flex;
  position: absolute;
  bottom: 80px;
  right: 16px;
`;

export const Input = styled.input`
  width: 400px;
  height: 40px;
`;

export const Container = styled.div`
  // common
  display: flex;
  width: 100%;
  height: 100vh;
  overflow: hidden;

  // web
  align-items: center;
  justify-content: space-between;
  background-color: transparent;

  @media (max-width: 992px) {
    position: relative;
    flex-direction: column;
  }
  // mobile
`;
export const OwnerVideoWrapper = styled.div`
  position: absolute;
  top: 0px;
  width: 50%;

  // common
  @media (max-width: 992px) {
    width: 100%;
    height: 100%;
  }
`;

export const VideoMenu = styled.div`
  z-index: 100;
  position: absolute;
  top: 32px;
  right: 16px;
  width: 2.5rem;
  min-height: 200px;
  background-color: transparent;
`;

export const VideoMenuItem = styled.div`
  display: inline-block;
  width: 2.25rem;
  height: 2.25rem;
  background-color: rgba(255, 255, 255, 0.2);
  text-align: center;
  line-height: 2.25rem;
  border-radius: 50%;
  margin: 0.65rem 0;
  &:last-child {
    margin-right: 0;
  }
`;

export const CommentWrapper = styled.div`
  height: 100vh;
  display: flex;
  justify-content: space-between;
  margin-right: 1rem;
  @media (max-width: 992px) {
    width: calc(100% - 132px);
    max-width: 13.625rem;
  }
`;

export const VideoSettingWrapper = styled.div`
  position: absolute;
`;

export const SettingWrapper = styled.div`
  position: fixed;
  bottom: 0px;
  width: 100%;
  height: 66px;
  padding: 1rem;
  display: flex;
  justify-content: space-around;
  background-color: var(--color-background);
`;

export const SettingMenuItem = styled.div`
  display: inline-block;
  width: 2.25rem;
  height: 2.25rem;
  background-color: var(--color-border);
  border-radius: 50%;
  margin: 0 0.25rem;
  &:last-child {
    margin-right: 0;
  }
`;

export const DragModal = styled(motion.div)`
  position: absolute;
  bottom: 0px;
  width: 100%;
  height: 60vh;
`;

export const CommentModalWrap = styled(motion.div)`
  position: absolute;
  bottom: 0px;
  width: 100%;
  padding-top: 2rem;
  z-index: 100;
`;
