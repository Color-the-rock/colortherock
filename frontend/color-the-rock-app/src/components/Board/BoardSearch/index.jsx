import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import * as S from "./style";
import BoardSelect from "../BoardSelect";
import boardApi from "../../../api/board";
const BoardSearchBar = ({
  setResult,
  setStoreId,
  setLoading,
  storeId = -1,
}) => {
  const [currentOption, setCurrentOption] = useState("색상");
  const [searchGymName, setSearchGymName] = useState("");

  const handleChangeLocation = (e) => {
    const {
      target: { value },
    } = e;
    setSearchGymName(value);
  };

  const handleOnClick = () => {
    setLoading(true);
    const requestData = {
      storeId: storeId,
      color: currentOption === "색상" ? "" : currentOption,
      gymName: searchGymName,
    };
    boardApi
      .getAllVideo(requestData)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200", _result);
          setResult(_result);
          let lastId =
            _result[_result.length - 1].videoBoardId === undefined
              ? -1
              : _result[_result.length - 1].videoBoardId;
          setStoreId(lastId);
        }
      })
      .catch((error) => console.log(error))
      .finally(() => {
        setTimeout(() => setLoading(false), 200);
      });
  };

  useEffect(() => {
    handleOnClick();
  }, [currentOption]);

  return (
    <S.Container>
      <BoardSelect
        currentOption={currentOption}
        setCurrentOption={setCurrentOption}
      />
      <S.InputBar
        type="text"
        placeholder="암장을 검색해주세요."
        value={searchGymName}
        onChange={handleChangeLocation}
      />
      <S.SearchButton onClick={handleOnClick} />
    </S.Container>
  );
};

export default BoardSearchBar;
BoardSearchBar.propTypes = {
  setResult: PropTypes.func,
  setLoading: PropTypes.func,
  setStoreId: PropTypes.func,
  storeId: PropTypes.number,
};
