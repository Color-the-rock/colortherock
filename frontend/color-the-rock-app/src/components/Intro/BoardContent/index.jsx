import React from "react";
import * as S from "./style";
import IntroTitle from "../IntroTitle";
import SecondLineImg from "../../../assets/img/intro/img-intro-board2.png";
import ParallaxText from "../ParallaxText";

const BoardContent = () => {
  return (
    <S.Container>
      <IntroTitle text="완등 영상 목록" />
      <S.Text>인OOOO, 유OO 검색도 안녕!</S.Text>
      <S.Text>
        정확하게 내가 원하는 암장, 색상을 선택해 풀이를 볼 수 있어요!
      </S.Text>
      <S.Section>
        <ParallaxText baseVelocity={-3}>
          <S.BoardImg src={SecondLineImg} alt="img" />
        </ParallaxText>
        <ParallaxText baseVelocity={3}>
          <S.BoardImg src={SecondLineImg} alt="img" />
        </ParallaxText>
      </S.Section>
    </S.Container>
  );
};

export default BoardContent;
