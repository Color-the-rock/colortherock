import React from "react";
import { useInput } from "../../../hooks/useInput";
import * as S from "./style";
import PropTypes from "prop-types";
const SearchBar = ({ setSearchValue }) => {
  const [searchValue, onChangeSearchValue] = useInput("");

  const handleOnClickSearch = () => {
    setSearchValue(searchValue);
  };

  const handleKeyPressEnter = (e) => {
    if (e.keyCode === 13) {
      setSearchValue(searchValue);
    }
  };
  return (
    <S.Wrapper>
      <S.InputBar
        type="text"
        placeholder="암장을 검색해주세요."
        value={searchValue}
        onKeyDown={handleKeyPressEnter}
        onChange={onChangeSearchValue}
      />
      <S.SearchButton onClick={handleOnClickSearch} />
    </S.Wrapper>
  );
};

export default SearchBar;
SearchBar.propTypes = {
  setSearchValue: PropTypes.func,
};
