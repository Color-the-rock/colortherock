import { useState } from "react";
import * as S from "./style";

const CustomSelect = ({ setter, optionValues }) => {
  const [showOption, setShowOption] = useState(false);
  const [currentOption, setCurrentOption] = useState(optionValues[0].key);

  const handleChangeOption = (e) => {
    // console.log("e: ", e.target.__reactProps$lwgv3va7748.value);
    setCurrentOption(e.target.innerText);
    // setter(e.target.__reactProps$lwgv3va7748.value);
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
