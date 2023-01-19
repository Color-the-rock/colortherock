import React from "react";
import * as S from "./style";
const Record = () => {
  return (
    <S.Container>
      <S.Title>나의 운동 기록</S.Title>
      <S.TextWrapper>
        <S.Text>
          예지님은 <S.GradientText>30일</S.GradientText>동안
        </S.Text>
        <S.Text>
          총 <S.GradientText>100개의 문제</S.GradientText>에 성공했어요!
        </S.Text>
      </S.TextWrapper>
      {/* 누적기록 */}
      {/* 일자별 기록 */}
      {/* 영상목록 */}
    </S.Container>
  );
};
export default Record;
