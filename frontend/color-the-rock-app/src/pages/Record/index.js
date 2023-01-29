import React from "react";
import StackedGraph from "../../components/Record/StackedGraph";
import Title from "../../components/Common/Title";
import "react-calendar/dist/Calendar.css";
import SubTitle from "../../components/Common/SubTitle";
import * as S from "./style";
import CustomCalendar from "../../components/Record/Calendar";
import { useInput } from "../../hooks/useInput";
import MyPost from "../../components/Mypage/MyPost";
import { Mobile, Desktop } from "../../components/layout/Template";
import StatisticGraph from "../../components/Record/StatisticGraph";
const Record = () => {
  const [radioValue, onChangeRadioButton] = useInput("success");

  return (
    <S.Container>
      <Title>나의 운동 기록</Title>
      <S.Description>나의 도전 현황을 한 눈에 확인해보세요!</S.Description>
      <S.TextWrapper>
        <S.Text>
          김싸피님은 <S.GradientText>30일</S.GradientText>동안
        </S.Text>
        <S.Text>
          총 <S.GradientText>100개의 문제</S.GradientText>에 성공했어요!
        </S.Text>
      </S.TextWrapper>
      <Mobile>
        {/* 레벨별 도전 현황 */}
        <SubTitle text="레벨별 도전 현황" />
        <StackedGraph />
        {/* 활동 통계 */}
        <SubTitle text="활동 통계" />
        <StatisticGraph />
        <SubTitle text="일별 도전 기록" />
        <S.CalendarWrapper>
          <CustomCalendar />
        </S.CalendarWrapper>
        <S.RadioGroup>
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
          <S.UploadButton>
            <S.UploadIcon size="20px" color="#ffffff" />
            업로드
          </S.UploadButton>
        </S.RadioGroup>
        <MyPost />
      </Mobile>
      <Desktop>
        <S.ContentWrapper>
          <SubTitle text="레벨별 도전 현황" />
          <StackedGraph />
          {/* 활동 통계 */}
          <SubTitle text="활동 통계" />
          <SubTitle text="일별 도전 기록" />
        </S.ContentWrapper>
      </Desktop>
    </S.Container>
  );
};
export default Record;
