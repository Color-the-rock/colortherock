import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";

const InputComp = ({ title, placeholder, handleChange, opacity = "100" }) => {
  return (
    <S.Container opacity={opacity}>
      <S.InputContent
        type="text"
        placeholder={placeholder}
        value={title}
        onChange={handleChange}
      />
    </S.Container>
  );
};

InputComp.propTypes = {
  placeholder: PropTypes.string.isRequired,
  setTitle: PropTypes.func,
  opacity: PropTypes.string,
};

export default React.memo(InputComp);
