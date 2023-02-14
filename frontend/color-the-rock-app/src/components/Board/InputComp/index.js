import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const InputComp = ({ title, placeholder, handleChange, opacity = "100" }) => {
  return (
    <S.Container opacity={opacity}>
      <S.InputContent
        type="text"
        placeholder={placeholder}
        defaultValue={title}
        onChange={(e) => handleChange(e.target.value)}
        maxLength="20"
      />
    </S.Container>
  );
};

export default React.memo(InputComp);

InputComp.propTypes = {
  placeholder: PropTypes.string.isRequired,
  setTitle: PropTypes.func,
  opacity: PropTypes.string,
};
