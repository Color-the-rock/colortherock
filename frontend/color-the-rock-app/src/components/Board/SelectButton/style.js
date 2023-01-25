import styled from "styled-components";

export const Container = styled.div`
  @media(min-width: 992px) {
    
}

  @media(max-width: 991px) {
    width: 43vw;
  }
`;

export const SelectWrap = styled.div`
  @media(min-width: 992px) {

}

  @media(max-width: 991px) {
    display: flex;
    justify-content: flex-start;
    align-items: center;

    margin: 10px 0px;
    height: 40px;

    background-color: var(--color-background);
    border: 1px solid var(--color-border);
    border-radius: 40px;
  }
`;
export const Select = styled.select`
  @media(min-width: 992px) {

}

  @media(max-width: 991px) {
    border: none;
    background-color: var(--color-background);
    margin: 0 10px;
    
    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 1.5rem;
    letter-spacing: -0.01em;
    
    color: var(--color-tertiary); 


  }
`;