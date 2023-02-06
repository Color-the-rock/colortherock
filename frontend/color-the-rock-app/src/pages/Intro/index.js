import React from "react";
import Content from "../../components/Intro/LiveContent";
import * as S from "./style";
import useScrollFadeIn from "../../hooks/useScroll";
import FirstLineImg from "../../assets/img/intro/img-intro-board1.png";
import SecondLineImg from "../../assets/img/intro/img-intro-board2.png";
import ParallaxText from "../../components/Intro/ParallaxText";
const Intro = () => {
  const fadeIn1 = useScrollFadeIn();
  const fadeIn2 = useScrollFadeIn();
  const fadeIn3 = useScrollFadeIn();
  return (
    <S.Container>
      <S.ContentWrapper>
        <S.Section>
          <ParallaxText baseVelocity={-5}>Color The Rock</ParallaxText>
          <ParallaxText baseVelocity={5}>Color your Rock</ParallaxText>
        </S.Section>
      </S.ContentWrapper>
      <S.ContentWrapper {...fadeIn1}>
        <Content />
      </S.ContentWrapper>
      <S.ContentWrapper>
        <S.BoardText>
          <p>내가 보고 싶은 완등 영상을</p>
          <p>한번에 찾아봐요:)</p>
        </S.BoardText>
        <S.Section>
          <ParallaxText baseVelocity={-3}>
            <S.BoardImg src={SecondLineImg} alt="img" />
          </ParallaxText>
          <ParallaxText baseVelocity={3}>
            <S.BoardImg src={SecondLineImg} alt="img" />
          </ParallaxText>
        </S.Section>
      </S.ContentWrapper>
      <S.ContentWrapper {...fadeIn3}>
        <Content />
      </S.ContentWrapper>
    </S.Container>
  );
};
export default Intro;
