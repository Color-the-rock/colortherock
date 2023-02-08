import React from "react";
import * as S from "./style";
import IntroTitle from "../IntroTitle";
import IntroButton from "../IntroButton";

// props : imgURL
const LiveContent = () => {
  return (
    <S.Container>
      <S.Wrapper>
        <IntroTitle text="실시간 도전" />
        <S.Text>외로운 혼클은 이제 안녕!</S.Text>
        <S.Text>실시간으로 소통하며</S.Text>
        <S.Text>함께 볼더링 문제를 풀어봐요!</S.Text>
        <IntroButton text="피드백 하러가기" />
      </S.Wrapper>
      <S.ImgWrapper></S.ImgWrapper>
    </S.Container>
  );
};

export default LiveContent;
