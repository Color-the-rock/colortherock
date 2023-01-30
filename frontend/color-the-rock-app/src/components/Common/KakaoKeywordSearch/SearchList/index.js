import React, { useState, useEffect } from 'react'
import { Desktop, Mobile } from "../../../layout/Template"
import * as S from "./style"
// import SearchData from "./style"


const { kakao } = window;

const KakaoSearchList = ({searchPlace, setOpenList}) => {

  const [searchData, setSearchData] = useState([]);
  
  useEffect(() => {
    const ps = new kakao.maps.services.Places();
    console.log("searchPlace: ",searchPlace);
    ps.keywordSearch(searchPlace, placesSearchCB);

    // function placesSearchCB(data, status, pagination) {
    //   console.log("data", data);
    //   setSearchData(data);
    // }

    // console.log("ps", ps);
  }, []);

  const placesSearchCB = (data, status, pagination) => {
    console.log("data: ", data);
    setSearchData(data);
  }

  const handleClick = () => {
    
    // 여기에 결과 설정...
    console.log("성공")

    setOpenList(false);
  }

  const CloseKakaoSearch = () => {
    console.log("close");
    setOpenList(false);
  }

  return (
    <div>
      <Desktop>

      </Desktop>

      <Mobile>
        <S.Container>
          <S.OutSideArea onClick={CloseKakaoSearch}/>
          <S.SearchResultWrap>
            {searchData.map((data, index) => (
              <button key={index} onClick={handleClick}>{data.place_name}</button>
              
            ))}
          </S.SearchResultWrap>
        </S.Container>
      </Mobile>
    </div>
  )

}


export default React.memo(KakaoSearchList);