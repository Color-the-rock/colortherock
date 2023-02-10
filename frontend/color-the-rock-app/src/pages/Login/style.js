/*
  Login style.js
*/

import styled from "styled-components";

export const Container = styled.div`
  width: 100vw;

  @media (min-width: 992px) {
    height: 80vh;
  }

  @media (max-width: 991px) {
    height: 100vh;
  }
`;

export const CloseBtnContainer = styled.div`
  @media (min-width: 992px) {
  }

  @media (max-width: 991px) {
    display: flex;
    justify-content: flex-end;
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
    margin-right: 1rem;
    font-weight: 500;
    color: var(--color-secondary);
    font-size: 2rem;
  }
`;

export const ContentWrap = styled.div`
  @media (min-width: 992px) {
    height: 80vh;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100vw;
  }

  @media (max-width: 991px) {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100vw;
    height: 85vh;
  }
`;

export const LogoContainer = styled.div`
  @media (min-width: 992px) {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  @media (max-width: 991px) {
    margin: 0 1rem;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
`;

export const LogoImg = styled.img`
  width: 17.25rem;
  height: 2.688rem;
  padding: 0 1rem;
  margin-bottom: 2rem;
`;

export const SecondLogo = styled.img`
  width: 13.625rem;
  height: 4.313rem;
  @media (max-width: 991px) {
    width: 13.625rem;
    height: 4.313rem;
  }
`;

export const LoginContainer = styled.div`
  @media (min-width: 992px) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  @media (max-width: 991px) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
`;
export const LoginWrap = styled.a`
  @media (min-width: 992px) {
    display: flex;
    justify-content: center;
    margin: 8px 16px;
    padding: 0;
    width: 260px;
    height: 50px;
  }

  @media (max-width: 991px) {
    display: flex;
    justify-content: center;
    margin: 8px 16px;
    padding: 0;
    width: 100%;
    height: 50px;
  }
`;
