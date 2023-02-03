import React, { useState, useEffect } from "react";
import * as S from "./style";

const { kakao } = window;

const KakaoSearchList = ({
  searchPlace,
  setLocation,
  setOpenList,
  opacity,
}) => {
  const [searchData, setSearchData] = useState([]);

  useEffect(() => {
    const ps = new kakao.maps.services.Places();
    console.log("searchPlace: ", searchPlace);
    ps.keywordSearch(searchPlace, placesSearchCB);
  }, []);

  const placesSearchCB = (data, status, pagination) => {
    setSearchData(data);
  };

  const handleClick = (e) => {
    console.log(e.target.innerText);
    setLocation(e.target.innerText);
    setOpenList(false);
  };

  const CloseKakaoSearch = () => {
    console.log("close");
    setOpenList(false);
  };

  return (
    <S.Container opacity={opacity}>
      <S.OutSideArea onClick={CloseKakaoSearch} />
      <S.SearchResultWrap>
        {searchData.length > 0 ? (
          searchData.map((data, index) => (
            <S.SearchResult
              key={index}
              value={data.place_name}
              onClick={handleClick}
            >
              {data.place_name}
            </S.SearchResult>
          ))
        ) : (
          <div>검색결과가 없습니다.</div>
        )}
      </S.SearchResultWrap>
    </S.Container>
  );
};

export default React.memo(KakaoSearchList);
