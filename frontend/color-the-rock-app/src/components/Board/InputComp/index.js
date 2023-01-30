import React from 'react'
import * as S from "./style"
import PropTypes from "prop-types"

const InputComp = ({placeholder, setTitle, title}) => {

  const handleChange = (e) => {
    setTitle(e.target.value);
  }

  return (
  <S.Container>
      <S.InputContent
        type="text"
        placeholder={placeholder}
        onChange={handleChange}
      />
  </S.Container>
  )
}

InputComp.propTypes = {
  placeholder: PropTypes.string.isRequired,
  setTitle: PropTypes.func,
}

export default React.memo(InputComp);
