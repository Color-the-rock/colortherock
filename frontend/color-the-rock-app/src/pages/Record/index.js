import React from "react";
import { DailyRecordWrapper } from "../../components/Record/DailyRecord/style";
import StackedGraph from "../../components/Record/StackedGraph";
import * as S from "./style";
const Record = () => {
  return (
    <S.Container>
      <S.Title>나의 운동 기록</S.Title>
      {/* 모바일 버전 */}
      <S.TextWrapper>
        <S.Text>
          예지님은 <S.GradientText>30일</S.GradientText>동안
        </S.Text>
        <S.Text>
          총 <S.GradientText>100개의 문제</S.GradientText>에 성공했어요!
        </S.Text>
      </S.TextWrapper>
      {/* 누적기록 */}
      <S.SubTitle>누적 기록</S.SubTitle>
      <StackedGraph />
      {/* 일자별 기록 */}
      <S.SubTitle>일자별 기록</S.SubTitle>
      <DailyRecordWrapper />
      {/* 영상목록 */}

      {/* 데스크탑 버전 */}
    </S.Container>
  );
};
export default Record;
