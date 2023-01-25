import styled from "styled-components";

export const Container = styled.div`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    min-width: 328px;
  }
`

export const InputWrap = styled.div`
@media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    
    display: flex;
    justify-content: flex-start;
    align-items: center;
    
    margin: 10px 16px;
    height: 40px;
    
    background-color: var(--color-background);
    color: var(--color-secondary);
    border: 1px solid var(--color-border);
    border-radius: 20px;
  }
`

export const InputContent = styled.input`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    border: none;
    width: 70vw;
    min-width: 280px;
    background-color: var(--color-background);
    color: var(--color-tertiary); 
    margin: 0 10px;
    letter-spacing: -0.01em;
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 1.5rem;
  }`