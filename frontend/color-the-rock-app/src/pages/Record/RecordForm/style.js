import styled from "styled-components";

export const ContainerWrap = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: transparent;

  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const HeaderWrap = styled.div`
  height: 10vh;
`;

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
`;

export const ArrowLeftBtnWrap = styled.div`
  width: 100%;
  display: flex;
  margin-top: 1rem;
`;

export const ContentWrap = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
`;
export const ComponenentWrap = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 1rem;
`;

export const Content = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  /* background-color: red; */

  padding: 0 1rem;
  @media (min-width: 992px) {
    width: 600px;
  }
  @media (max-width: 991px) {
    width: 100vw;
    height: 90vh;
    min-width: 360px;
    max-width: 600px;
  }

  div {
    display: block;
  }
`;

export const SelectButtonWrap = styled.div`
  width: 100%;
  margin-bottom: 1rem;
`;

export const selectBtnContent = styled.span`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-self: center;
  grid-template-columns: 1fr 1fr;
  grid-gap: 1rem;
`;

export const PickDate = styled.input`
  background-color: #0080ff;

  &::before {
  }

  &::after {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
  }
`;
