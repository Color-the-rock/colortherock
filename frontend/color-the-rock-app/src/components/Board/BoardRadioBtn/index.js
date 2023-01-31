import React, {useState} from 'react'
import { useInput } from "../../../hooks/useInput"
import * as S from "./style"


const BoardRadioBtn = () => {

  const [radioValue, onChangeRadioButton] = useInput("success")

  return (
    <S.Container>
      <S.ContentWrap checked={radioValue === "success"}>
      <S.RadioButton
          className='radiobtn'
          type="radio"
          name="type"
          value="success"
          checked = {radioValue === "success"}
          onChange={onChangeRadioButton}
        /><span>성공</span>
      </S.ContentWrap>
      <S.ContentWrap checked={radioValue === "fail"}>
      <S.RadioButton
          type="radio"
          name="type"
          value="fail"
          checked = {radioValue === "fail"}
          onChange={onChangeRadioButton}
        /><span>실패</span>
      </S.ContentWrap>
    </S.Container>
  )
}

export default BoardRadioBtn;