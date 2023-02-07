import { useState } from "react";
import * as S from "./style";

const CustomSelect = ({ setter, optionValues }) => {
  const [showOption, setShowOption] = useState(false);
  const [currentOption, setCurrentOption] = useState(optionValues[0].key);

  const handleChangeOption = (value, e) => {
    console.log(value);
    console.log(e);
    // setCurrentOption(e.target.innerText);
    // console.log("뭐야:", e.target.value);
    setter(value);
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
                  onClick={(e) => handleChangeOption(option.value, e)}
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
