import React, { useState, useEffect } from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const { kakao } = window;

const KakaoSearchList = ({
  searchPlace,
  setLocation,
  setOpenList,
  opacity,
  currentLocation,
}) => {
  const [searchData, setSearchData] = useState([]);

  useEffect(() => {
    const ps = new kakao.maps.services.Places();
    console.log("searchPlace: ", searchPlace);
    ps.keywordSearch(searchPlace, placesSearchCB);
  }, []);

  // 완등 영상 목록 실시간 검색
  useEffect(() => {
    const ps = new kakao.maps.services.Places();
    // 위치 정보 제공시 로직
    if (currentLocation !== undefined && currentLocation !== null) {
      ps.keywordSearch(searchPlace, placesSearchCB, {
        location: new kakao.maps.LatLng(
          currentLocation.lat,
          currentLocation.lng
        ),
      });
      return;
    }

    // 위치 정보 비제공시 로직
    ps.keywordSearch(searchPlace, placesSearchCB);
  }, [searchPlace]);

  const placesSearchCB = (data, status) => {
    if (status === kakao.maps.services.Status.OK) {
      setSearchData(data);
    }
  };

  const handleOnClick = (e) => {
    console.log(e.target.innerText);
    setLocation(e.target.innerText);
    setOpenList(false);
  };

  const closeKakaoSearch = () => {
    console.log("close");
    setOpenList(false);
  };

  return (
    <S.Container opacity={opacity}>
      <S.OutSideArea onClick={closeKakaoSearch} />
      <S.SearchResultWrap>
        {searchData && searchData.length > 0 ? (
          searchData.map((data, index) => (
            <S.SearchResult
              key={index}
              value={data.place_name}
              onClick={handleOnClick}
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

KakaoSearchList.propTypes = {
  searchPlace: PropTypes.string,
  setLocation: PropTypes.func,
  setOpenList: PropTypes.func,
  opacity: PropTypes.string,
  currentLocation: PropTypes.object,
};
