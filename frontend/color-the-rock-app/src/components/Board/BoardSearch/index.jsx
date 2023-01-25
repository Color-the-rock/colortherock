import React from "react";
import * as S from "./style";
import SearchBar from "../../Common/Search";
import BoardSelect from "../BoardSelect";
const BoardSearchBar = () => {
  return (
    <S.Container>
      <BoardSelect />
      <S.InputBar type="text" placeholder="암장을 검색해주세요." />
      <S.SearchButton />
    </S.Container>
  );
};

export default BoardSearchBar;
