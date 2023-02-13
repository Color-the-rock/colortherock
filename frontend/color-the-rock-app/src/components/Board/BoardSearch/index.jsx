import React from "react";
import PropTypes from "prop-types";
import * as S from "./style";
import BoardSelect from "../BoardSelect";
import { useDispatch, useSelector } from "react-redux";
import { setSearchGymName } from "../../../stores/board/boardSlice";
const BoardSearchBar = ({ getBoardList, setStoreId }) => {
  const dispatch = useDispatch();
  const currentOption = useSelector((state) => state.board.searchColorValue);
  const searchGymName = useSelector((state) => state.board.searchGymName);

  const handleChangeLocation = (e) => {
    if (e.keyCode === 13) {
      getBoardList();
      return;
    }

    const {
      target: { value },
    } = e;

    dispatch(setSearchGymName(value));
    setStoreId(-1); // 검색어 변경으로 초기화
  };

  return (
    <S.Container>
      <BoardSelect currentOption={currentOption} setStoreId={setStoreId} />
      <S.InputBar
        type="text"
        placeholder="암장을 검색해주세요."
        value={searchGymName}
        onChange={handleChangeLocation}
        onKeyDown={handleChangeLocation}
      />
      <S.SearchButton onClick={getBoardList} />
    </S.Container>
  );
};

export default BoardSearchBar;
BoardSearchBar.propTypes = {
  getBoardList: PropTypes.func,
  setStoreId: PropTypes.func,
};
