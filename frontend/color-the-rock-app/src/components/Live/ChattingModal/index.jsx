import React from "react";
import * as S from "./style";
import { HiOutlineArrowSmUp } from "react-icons/hi";
import { useInput } from "../../../hooks/useInput";

const ChattingModal = ({
  setShowChattingModal,
  getToken,
  session,
  messages,
}) => {
  const [message, setMessage] = useInput("");

  // 채팅 전송
  const sendMessage = () => {
    if (session !== undefined && message !== undefined) {
      const signalOptions = {
        data: JSON.stringify({ message: message }),
        type: "signal",
        to: [], // everyone
      };
      session.signal(signalOptions);
    }
  };

  return (
    <S.Container
      drag="y"
      dragMomentum={false}
      dragElastic={0}
      dragConstraints={{ top: 10 }}
      onDirectionLock={{ bottom: 0 }}
      onDrag={(_, info) => {
        console.log("onDrag", info.point.x, info.point.y);
        if (info.point.y >= 700) {
          setShowChattingModal(false);
        }
      }}
    >
      <S.InputWrapper>
        <ov-videoconference tokens={getToken} toolbarDisplaySessionName={false}>
          <S.CommentInput
            type="text"
            placeholder="댓글을 입력하세요."
            onChange={setMessage}
            value={message}
          />
        </ov-videoconference>
        <S.SendButton onClick={sendMessage}>
          <HiOutlineArrowSmUp size="1rem" color="#ffffff" />
        </S.SendButton>
      </S.InputWrapper>
      <S.ChattingWrapper>
        <S.ChattingList>
          {messages &&
            messages.length > 0 &&
            messages.map((item, index) => (
              <S.ChattingContent key={item + index}>
                <S.ChattingUserNickname>{item.userName}</S.ChattingUserNickname>
                <S.ChattingText>{item.text}</S.ChattingText>
              </S.ChattingContent>
            ))}
        </S.ChattingList>
      </S.ChattingWrapper>
    </S.Container>
  );
};
export default ChattingModal;
