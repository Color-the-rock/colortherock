import styled from "styled-components";
import { motion } from "framer-motion";
import { HiOutlineDotsVertical } from "react-icons/hi";

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
  position: absolute;
  bottom: 84px;
  right: 16px;
  min-height: 200px;
  background-color: transparent;
`;

export const VideoMenuItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  margin: 0.65rem 0;
  font-style: normal;
  font-weight: 400;
  font-size: 0.75rem;
  line-height: 1rem;
  text-align: center;
  letter-spacing: -0.005em;
`;

export const CommentWrapper = styled.div`
  height: 100vh;
  display: flex;
  justify-content: space-between;
  margin-right: 1rem;
  @media (max-width: 992px) {
    width: 100%;
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

export const StreamTitle = styled.h1`
  position: absolute;
  top: 24px;
  left: 16px;
  font-size: 1.25rem;
`;

export const VideoSettingsIcon = styled(HiOutlineDotsVertical)`
  position: absolute;
  top: 16px;
  right: 16px;
  background-color: transparent;
  z-index: 100;
  padding: 0.25rem;
  width: 36px;
  height: 36px;
  border-radius: 50%;
`;

export const VideoSettingsMenu = styled.ul`
  display: flex;
  flex-direction: column;
  position: absolute;
  top: 64px;
  right: 16px;
  background-color: var(--color-background);
  z-index: 1000;
  padding: 0.25rem;
  width: 160px;
  height: 200px;
  border-radius: 20px;
`;

export const VideoSettingsMenuItem = styled.li`
  display: flex;
  background-color: transparent;
  height: 36px;
  margin: 0.5rem 1rem;
  align-items: center;
`;

export const MenuTitle = styled.label`
  font-size: 1rem;
  margin-left: 1rem;
`;

export const IconWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 2.25rem;
  height: 2.25rem;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 50%;
  margin: 0.65rem 0;
`;
