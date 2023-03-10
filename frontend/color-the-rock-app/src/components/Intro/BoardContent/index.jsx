import React from "react";
import * as S from "./style";
import IntroTitle from "../IntroTitle";
import FirstLineImg from "../../../assets/img/intro/img-intro-board1.png";
import SecondLineImg from "../../../assets/img/intro/img-intro-board2.png";
import ParallaxText from "../ParallaxText";

const BoardContent = () => {
  return (
    <S.Container>
      <IntroTitle text="완등 영상 목록" margin="0 1rem" />
      <S.Text>인스타그램, 유튜브 검색도 안녕!</S.Text>
      <S.Text>
        정확하게 내가 원하는 암장, 색상을 선택해 풀이를 볼 수 있어요!
      </S.Text>
      <S.ThumbnailContainer>
        <S.ContentWrapper>
          <S.Books type="book" contents={FirstLineImg} />
          <S.Books type="movie" contents={SecondLineImg} />
        </S.ContentWrapper>
      </S.ThumbnailContainer>
    </S.Container>
  );
};

export default BoardContent;
