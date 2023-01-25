import React from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../layout/Template"


export default function RegistBtn({btnName}) {
  
  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
          <S.ButtonWrap>
            {btnName}
          </S.ButtonWrap>
        </S.Container>
      </Mobile>
    </div>
  )
}
