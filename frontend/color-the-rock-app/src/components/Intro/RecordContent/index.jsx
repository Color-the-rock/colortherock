import React from "react";
import IntroTitle from "../IntroTitle";
import * as S from "./style";

const RecordContent = () => {
  return (
    <S.Container>
      <IntroTitle text="클라이밍 기록" />
      <S.Text>내가 깬 클라이밍 기록을</S.Text>
      <S.Text>컬러더락만의 컬러들로</S.Text>
      <S.Text>한 눈에 보여드려요!</S.Text>
    </S.Container>
  );
};

export default RecordContent;
