import styled from "styled-components";

export const Container = styled.div`
  width: 90px;
  background-color: transparent;
  display: flex;
  justify-content: center;
  color: var(--color-tertiary);
`;

export const SelectBox = styled.div`
  position: relative;
  width: 100%;
  padding: 8px;
  border-radius: 12px;
  cursor: pointer;
  background-color: transparent;
  align-self: center;

  &::before {
    content: "⌵";
    position: absolute;
    top: 0px;
    right: 8px;
    font-size: 1.5rem;
    color: var(--color-brand-primary);
  }
`;

export const Label = styled.label`
  margin-left: 4px;
  text-align: center;
  font-size: 0.813rem;
`;

export const SelectOption = styled.ul`
  position: absolute;
  list-style: none;
  top: 32px;
  left: 0;
  width: 100%;
  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
  height: 200px;
  max-height: ${(props) => (props.show ? "none" : "0")};
  padding: 0;
  border-radius: 8px;
  background: var(--color-background);
  color: #ffffff;
  transition: 0.2s ease-in;
`;

export const OptionItem = styled.li`
  font-size: 14px;
  padding: 0.5rem 1rem;
  text-align: center;
  transition: 0.1s ease-in;
  border-bottom: 1px dashed var(--color-border);
  &:hover {
    background-color: #667bf3;
  }
`;

export const Dot = styled.div`
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background-color: ${(props) => (props.color ? props.color : "transparent")};
  margin-right: 0.25rem;
`;
