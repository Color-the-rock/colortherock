import React from "react";
import Content from "../../components/Intro/LiveContent";
import * as S from "./style";
import { useRef } from "react";
import {
  motion,
  useScroll,
  useSpring,
  useTransform,
  useMotionValue,
  useVelocity,
  useAnimationFrame,
} from "framer-motion";
import { wrap } from "@motionone/utils";
import useScrollFadeIn from "../../hooks/useScroll";
import FirstLineImg from "../../assets/img/intro/img-intro-board1.png";
import SecondLineImg from "../../assets/img/intro/img-intro-board2.png";
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

function ParallaxText({ children, baseVelocity = 100 }) {
  const baseX = useMotionValue(0);
  const { scrollY } = useScroll();
  const scrollVelocity = useVelocity(scrollY);
  const smoothVelocity = useSpring(scrollVelocity, {
    damping: 50,
    stiffness: 400,
  });
  const velocityFactor = useTransform(smoothVelocity, [0, 1000], [0, 5], {
    clamp: false,
  });

  const x = useTransform(baseX, (v) => `${wrap(-20, -45, v)}%`);

  const directionFactor = useRef(1);
  useAnimationFrame((t, delta) => {
    let moveBy = directionFactor.current * baseVelocity * (delta / 1000);

    if (velocityFactor.get() < 0) {
      directionFactor.current = -1;
    } else if (velocityFactor.get() > 0) {
      directionFactor.current = 1;
    }

    moveBy += directionFactor.current * moveBy * velocityFactor.get();

    baseX.set(baseX.get() + moveBy);
  });

  return (
    <S.Parallax>
      <motion.div className="scroller" style={{ x }}>
        <span>{children} </span>
        <span>{children} </span>
        <span>{children} </span>
        <span>{children} </span>
      </motion.div>
    </S.Parallax>
  );
}
