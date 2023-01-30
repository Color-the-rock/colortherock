import styled from "styled-components"

export const RadioGroup = styled.span`
  width: 100%;
  height: 40px;
  text-align: center;
  color: var(--color-secondary);
  background-color: var(--color-background);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  min-width: 328px;
  max-width: 600px;
  display: grid;
  grid-template-columns: 1fr 1fr;
`

export const RadioLabel = styled.label`
  width: 100%;
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.625rem;
  color: ${(props) =>
    props.checked ? "var(--color-white)" : "var(--color-tertiary)"};



  &:nth-child(1) {

    border-top: 10px;
    border-right: 10px;
    /* border: 1px solid white; */
  }
  &:nth-child(2) {

    border-bottom: 10px;
    border-left: 10px;
    /* border: 1px solid white; */
  }
`
export const RadioButton = styled.input`
  appearance: none;
`;