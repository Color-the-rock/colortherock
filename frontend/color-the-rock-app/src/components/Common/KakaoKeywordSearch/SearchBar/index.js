import React, { useState, useEffect } from 'react'
import PropTypes from "prop-types"
import * as S from "./style"
import SearchList from '../SearchList';

const SearchBar = ({location, setLocation, opacity="100"}) => {

  // const [inputText, setInputText] = useState("");
  const [OpenList, setOpenList] = useState(false);

  const handleChange = (e) => {
    setLocation(e.target.value);
    setOpenList(false);
  }

  const handleOnKeyPress = (e) => {
    // Enter event 발생시
    if(e.key === 'Enter') {
      setLocation(location);
      setOpenList(true);
    }
  }
  
  return (
    <div>
      <S.Container opacity={opacity}>
          <S.InputContent
            type="text"
            value={location}
            placeholder="암장을 입력해주세요."
            onChange={handleChange}
            onKeyDown={handleOnKeyPress}
          />
      </S.Container>

        { OpenList ?
          (<SearchList searchPlace={location} opacity={opacity} setLocation={setLocation} setOpenList={setOpenList}></SearchList>) 
        :
          null
        }
    </div>
  )
}

SearchBar.propTypes = {
  location: PropTypes.string,
  setLocation: PropTypes.func,
}



export default React.memo(SearchBar); 