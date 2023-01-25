import React, { useState } from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../layout/Template"

export default function InputComp({placeholder}) {
  
  const [value, setValue] = useState("");
  
  const handleChange = (e) => {
    setValue(e.target.value);
    // 여기에 추가할 조건 추가.....
  }

  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
          <S.InputWrap>
            <S.InputContent
              type="text"
              placeholder={placeholder}
              onChange={handleChange}
            />
          </S.InputWrap>
        </S.Container>
      </Mobile>
    </div>
  )
}
