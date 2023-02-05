import { useState } from "react";
import * as S from "./style";
const OptionValue = [
  { key: "색상", value: "" },
  { key: "흰색", value: "흰색" },
  { key: "빨강", value: "빨강" },
  { key: "주황", value: "주황" },
  { key: "노랑", value: "노랑" },
  { key: "연두", value: "연두" },
  { key: "초록", value: "초록" },
  { key: "하늘", value: "하늘" },
  { key: "파랑", value: "파랑" },
  { key: "남색", value: "남색" },
  { key: "보라", value: "보라" },
  { key: "핑크", value: "핑크" },
  { key: "검정", value: "검정" },
  { key: "갈색", value: "갈색" },
  { key: "회색", value: "회색" },
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
