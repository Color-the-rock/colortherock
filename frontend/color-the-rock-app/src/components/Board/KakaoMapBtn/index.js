import React, { useState, useEffect } from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../layout/Template"
import KakaoSearchList from '../KakaoSearchList';

function KakaoMapBtn({location, setLocation}) {
  console.log("흠");
  const [inputText, setInputText] = useState("");
  const [OpenList, setOpenList] = useState(false);
  const handleChange = (e) => {
    setInputText(e.target.value);
  }

  const handleOnKeyPress = (e) => {
    // Enter event 발생시
    if(e.key === 'Enter') {
      console.log("아직 여긴 하지도 않았는데...")
      setLocation(inputText);
      setOpenList(true);
      setInputText("");
    }
  }
  
  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
        <S.InputWrap>
            <S.InputContent
              type="text"
              placeholder="암장을 입력해주세요."
              onChange={handleChange}
              onKeyDown={handleOnKeyPress}
            />
          </S.InputWrap>
          {OpenList ?
         (<KakaoSearchList searchPlace={location}></KakaoSearchList>) 
        :(<div></div>)
      }
          
        </S.Container>
      </Mobile>
    </div>
  )
}

export default React.memo(KakaoMapBtn);