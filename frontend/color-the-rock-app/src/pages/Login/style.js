/*
  Login style.js
*/

import styled from "styled-components"

export const Container = styled.div`
  width: 100vw;
  
  @media(min-width: 992px) {
    height: 80vh;
  }
  
  @media(max-width: 991px) {
    /* position: fixed; */
    height: 100vh;
  }
`

export const CloseBtnContainer = styled.div`
@media(min-width: 992px) {
  
}

@media(max-width: 991px) {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 10vh;
}
`

export const CloseBtn = styled.div`

  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display: flex;
    align-items: center;
    margin-right: 1rem;
    font-weight: 500;
    color: var(--color-secondary);
    /* margin-left: 1rem; */
    font-size: 2rem;
  }
`;


export const ContentWrap = styled.div`
  @media(min-width: 992px) {
    height: 80vh;
  }
  
  @media(max-width: 991px) {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100vw;
    height: 85vh;
  }
`



export const LogoContainer = styled.div`
  
  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    margin: 0 16px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
`

export const LogoImg = styled.img`
  
  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    width: 176px;
    height: 101px;
    margin: 0 16px;
  }
`

export const SecondLogo = styled.div`



  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display: flex;
    justify-content: center;
    
    margin-top: 10px;

    white-space: pre-wrap;

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 24px;
    line-height: 34px;

  }
`

export const LoginContainer = styled.div`

  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
`
export const LoginWrap = styled.a`

  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display: flex;
    justify-content: center;
    margin: 8px 16px;
    padding: 0;
    width: 100%;
    height: 50px;

  }
`
