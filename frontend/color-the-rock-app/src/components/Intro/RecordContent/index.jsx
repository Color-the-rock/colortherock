import React from "react";
import IntroTitle from "../IntroTitle";
import * as S from "./style";
import { Mobile, Desktop } from "../../layout/Template";
import Record1 from "../../../assets/img/intro/intro-record1.png";
import Record2 from "../../../assets/img/intro/intro-record2.png";
const RecordContent = () => {
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
        duration: 0.6,
      },
    },
  };

  return (
    <S.Container
      initial="offscreen"
      whileInView="onscreen"
      viewport={{ once: true, amount: 0.8 }}
    >
      <S.Wrapper>
        <IntroTitle text="클라이밍 기록" />
        <S.Text>내가 깬 클라이밍 기록을</S.Text>
        <S.Text>컬러더락만의 컬러들로</S.Text>
        <S.Text>한 눈에 보여드려요!</S.Text>
      </S.Wrapper>
      <S.ImgWrapper>
        <Desktop>
          <S.ImgAnimation variants={imgVariants}>
            <S.ImgBox left="10" bg={Record1} />
            <S.ImgBox left="360" bg={Record2} />
          </S.ImgAnimation>
        </Desktop>
        <Mobile>
          <S.ImgAnimation variants={imgMobileVariants}>
            <S.ImgBoxMobile right="45" bg={Record1} />
            <S.ImgBoxMobile right="0" bg={Record2} />
          </S.ImgAnimation>
        </Mobile>
      </S.ImgWrapper>
    </S.Container>
  );
};

export default RecordContent;
