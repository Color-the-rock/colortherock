import React, { useState } from "react";
import StackedGraph from "../../components/Record/StackedGraph";
import Title from "../../components/Common/Title";
import "react-calendar/dist/Calendar.css";
import "./calendar.css";
import SubTitle from "../../components/Common/SubTitle";
import * as S from "./style";
import RecordVideo from "../../components/Record/RecordVideo";
const Record = () => {
  const [value, onChange] = useState(new Date());

  return (
    <S.Container>
      <Title>나의 운동 기록</Title>
      {/* 모바일 버전 */}
      <S.TextWrapper>
        <S.Text>
          김싸피님은 <S.GradientText>30일</S.GradientText>동안
        </S.Text>
        <S.Text>
          총 <S.GradientText>100개의 문제</S.GradientText>에 성공했어요!
        </S.Text>
      </S.TextWrapper>
      {/* 레벨별 도전 현황 */}
      <SubTitle text="레벨별 도전 현황" />
      <S.GraphWrapper>
        <StackedGraph />
      </S.GraphWrapper>
      {/* 활동 통계 */}
      <SubTitle text="활동 통계" />
      <S.GraphWrapper></S.GraphWrapper>
      {/* <StatisticGraph /> */}
      <SubTitle text="일별 도전 기록" />
      <S.RecordCalendar onChange={onChange} value={value} />
    </S.Container>
  );
};
export default Record;
