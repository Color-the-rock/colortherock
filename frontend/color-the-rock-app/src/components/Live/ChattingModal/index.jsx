import React, { useEffect, useRef } from "react";
import * as S from "./style";
import { HiOutlineArrowSmUp } from "react-icons/hi";
import { useState } from "react";
import { useMediaQuery } from "react-responsive";

const ChattingModal = ({
  setShowChattingModal,
  getToken,
  session,
  messages,
  onSessionCreated,
}) => {
  const isMobile = useMediaQuery({ maxWidth: 767 });
  const isTablet = useMediaQuery({ minWidth: 768, maxWidth: 991 });
  const isDesktop = useMediaQuery({ minWidth: 992 });
  const scrollRef = useRef();
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
    if (message === "") return;

    if (window.event.keyCode === 13) {
      sendMessage();
    }
  };

  useEffect(() => {
    scrollRef.current.scrollIntoView({
      behavior: "smooth",
      block: "end",
      inline: "nearest",
    });
  }, [messages]);

  return (
    <S.Container
      drag={isMobile ? "y" : false}
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
      {isDesktop && <S.ChattingTitle>채팅창</S.ChattingTitle>}
      {isMobile && (
        <S.InputWrapper>
          <ov-videoconference
            id="ov-videoconference"
            tokens={getToken}
            toolbarDisplaySessionName={false}
            onSessionCreated={onSessionCreated}
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
      )}
      <S.ChattingWrapper>
        <S.ChattingList ref={scrollRef}>
          {messages && messages.length > 0 ? (
            messages.map((item, index) => (
              <S.ChattingContent key={item + index}>
                <S.ChattingUserNickname>{item.userName}</S.ChattingUserNickname>
                <S.ChattingText>{item.text}</S.ChattingText>
              </S.ChattingContent>
            ))
          ) : (
            <div>채팅을 시작해보세요:)</div>
          )}
        </S.ChattingList>
      </S.ChattingWrapper>

      {isDesktop && (
        <S.InputWrapper>
          <ov-videoconference
            id="ov-videoconference"
            tokens={getToken}
            toolbarDisplaySessionName={false}
            onSessionCreated={onSessionCreated}
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
      )}
    </S.Container>
  );
};
export default ChattingModal;
