import React, { useMemo, useState } from "react";
import PropTypes from "prop-types";
import * as S from "./style";
import SearchList from "../SearchList";

const SearchBar = ({ location, setLocation, opacity = "100" }) => {
  const [OpenList, setOpenList] = useState(false);
  const [currentLocation, setCurrentLocation] = useState(null);

  const handleChange = (e) => {
    setLocation(e.target.value);
    setOpenList(false);
  };

  const handleOnKeyPress = (e) => {
    // Enter event 발생시
    if (e.key === "Enter") {
      setLocation(location);
      setOpenList(true);
    }
  };

  useMemo(() => {
    const handleSuccessCallback = (position) => {
      setCurrentLocation({
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      });
    };

    const handleFailCallback = () => {
      console.log("[handleFailCallback] 현재 위치 받기 실패 ");
    };

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        handleSuccessCallback,
        handleFailCallback
      );
    }
  }, []);

  return (
    <div>
      <S.Container opacity={opacity}>
        <S.InputContent
          type="search"
          value={location}
          placeholder="암장을 입력해주세요."
          onChange={handleChange}
          onKeyDown={handleOnKeyPress}
        />
      </S.Container>

      {OpenList ? (
        <SearchList
          searchPlace={location}
          opacity={opacity}
          setLocation={setLocation}
          setOpenList={setOpenList}
          currentLocation={currentLocation}
        ></SearchList>
      ) : null}
    </div>
  );
};

export default React.memo(SearchBar);

SearchBar.propTypes = {
  location: PropTypes.string,
  setLocation: PropTypes.func,
  opacity: PropTypes.string,
};
