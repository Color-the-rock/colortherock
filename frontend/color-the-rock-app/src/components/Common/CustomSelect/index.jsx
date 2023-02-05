import { useState } from "react";
import * as S from "./style";

const CustomSelect = ({ optionValues }) => {
  const [showOption, setShowOption] = useState(false);
  const [currentOption, setCurrentOption] = useState(optionValues[0].key);

  const handleChangeOption = (e) => {
    setCurrentOption(e.target.innerText);
    setShowOption(!showOption);
  };

  return (
    <S.Container>
      <S.SelectBox onClick={() => setShowOption(!showOption)}>
        <S.Label>{currentOption}</S.Label>
        <S.SelectOption show={showOption}>
          {optionValues &&
            optionValues.length > 0 &&
            optionValues.map((option) => {
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

export default CustomSelect;
