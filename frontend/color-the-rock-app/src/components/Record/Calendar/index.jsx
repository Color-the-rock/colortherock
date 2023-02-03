import React, { useEffect, useState } from "react";
import Calendar from "react-calendar";
import moment from "moment";
import * as S from "./style";
import "./calendar.css";
import { recordApi } from "../../../api/record";
const testData = [
  { date: "2023-01-02", colors: ["#FF4E36", "#FFCF1B", "#C0FA87"] },
  { date: "2023-01-03", colors: ["#FFCF1B", "#C0FA87"] },
  { date: "2023-01-12", colors: ["#FF6CAB", "#6EE2F5", "#C0FA87"] },
  { date: "2023-01-17", colors: ["#FF4E36", "#FFA62E"] },
  { date: "2023-01-23", colors: ["#FFA62E", "#FFCF1B", "#695F54"] },
  { date: "2023-01-21", colors: ["#FF6CAB"] },
  { date: "2023-01-28", colors: ["#6EE2F5", "#3C5DD3", "#FF6CAB"] },
];
const CustomCalendar = () => {
  const [value, onChange] = useState(new Date());
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
    }
    onChange(e);
  };

  const getCalendarData = () => {
    console.log("value? ", value);
    console.log("value? ", moment(value).format("YYYY-MM"));
    recordApi
      .getCalendarData(moment(value).format("YYYY-MM"))
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          console.log("success!");
          setMarkers(result);
          setPrevYearMonth(value);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getCalendarData();
  }, []);

  return (
    <S.Container>
      <Calendar
        onClickDecade={handleOnChange}
        onClickWeekNumber={handleOnChange}
        onClickMonth={handleOnChange}
        onClickYear={handleOnChange}
        onChange={handleOnChange}
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
