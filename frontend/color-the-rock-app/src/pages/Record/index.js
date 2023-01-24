import React, { useState } from "react";
import StackedGraph from "../../components/Record/StackedGraph";
import "react-calendar/dist/Calendar.css";
import "./calendar.css";

import * as S from "./style";
const Record = () => {
  const [value, onChange] = useState(new Date());

  return (
    <S.Container>
      <S.Title>나의 운동 기록</S.Title>
      {/* 모바일 버전 */}
      <S.TextWrapper>
        <S.Text>
          나싸피님은 <S.GradientText>30일</S.GradientText>동안
        </S.Text>
        <S.Text>
          총 <S.GradientText>100개의 문제</S.GradientText>에 성공했어요!
        </S.Text>
      </S.TextWrapper>
      {/* 레벨별 도전 현황 */}
      <S.SubTitle>레벨별 도전 현황</S.SubTitle>
      <S.GraphWrapper>
        <StackedGraph />
      </S.GraphWrapper>
      {/* 활동 통계 */}
      <S.SubTitle>활동 통계</S.SubTitle>
      <S.GraphWrapper></S.GraphWrapper>
      {/* <StatisticGraph /> */}
      <S.SubTitle>일별 도전 기록</S.SubTitle>
      <S.RecordCalendar onChange={onChange} value={value} />

      <S.SubTitle>성공 영상</S.SubTitle>

      <S.SubTitle>실패 영상</S.SubTitle>
    </S.Container>
  );
};
export default Record;
