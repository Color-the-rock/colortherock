import React, { useEffect, useRef, useState } from "react";
import * as S from "./style";
import useScrollFadeIn from "../../hooks/useScroll";
import RecordContent from "../../components/Intro/RecordContent";
import LiveContent from "../../components/Intro/LiveContent";
import BoardContent from "../../components/Intro/BoardContent";
import Content from "../../components/Intro/Content";
const Intro = () => {
  const fadeInRecord = useScrollFadeIn();
  const fadeInLive = useScrollFadeIn();
  const fadeInBoard = useScrollFadeIn();
  const fadeIntro = useScrollFadeIn();
  const handleBackToTop = () => {
    let container = document.getElementById("container");
    container.scrollTo(0, 0);
  };

  return (
    <S.Container id="container">
      <S.ContentWrapper {...fadeIntro}>
        <Content />
      </S.ContentWrapper>
      <S.ContentWrapper {...fadeInLive}>
        <LiveContent />
      </S.ContentWrapper>
      <S.ContentWrapper {...fadeInBoard}>
        <BoardContent />
      </S.ContentWrapper>
      <S.ContentWrapper {...fadeInRecord}>
        <RecordContent />
      </S.ContentWrapper>
      <S.BackToTop id="back-to-top" onClick={handleBackToTop} />
    </S.Container>
  );
};
export default Intro;
