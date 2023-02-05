import styled from "styled-components";

export const Text = styled.div`
  display: flex;
  align-items: center;
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.625rem;
  margin-top: 2.25rem;
  margin-bottom: 1rem;
  margin-left: ${(props) =>
    props.margin !== "0" ? `${props.margin}px` : "0px"};
`;
