import React, {useState, useEffect} from 'react'
import * as S from "./style"
import PropTypes from "prop-types"
import "./calendar.css";

const CustomCalendar = ({selectDate, setSelectDate}) => {

  const [maxDate, setMaxDate] = useState("");
  
  useEffect(() => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    
    setMaxDate(year + '-' + month + '-' + day);
  }, [])
  
  console.log("selectDate", selectDate);

  return (
    <S.Container className='input_date_box'>
      <S.Calendar 
      type="date" 
      id="date1"
      onChange={e => setSelectDate(e.target.value)}
      ></S.Calendar>
    </S.Container>
  )
}

CustomCalendar.propTypes = {
  selectDate: PropTypes.string.isRequired,
  setSelectDate: PropTypes.func.isRequired,
}


export default React.memo(CustomCalendar);