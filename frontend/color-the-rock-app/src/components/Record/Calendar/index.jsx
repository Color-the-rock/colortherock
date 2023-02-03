import React, { useEffect, useState } from "react";
import Calendar from "react-calendar";
import moment from "moment";
import * as S from "./style";
import "./calendar.css";
import { recordApi } from "../../../api/record";
import { useDispatch, useSelector } from "react-redux";
import { setCurrentDate } from "../../../stores/record/recordSlice";

const CustomCalendar = () => {
  const dispatch = useDispatch();
  const date = useSelector((state) => state.record.currentDate);
  const [markers, setMarkers] = useState([]); // 컬러 마커 데이터
  const [prevYearMonth, setPrevYearMonth] = useState(new Date()); // 이전 호출 값 저장
  const handleOnChange = (e) => {
    if (
      moment(e).format("YYYY-MM") === moment(prevYearMonth).format("YYYY-MM")
    ) {
      console.log("같음");
    } else {
      console.log("다름");
      getCalendarData();
      dispatch(setCurrentDate(e));
    }
  };

  const getCalendarData = () => {
    console.log("getCalendarData()... ? ", date);
    console.log("date format? ", moment(date).format("YYYY-MM"));
    recordApi
      .getCalendarData(moment(date).format("YYYY-MM"))
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          console.log("success!");
          setMarkers(result);
          setPrevYearMonth(date);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getCalendarData();
  }, []);

  useEffect(() => {
    console.log("Dddd", markers);
  }, [markers]);

  return (
    <S.Container>
      <Calendar
        onClickDecade={handleOnChange}
        onClickWeekNumber={handleOnChange}
        onClickMonth={handleOnChange}
        onClickYear={handleOnChange}
        onChange={handleOnChange}
        value={date}
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
