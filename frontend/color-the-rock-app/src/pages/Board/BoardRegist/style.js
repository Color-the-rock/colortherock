/*
  BoardRegist style.js
*/

import styled from "styled-components";

export const Container = styled.div`
  @media (min-width: 992px) {
  
  }

  @media (max-width: 991px) {
    /* position: fixed; */
    width: 100vw;
    height: 100vh;
  }
`;

export const CloseBtnContainer = styled.div`
  @media (min-width: 992px) {
  
  }

  @media (max-width: 991px) {
    display: flex;
    align-items: center;
    height: 10vh;
  }
`;

export const CloseBtn = styled.div`
  @media (min-width: 992px) {
  
  }

  @media (max-width: 991px) {
    display: flex;
    align-items: center;
    margin-left: 1rem;
    font-weight: 500;
    color: var(--color-secondary);
    font-size: 2rem;
  }
`;

export const ContentWrap = styled.div`
  @media (min-width: 992px) {
  
  }

  @media (max-width: 991px) {
    /* display: flex;
  justify-content: center;
  align-items: flex-start; */
    /* min-width: 360px; */
    width: 100vw;
    height: 90vh;
  }
`;

export const Content = styled.div`
  @media (min-width: 992px) {
  }

  @media (max-width: 991px) {
    /* display: flex;
    flex-direction: column;
    width: 100%;
    min-width: 328px; */
    /* max-width: 400px;  */
  }
`;

export const SelectWrap = styled.div`
  @media (min-width: 992px) {
  }

  @media (max-width: 991px) {
    display: flex;
    justify-content: space-between;
    margin: 0 16px;
    min-width: 328px;
  }
`;
