import styled from "styled-components";

export const Container = styled.div`
    @media(min-width: 992px) {
    
  }
  
    @media(max-width: 991px) {
      min-width: 328px;

    }
`
export const DateWrap = styled.div`
    @media(min-width: 992px) {

}

  @media(max-width: 991px) {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin: 20px 16px;
    height: 40px;
    
    background-color: var(--color-background);
    color: var(--color-secondary);
    border: 1px solid var(--color-border);
    border-radius: 20px;

    .datepicker {
      margin-left: 10px;
      background-color: var(--color-background);
      color: var(--color-secondary);
      border: none;
      font-family: 'Noto Sans KR';
      font-style: normal;
      font-weight: 400;
      font-size: 1rem;
      line-height: 1.5rem;

    }
  }
`
