import React from "react";
import { useInput } from "../../../hooks/useInput";
import * as S from "./style";
const SearchBar = () => {
  const [searchValue, onChangeSearchValue] = useInput("");
  return (
    <S.Wrapper>
      <S.InputBar
        type="search"
        placeholder="암장을 검색해주세요."
        value={searchValue}
        onChange={onChangeSearchValue}
      />
      <S.SearchButton />
    </S.Wrapper>
  );
};

export default SearchBar;
