import React from 'react'
import { useInput } from "../../../hooks/useInput"
import * as S from "./style"


export const BoardRadioBtn = () => {

  const [radioValue, onChangeRadioButton] = useInput("success")
  return (
    <S.RadioGroup>
      <S.RadioLabel checked={radioValue === "success"}>
        <S.RadioButton
          type="radio"
          name="type"
          value="success"
          checked = {radioValue === "success"}
          onChange={onChangeRadioButton}
        />성공
      </S.RadioLabel>
      <S.RadioLabel checked={radioValue === "fail"}>
        <S.RadioButton
          type="radio"
          name="type"
          value="fail"
          checked={radioValue === "fail"}
          onChange={onChangeRadioButton}
        />실패
      </S.RadioLabel>
    </S.RadioGroup>
  )
}
