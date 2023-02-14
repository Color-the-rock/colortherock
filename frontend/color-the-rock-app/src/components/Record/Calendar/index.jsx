import React, { useEffect, useState } from "react";
import Calendar from "react-calendar";
import moment from "moment";
import * as S from "./style";
import "./calendar.css";
import { recordApi } from "../../../api/record";
import { useDispatch, useSelector } from "react-redux";
import { setCurrentDate, setVideos } from "../../../stores/record/recordSlice";

const CustomCalendar = () => {
  const dispatch = useDispatch();
  const date = useSelector((state) => state.record.currentDate);
  const radioValue = useSelector((state) => state.record.isSuccess);
  const [markers, setMarkers] = useState([]); // 컬러 마커 데이터
  const [prevYearMonth, setPrevYearMonth] = useState(new Date()); // 이전 호출 값 저장
  const [value, setValue] = useState(date);

  // 달력의 날짜가 변경된 경우 처리
  const handleOnChange = (e) => {
    setValue(e);
    if (
      moment(e).format("YYYY-MM-DD") !==
      moment(prevYearMonth).format("YYYY-MM-DD")
    ) {
      dispatch(setCurrentDate(e));
    }

    if (
      moment(e).format("YYYY-MM") !== moment(prevYearMonth).format("YYYY-MM")
    ) {
      getCalendarData(e);
      dispatch(setCurrentDate(e));
    }
    getVideoListByCalendar(e);
  };

  const getCalendarData = (e) => {
    recordApi
      .getCalendarData(moment(e).format("YYYY-MM"))
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          setMarkers(result);
          setPrevYearMonth(date);
        }
      })
      .catch((error) => console.log("error", error));
  };

  const getVideoListByCalendar = (e) => {
    const data = {
      videoId: -1,
      shootingDate: moment(e).format("YYYY-MM-DD"),
      isSuccess: radioValue ? true : false,
    };

    console.log("[requestBody] : ", data);
    recordApi
      .getAllRecordVideo(data)
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          console.log("[getAllRecordVideo()] statusCode : 200 ", result);
          dispatch(setVideos(result));
        }
      })
      .catch((error) => console.log("error :", error));
  };

  useEffect(() => {
    getCalendarData();
  }, [date]);

  useEffect(() => {
    getVideoListByCalendar(date);
  }, [radioValue]);

  return (
    <S.Container>
      <Calendar
        onClickDecade={handleOnChange}
        onClickWeekNumber={handleOnChange}
        onClickMonth={handleOnChange}
        onClickYear={handleOnChange}
        onChange={handleOnChange}
        minDetail="year"
        value={value}
        formatDay={(locale, date) => moment(date).format("DD")}
        tileContent={({ date }) => {
          let html = [];
          let target = markers.find(
            (x) => x.date === moment(date).format("YYYY-MM-DD")
          );
          if (target !== undefined) {
            html.push(
              <span className="dot_list" key={date}>
                {target.colors.map((color, index) => (
                  <S.Dot
                    className="dot"
                    color={color}
                    key={date + color + index}
                  />
                ))}
              </span>
            );
          }
          return (
            <div>
              <div>{html}</div>
            </div>
          );
        }}
      />
    </S.Container>
  );
};
export default CustomCalendar;
