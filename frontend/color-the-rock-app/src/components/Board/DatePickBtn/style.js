import styled from "styled-components";

export const Container = styled.div`
  width:100%;
  height: 100%;
  min-width: 328px;
`
export const DateWrap = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  height: 40px;
  background-color: var(--color-background);
  color: var(--color-secondary);
  border: 1px solid var(--color-border);
  border-radius: 20px;
  
  .datepicker {
    background-color: var(--color-background);
    color: var(--color-tertiary);
    border: none;
    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 1.5rem;
    height: 38px;
    padding: 16px;
  }
`
