import React from 'react'
import * as S from "./style"

const SelectButton = ({value, setValue}) => {
  return (
    <S.Container className='custom-select'>
        <S.Select>
          <option value="0">난이도 레벨</option>
          <option value="dog">Dog</option>
          <option vlaue="cat">Cat</option>
        </S.Select>
    </S.Container>
  )
}



export default React.memo(SelectButton);