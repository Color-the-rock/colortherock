import React, { useEffect, useState } from "react";
import StackedGraph from "../../components/Record/StackedGraph";
import Title from "../../components/Common/Title";
import "react-calendar/dist/Calendar.css";
import SubTitle from "../../components/Common/SubTitle";
import * as S from "./style";
import CustomCalendar from "../../components/Record/Calendar";
import { Mobile, Desktop } from "../../components/layout/Template";
import StatisticGraph from "../../components/Record/StatisticGraph";
import MyRecordVideoList from "../../components/Record/MyRecordVideoList";
import { useDispatch, useSelector } from "react-redux";
import { recordApi } from "../../api/record";
import GuideImg from "../../assets/img/record/img-record-guide.png";
import { setSuccess } from "../../stores/record/recordSlice";

// 운동기록 페이지
const Record = () => {
  const dispatch = useDispatch();
  const radioValue = useSelector((state) => state.record.isSuccess);
  const userNickName = useSelector((state) => state.users.nickName);
  const [userRecordInfo, setUserRecordInfo] = useState({});
  const [isShowGuide, setShowGuide] = useState(false);

  //  운동 기록 통계 정보 조회
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

  // 성공 및 실패 영상

  useEffect(() => {
    getUserRecordInfo();

    // show info guide
    const infoGuide = document.getElementById("record-info-guide");

    if (infoGuide !== null) {
      infoGuide.addEventListener("mouseover", function () {
        setShowGuide(true);
      });

      infoGuide.addEventListener("mouseout", function () {
        setShowGuide(false);
      });
    }
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
          <S.GradientText>
            {userRecordInfo.successCount}개의 문제
          </S.GradientText>
          에 성공했어요!
        </S.Text>
      </S.TextWrapper>
      <Mobile>
        <S.InfoWrapper>
          {/* 레벨별 도전 현황 */}
          <SubTitle text="레벨별 도전 현황">
            <S.InfoButton id="record-info-guide" />
          </SubTitle>
          {isShowGuide && <S.InfoGuideImg src={GuideImg} alt="guide" />}
        </S.InfoWrapper>
        <StackedGraph />
        {/* 활동 통계 */}
        <SubTitle text="활동 통계" />
        <StatisticGraph />
        <SubTitle text="일별 도전 기록" />
        <S.CalendarWrapper>
          <CustomCalendar />
        </S.CalendarWrapper>
        <S.RadioGroup>
          <S.RadioLabel checked={radioValue}>
            <S.RadioButton
              type="radio"
              name="type"
              value="success"
              checked={radioValue}
              onChange={() => dispatch(setSuccess(true))}
            />
            성공영상
          </S.RadioLabel>
          <S.RadioLabel checked={!radioValue}>
            <S.RadioButton
              type="radio"
              name="type"
              value="fail"
              checked={!radioValue}
              onChange={() => dispatch(setSuccess(false))}
            />
            실패영상
          </S.RadioLabel>
          <S.UploadButton to="/record/form">
            <S.UploadIcon size="20px" color="#ffffff" />
            업로드
          </S.UploadButton>
        </S.RadioGroup>
        <MyRecordVideoList />
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
              <CustomCalendar isSuccess={radioValue} />
            </S.CalendarWrapper>

            <div>
              <S.RadioGroup>
                <S.RadioLabel checked={radioValue}>
                  <S.RadioButton
                    type="radio"
                    name="type"
                    value="success"
                    checked={radioValue}
                    onChange={() => dispatch(setSuccess(true))}
                  />
                  성공영상
                </S.RadioLabel>
                <S.RadioLabel checked={!radioValue}>
                  <S.RadioButton
                    type="radio"
                    name="type"
                    value="fail"
                    checked={!radioValue}
                    onChange={() => dispatch(setSuccess(false))}
                  />
                  실패영상
                </S.RadioLabel>
                <S.UploadButton to="/record/form">
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
