import styled from "styled-components";
import { motion } from "framer-motion";

export const Container = styled(motion.div)`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 600px;
  max-height: 65%;
  background-color: var(--color-background);
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  filter: drop-shadow(0px -4px 2px rgba(0, 0, 0, 0.25));
  padding: 1rem;
`;

export const ChattingWrapper = styled.div`
  width: 100%;
  height: 300px;
  overflow-y: scroll;
  background-color: transparent;
`;

export const InputWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 3rem;
  border-radius: 3rem;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.7);
  padding: 0.25rem 1rem;
  border: none;
  margin-bottom: 1rem;
`;

export const CommentInput = styled.input`
  width: calc(100% - 3.5rem);
  height: 100%;
  margin-right: 1rem;
  color: var(--color-white);
  background-color: var(--color-background);
  border: none;
`;

export const SendButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 1.75rem;
  height: 1.75rem;
  border-radius: 50%;
  border: none;
  color: var(--color-white);
  background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
`;

export const Test = styled.div`
  width: 100%;
  height: 100vh;

  background: #000;
  overflow-y: scroll;
`;

export const ChattingList = styled.ul`
  list-style: none;
  width: 100%;
  background-color: transparent;
`;

export const ChattingContent = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 1.25rem;
`;

export const ChattingUserNickname = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 0.813rem;
  line-height: 1.25rem;
  letter-spacing: -0.01em;
`;

export const ChattingText = styled.label`
  font-weight: 500;
  font-size: 0.813rem;
  line-height: 1.25rem;
  letter-spacing: -0.01em;
`;
