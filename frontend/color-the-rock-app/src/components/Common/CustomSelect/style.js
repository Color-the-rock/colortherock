import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  border-radius: 10px;
  color: var(--color-tertiary);
  background-color: transparent;
  /* min-width: 155px; */
`;

export const SelectBox = styled.div`
  display: flex;
  align-items: center;
  position: relative;
  width: 100%;
  min-height: 2.5rem;
  padding: 0.5rem;
  border-radius: 20px;
  cursor: pointer;
  background-color: var(--color-background);
  border: 1px solid var(--color-border);

  &::before {
    content: "⌵";
    position: absolute;
    top: 4px;
    right: 8px;
    font-size: 1.25rem;
    color: var(--color-brand-primary);
  }
`;

export const Label = styled.label`
  margin-left: 0.25rem;
  text-align: center;
  font-size: 0.813rem;
`;

export const SelectOption = styled.ul`
  position: absolute;
  list-style: none;
  top: 38px;
  left: 0;
  width: 100%;
  overflow: hidden;
  max-height: ${(props) => (props.show ? "7.5rem" : 0)};
  padding: 0;
  border-radius: 8px;
  background: var(--color-background);
  color: #ffffff;
  transition: 0.2s ease-in;
  overflow: scroll;

  /* 추가 */
  z-index: 1;
  /* margin-top: 2px; */
  /* border: 1px solid var(--color-border); */
`;

export const OptionItem = styled.li`
  font-size: 14px;
  padding: 0.5rem 1rem;
  text-align: center;
  transition: 0.1s ease-in;
  &:hover {
    background-color: var(--color-brand-primary);
  }
`;
