/*
  Singup Style.js
*/

import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;

  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    /* position: fixed; */
    height:  100vh;
  }
`;

export const CloseBtnContainer = styled.div`
@media(min-width: 992px) {
  
}

@media(max-width: 991px) {
  display: flex;
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
    margin-left: 1rem;
    font-weight: 500;
    color: var(--color-secondary);
    font-size: 2rem;
  }
`;


export const ContentWrap = styled.div`
  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    width: 100vw;
    height: 80vh;
    margin: 0px 1rem;
  }
`

export const InputTitle = styled.div`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    padding-bottom: 0.25rem;
    margin-left: 10px;
    font-family: 'Noto Sans KR';
    font-style: normal;
    font-size: 1.5rem;
    letter-spacing: -0.01em;
    color: #E0E2E7;
  }
`
// gradation + border가 충동이 나지 않도록 적용하는 법 배우기!!!
export const InputWrap = styled.div`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    border: 1px solid transparent;
    margin: 10px 0px;
    color: var(--color-secondary);
    border-radius: 20px;
    width: 300px;
    height: 40px;
    background-image: linear-gradient(var(--color-background), var(--color-background)), linear-gradient(to right, #ff6cab, #8533ff);
    background-origin: border-box;
    background-clip: content-box, border-box;
  }
`

export const InputComp = styled.input`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    /* outline: none; */
    border: none;
    
    background-color: var(--color-background);
    color: var(--color-secondary); 
    width: 244px;
    margin: 0 10px;

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 400;
    font-size: 1rem;
    line-height: 1.5rem;
  }
`
export const InputButttonWrap = styled.div`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    border-radius: 20px;

    .FiArrowRightCircle {
      font-size: 1.25rem;
      margin: 5px 3px 0.25rem 0.25rem;
      /* margin-top: 0.05px; */
      color: var(--color-level-v1-start);
    }
  }
`


export const InputButton = styled.button`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    margin-left: 10px;
    display: flex;
    align-items: center;
    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 24px;
    letter-spacing: -0.01em;
    color: var(--color-level-v1-start);
    
    background: linear-gradient(135deg, #FF4E36 0%, #9D2038 100%);

    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-fill-color: transparent;

    .p {
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
`
export const ErrorMessageWrap = styled.div`
  
  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display: flex;
    justify-content: center;
    align-items: center;
    .p {
      font-family: 'Noto Sans KR';
      font-style: normal;
      font-size: 1.5rem;
      letter-spacing: -0.01em;
      color: #E0E2E7;
    }
  }
`

export const ErrorMessage = styled.div`

@media(min-width: 992px) {

}

@media(max-width: 991px) {
  display: flex;
  align-items: center;
  margin-left: 5px;
  
  div {
    margin: 0px 5px;
    display: flex;
    width: 0.75rem;
    height: 0.75rem;
    font-weight: 400;
    justify-content: center;
    align-items: center;
    font-size: 0.5rem;
    color: white;
    background-color: var(--color-level-v1-start);
    /* border: 1px solid white; */
    border-radius: 100%;
  }

  p {
    /* small/500 */

font-family: 'Noto Sans KR';
font-style: normal;
font-weight: 400;
font-size: 12px;
line-height: 24px;
color: var(--color-level-v1-start);
/* identical to box height, or 171% */

display: flex;
align-items: center;
letter-spacing: -0.01em;

  }
}
`