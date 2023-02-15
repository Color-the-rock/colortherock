import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import "./calendar.css";

const CustomCalendar = ({
  placeholder = "날짜 선택",
  selectDate,
  setSelectDate,
}) => {
  const [maxDate, setMaxDate] = useState("");

  useEffect(() => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ("0" + (today.getMonth() + 1)).slice(-2);
    const day = ("0" + today.getDate()).slice(-2);

    setMaxDate(year + "-" + month + "-" + day);
  }, []);

  return (
    <div className="input_date_box">
      <input
        type="date"
        data-placeholder={placeholder}
        required
        style={{
          color: "var(--color-secondary)",
          fontFamily: "Noto Sans KR",
        }}
        defaultValue={selectDate}
        onChange={(e) => setSelectDate(e.target.value)}
      ></input>
    </div>
  );
};

export default React.memo(CustomCalendar);

CustomCalendar.propTypes = {
  selectDate: PropTypes.string.isRequired,
  setSelectDate: PropTypes.func.isRequired,
};
