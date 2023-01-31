import styled from "styled-components";

export const ChattingContainer = styled.div`
  position: absolute;
  right: 16px;
  top: 32px;
  width: 600px;
  margin: 0 auto;
  height: 200px;
  background-color: #fff;
  border: 2px solid #ddd;
  z-index: 10;
`;

export const Title = styled.h3`
  text-align: center;
`;

export const TextList = styled.ul`
  list-style: none;
  background-color: transparent;
  border-bottom: 2px dashed #e4e4e4;
`;

export const Text = styled.li`
  display: block;
  color: black;
`;
