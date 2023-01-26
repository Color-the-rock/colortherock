import React from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../layout/Template"


function RegistBtn({btnName, clickHandler}) {  
  
  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container onClick={clickHandler}>
          <S.ButtonWrap>
            {btnName}
          </S.ButtonWrap>
        </S.Container>
      </Mobile>
    </div>
  )
}

export default React.memo(RegistBtn);