import React, { useEffect, useState } from "react";
import * as S from "./style";
import BoardSelect from "../BoardSelect";
import SearchList from "../../Common/KakaoKeywordSearch/SearchList";
const BoardSearchBar = ({ searchLocation, setSearchLocation }) => {
  const [isOpenSearchList, setOpenSearchList] = useState(false);
  const handleChangeLocation = (e) => {
    setSearchLocation(e.target.value);

    if (e.target.value !== "") {
      setOpenSearchList(true);
    } else {
      setOpenSearchList(false);
    }
  };

  const handleOnClick = () => {
    console.log("handleOnClick()....");
    
  };

  useEffect(() => {
    console.log("암장?? ", searchLocation);
  }, [searchLocation]);

  return (
    <S.Container>
      <BoardSelect />
      <S.InputBar
        type="text"
        placeholder="암장을 검색해주세요."
        value={searchLocation}
        onChange={handleChangeLocation}
      />
      <S.SearchButton />
      {isOpenSearchList ? (
        <S.SearchListWrapper>
          <SearchList
            searchPlace={searchLocation}
            setLocation={setSearchLocation}
            setOpenList={setOpenSearchList}
            handleOnClick={handleOnClick}
          ></SearchList>
        </S.SearchListWrapper>
      ) : null}
    </S.Container>
  );
};

export default BoardSearchBar;
