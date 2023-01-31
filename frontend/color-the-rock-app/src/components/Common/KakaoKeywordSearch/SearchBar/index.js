import React, { useState } from 'react'
import PropTypes from "prop-types"
import * as S from "./style"
import { Desktop, Mobile } from "../../../layout/Template"
import SearchList from '../SearchList';

const KakaoMapBtn = ({location, setLocation}) => {

  const [inputText, setInputText] = useState("");
  const [OpenList, setOpenList] = useState(false);

  const handleChange = (e) => {
    setInputText(e.target.value);
  }

  const handleOnKeyPress = (e) => {
    // Enter event 발생시
    if(e.key === 'Enter') {
      setLocation(inputText);
      setOpenList(true);
    }
  }
  
  return (
    <S.Container>
        <S.InputContent
          type="text"
          placeholder="암장을 입력해주세요."
          onChange={handleChange}
          onKeyDown={handleOnKeyPress}
        />

      { OpenList ?
        (<SearchList searchPlace={location} setOpenList={setOpenList}></SearchList>) 
      :
        null
      }
        
    </S.Container>
  )
}

KakaoMapBtn.propTypes = {
  location: PropTypes.string,
  setLocation: PropTypes.func,
}



export default React.memo(KakaoMapBtn); 