import React, { useState, useEffect } from 'react'
import { Desktop, Mobile } from "../../layout/Template"
import * as S from "./style"


export default function SubTitleForm({title}) {
  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
          <S.Title>{title}</S.Title>
        </S.Container>
      </Mobile>
    </div>
  )
}

