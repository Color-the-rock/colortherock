import { useState } from "react";
import * as S from "./style";
import PropTypes from "prop-types";
import { useDispatch, useSelector } from "react-redux";
import { setSearchColorValue } from "../../../stores/board/boardSlice";
const OptionValue = [
  { key: "색상", value: "", color: "" },
  { key: "흰색", value: "흰색", color: "white" },
  { key: "빨강", value: "빨강", color: "red" },
  { key: "주황", value: "주황", color: "orange" },
  { key: "노랑", value: "노랑", color: "yellow" },
  { key: "연두", value: "연두", color: "greenyellow" },
  { key: "초록", value: "초록", color: "green" },
  { key: "하늘", value: "하늘", color: "skyblue" },
  { key: "파랑", value: "파랑", color: "blue" },
  { key: "남색", value: "남색", color: "navy" },
  { key: "보라", value: "보라", color: "purple" },
  { key: "핑크", value: "핑크", color: "pink" },
  { key: "검정", value: "검정", color: "black" },
  { key: "갈색", value: "갈색", color: "brown" },
  { key: "회색", value: "회색", color: "grey" },
];
const BoardSelect = ({ setStoreId }) => {
  const dispatch = useDispatch();
  const [showOption, setShowOption] = useState(false);
  const currentOption = useSelector((state) => state.board.searchColorValue);

  const handleChangeOption = (e) => {
    dispatch(setSearchColorValue(e.target.innerText));
    setShowOption(!showOption);
    setStoreId(-1);
  };

  return (
    <S.Container>
      <S.SelectBox onClick={() => setShowOption(!showOption)}>
        <S.Label>{currentOption}</S.Label>
        <S.SelectOption show={showOption}>
          {OptionValue.map((option) => {
            return (
              <S.OptionItem
                key={option.key}
                value={option.value}
                onClick={handleChangeOption}
              >
                <S.Dot color={option.color} />
                {option.key}
              </S.OptionItem>
            );
          })}
        </S.SelectOption>
      </S.SelectBox>
    </S.Container>
  );
};

export default BoardSelect;

BoardSelect.propTypes = {
  setStoreId: PropTypes.func,
};
