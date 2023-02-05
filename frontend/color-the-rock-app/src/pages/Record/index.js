import React, { useEffect, useState } from "react";
import StackedGraph from "../../components/Record/StackedGraph";
import Title from "../../components/Common/Title";
import "react-calendar/dist/Calendar.css";
import SubTitle from "../../components/Common/SubTitle";
import * as S from "./style";
import CustomCalendar from "../../components/Record/Calendar";
import { useInput } from "../../hooks/useInput";
import { Mobile, Desktop } from "../../components/layout/Template";
import StatisticGraph from "../../components/Record/StatisticGraph";
import MyRecordVideoList from "../../components/Record/MyRecordVideoList";
import { useSelector } from "react-redux";
import { recordApi } from "../../api/record";

const Record = () => {
  const [radioValue, onChangeRadioButton] = useInput("success");
  const userNickName = useSelector((state) => state.user.nickName);
  const [userRecordInfo, setUserRecordInfo] = useState({});

  const getUserRecordInfo = () => {
    recordApi
      .getTotalStatistics()
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          console.log("success : 200", result);
          setUserRecordInfo(result);
        }
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getUserRecordInfo();
  }, []);

  return (
    <S.Container>
      <Title>나의 운동 기록</Title>
      <S.Description>나의 도전 현황을 한 눈에 확인해보세요!</S.Description>
      <S.TextWrapper>
        <S.Text>
          {userNickName === "" ? "사용자" : userNickName}님은
          <S.GradientText> {userRecordInfo.videoCount}일</S.GradientText>동안
        </S.Text>
        <S.Text>
          <S.GradientText>{userRecordInfo.visitCount}개</S.GradientText>의
          암장에서{" "}
          <S.GradientText>
            {userRecordInfo.successCount}개의 문제
          </S.GradientText>
          에 성공했어요!
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
        <MyRecordVideoList isSuccess={radioValue} />
      </Mobile>
      <Desktop>
        <S.ContentWrapper>
          <S.RecordWrapper>
            <div>
              <SubTitle text="레벨별 도전 현황" />
              <StackedGraph />
            </div>
            {/* 활동 통계 */}
            <div>
              <SubTitle text="활동 통계" />
              <StatisticGraph />
            </div>
          </S.RecordWrapper>
          <SubTitle text="일별 도전 기록" />
          <S.RecordWrapper>
            <S.CalendarWrapper>
              <CustomCalendar />
            </S.CalendarWrapper>

            <div>
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
              <MyRecordVideoList />
            </div>
          </S.RecordWrapper>
        </S.ContentWrapper>
      </Desktop>
    </S.Container>
  );
};
export default Record;
