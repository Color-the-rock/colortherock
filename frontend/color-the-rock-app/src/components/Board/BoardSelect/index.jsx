import { useState } from "react";
import * as S from "./style";
const OptionValue = [
  { key: "색상", value: "color" },
  { key: "암장", value: "gym" },
];
const BoardSelect = () => {
  const [showOption, setShowOption] = useState(false);
  const [currentOption, setCurrentOption] = useState("색상");

  const handleChangeOption = (e) => {
    setCurrentOption(e.target.innerText);
    setShowOption(!showOption);
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
