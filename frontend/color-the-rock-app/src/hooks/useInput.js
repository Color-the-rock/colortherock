import { useState } from "react";

export const useInput = (initValue = "") => {
  const [value, setValue] = useState(initValue);

  const onChange = (e) => {
    const { value } = e.target;
    setValue(value);
  };

  return [value, onChange];
};
