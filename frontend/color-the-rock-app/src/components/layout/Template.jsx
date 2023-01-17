import React from "react";
import styled from "styled-components";

const Template = ({ children }) => {
  return <Container>{children}</Container>;
};

export default Template;

const Container = styled.div`
  width: 100%;
  height: calc(100vh - 96px);
  background-color: #fff;
`;
