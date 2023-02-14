import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const BoardRadioBtn = ({
  isPublic,
  setIsPublic,
  firstText = "성공",
  SecondText = "실패",
  opacity = "100",
}) => {
  const onChangeRadioButton = (e) => {
    const { value } = e.target;
    setIsPublic(value === "true");
  };

  return (
    <S.Container opacity={opacity}>
      <S.ContentWrap checked={isPublic === true}>
        <S.RadioButton
          className="radiobtn"
          type="radio"
          name="type"
          value={true}
          onChange={onChangeRadioButton}
        />
        <span>{firstText}</span>
      </S.ContentWrap>
      <S.ContentWrap checked={isPublic === false}>
        <S.RadioButton
          type="radio"
          name="type"
          value={false}
          onChange={onChangeRadioButton}
        />
        <span>{SecondText}</span>
      </S.ContentWrap>
    </S.Container>
  );
};

BoardRadioBtn.propTypes = {
  isPublic: PropTypes.bool.isRequired,
  setIsPublic: PropTypes.func.isRequired,
  firstText: PropTypes.string,
  SecondText: PropTypes.string,
  opacity: PropTypes.string,
};

export default BoardRadioBtn;
