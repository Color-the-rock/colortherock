import React from "react";
import * as S from "./style";
import IntroTitle from "../IntroTitle";
import IntroButton from "../IntroButton";
import { Desktop, Mobile } from "../../layout/Template";
const LiveContent = () => {
  const imgVariants = {
    offscreen: {
      y: 500,
    },
    onscreen: {
      y: 80,
      transition: {
        type: "spring",
        bounce: 0.4,
        duration: 0.8,
      },
    },
  };

  const imgMobileVariants = {
    offscreen: {
      y: 400,
    },
    onscreen: {
      y: 150,
      transition: {
        type: "spring",
        bounce: 0.4,
        duration: 0.8,
      },
    },
  };

  return (
    <S.Container
      initial="offscreen"
      whileInView="onscreen"
      viewport={{ once: false, amount: 0.8 }}
    >
      <S.Wrapper>
        <IntroTitle text="실시간 도전" />
        <S.Text>외로운 혼클은 이제 안녕!</S.Text>
        <S.Text>실시간으로 소통하며</S.Text>
        <S.Text>함께 볼더링 문제를 풀어봐요!</S.Text>
        <IntroButton path="/streaming" text="피드백 하러가기" />
      </S.Wrapper>
      <S.ImgWrapper>
        <Desktop>
          <S.ImgAnimation variants={imgVariants}>
            <S.ImgBox left="60" depth="1" bg="#dddddd" />
            <S.ImgBox bottom="100" left="240" depth="10" />
            <S.ImgBox left="420" depth="100" bg="#e4e4e4" />
          </S.ImgAnimation>
        </Desktop>
        <Mobile>
          <S.ImgAnimation variants={imgMobileVariants}>
            <S.ImgBoxMobile right="50" bg="#dddddd" />
            <S.ImgBoxMobile right="25" />
            <S.ImgBoxMobile right="0" bg="#e4e4e4" />
          </S.ImgAnimation>
        </Mobile>
      </S.ImgWrapper>
    </S.Container>
  );
};

export default LiveContent;
