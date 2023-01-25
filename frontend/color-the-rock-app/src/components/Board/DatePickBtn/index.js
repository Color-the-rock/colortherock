import React from 'react'
import * as S from "./style"
import DatePicker from "react-datepicker"
import "react-datepicker/dist/react-datepicker.css";
import { Desktop, Mobile } from "../../layout/Template"
import { ko } from "date-fns/esm/locale";

const DatePickBtn = ({startDate, setStartDate}) => {
  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
          <S.DateWrap>
            <DatePicker
              className="datepicker"
              locale={ko}
              placeholderText='날짜를 설정해주세요.'
              dateFormat="yyyy/MM/dd"
              selected={startDate}
              onChange={(date) => setStartDate(date)}
              // customInput={
              //   <S.CustomComp/>
              // }
            />
          </S.DateWrap>
        </S.Container>
      </Mobile>
    </div>
  )
}

export default React.memo(DatePickBtn);
