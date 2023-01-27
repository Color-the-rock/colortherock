import styled from "styled-components";

export const Container = styled.div``;

export const Dot = styled.div`
  height: 6px;
  width: 6px;
  background-color: ${(props) =>
    props.color !== undefined ? props.color : "transparent"};
  border-radius: 50%;
  display: flex;
`;
