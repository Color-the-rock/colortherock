import styled from "styled-components";

export const Container = styled.div`
  z-index: 2000;
  display: block;
  width: 100%;
  background-color: var(--color-background);
  position: relative;
  z-index: 10;
  opacity: ${(props) =>
    props.opacity !== "100" ? `${props.opacity}%` : "100%"};
`;

export const OutSideArea = styled.div`
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  opacity: 0%;
  z-index: 1000;
`;

export const SearchResultWrap = styled.ul`
  list-style: none;
  width: 100%;
  display: flex;
  margin-top: 2px;
  z-index: 2000;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  border: 1px solid var(--color-border);
  background-color: var(--color-background);
  border-radius: 10px;
  padding: 10px;
  overflow: scroll;
  overflow: hidden;
  position: absolute;
`;

export const SearchResult = styled.li`
  width: 100%;
  height: 30px;
`;
