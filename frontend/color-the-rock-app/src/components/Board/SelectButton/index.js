import React, { useState } from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../layout/Template"

const levelList = [

]

export default function SelectButton() {
  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
          <S.SelectWrap>
            <S.Select>
              <option value="">난이도 레벨</option>
              <option value="dog">Dog</option>
              <option vlaue="cat">Cat</option>
            </S.Select>
          </S.SelectWrap>
        </S.Container>
      </Mobile>
    </div>
  )
}
