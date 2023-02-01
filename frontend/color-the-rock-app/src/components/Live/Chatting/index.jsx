import React, { useEffect } from "react";
import * as S from "./style";
const Chatting = ({ messages }) => {
  useEffect(() => {
    console.log("[Chatting] messages", messages);
  }, [messages]);

  return (
    <S.ChattingContainer>
      <S.Title>채팅창</S.Title>
      <S.TextList>
        {messages &&
          messages.length > 0 &&
          messages.map((msg, index) => (
            <S.Text key={index}>
              {msg.userName}:{msg.text}
            </S.Text>
          ))}
      </S.TextList>
    </S.ChattingContainer>
  );
};

export default Chatting;
