import React from 'react'
import * as S from "./style"

function RegistBtn({btnName, clickHandler}) {  
  
  return (
    <S.Container onClick={clickHandler}>
      <S.ButtonWrap>
        {btnName}
      </S.ButtonWrap>
    </S.Container>
  )
}

export default React.memo(RegistBtn);