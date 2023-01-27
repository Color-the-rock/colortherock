import React from "react";
import StackedGraph from "../../components/Record/StackedGraph";
import Title from "../../components/Common/Title";
import "react-calendar/dist/Calendar.css";
import SubTitle from "../../components/Common/SubTitle";
import * as S from "./style";
import CustomCalendar from "../../components/Record/Calendar";
import { useInput } from "../../hooks/useInput";
import MyPost from "../../components/Mypage/MyPost";
const Record = () => {
  const [radioValue, onChangeRadioButton] = useInput("success");

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
      <S.GraphWrapper />
      {/* <StatisticGraph /> */}
      <SubTitle text="일별 도전 기록" />
      <CustomCalendar />
      {/* 일자별 성공/실패 영상 목록 */}

      <S.RadioGroup>
        {/* 선택한 날짜 정보를 같이 제공해줄지 고민 */}
        {/* <SubTitle text={moment(value).format("YYYY년 MM월 DD일")} /> */}
        <S.RadioLabel checked={radioValue === "success"}>
          <S.RadioButton
            type="radio"
            name="type"
            value="success"
            checked={radioValue === "success"}
            onChange={onChangeRadioButton}
          />
          성공영상
        </S.RadioLabel>
        <S.RadioLabel checked={radioValue === "fail"}>
          <S.RadioButton
            type="radio"
            name="type"
            value="fail"
            checked={radioValue === "fail"}
            onChange={onChangeRadioButton}
          />
          실패영상
        </S.RadioLabel>
      </S.RadioGroup>
      <MyPost />
    </S.Container>
  );
};
export default Record;
