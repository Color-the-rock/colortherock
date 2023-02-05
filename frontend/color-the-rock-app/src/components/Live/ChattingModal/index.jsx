import React from "react";
import * as S from "./style";
import { HiOutlineArrowSmUp } from "react-icons/hi";
import { useState } from "react";
import { useMediaQuery } from "react-responsive";

const ChattingModal = ({
  setShowChattingModal,
  getToken,
  session,
  messages,
  isShowChattingModal,
}) => {
  const isMobile = useMediaQuery({ maxWidth: 767 });
  const isTablet = useMediaQuery({ minWidth: 768, maxWidth: 991 });

  const [message, setMessage] = useState("");

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
    setMessage(""); // 입력값 초기화
  };

  const handlePressEnter = () => {
    if (window.event.keyCode === 13) {
      sendMessage();
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
        if (isMobile && info.point.y >= 600) {
          setShowChattingModal(false);
        } else if (isTablet && info.point.y >= 960) {
          setShowChattingModal(false);
        }
      }}
    >
      <S.InputWrapper>
        <ov-videoconference
          id="ov-videoconference"
          tokens={getToken}
          toolbarDisplaySessionName={false}
        >
          <S.CommentInput
            type="text"
            placeholder="댓글을 입력하세요."
            onChange={(e) => setMessage(e.target.value)}
            value={message}
            onKeyUp={handlePressEnter}
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
