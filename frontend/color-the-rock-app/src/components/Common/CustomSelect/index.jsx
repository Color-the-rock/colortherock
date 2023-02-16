import { useState, useEffect } from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const CustomSelect = ({ setter, optionValues, defaultValue = 0 }) => {
  const [showOption, setShowOption] = useState(false);
  const [currentOption, setCurrentOption] = useState(
    defaultValue !== 0 ? defaultValue : optionValues[0].key
  );

  useEffect(() => {
    if (defaultValue === 0) return;
    setCurrentOption(defaultValue);
  }, [defaultValue]);

  const handleChangeOption = (option, e) => {
    console.log(option);
    console.log(e);
    setter(option.value);
    setCurrentOption(option.key);
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
                  onClick={(e) => handleChangeOption(option, e)}
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

CustomSelect.propTypes = {
  setter: PropTypes.func,
  optionValues: PropTypes.array,
  defaultValue: PropTypes.number,
};
