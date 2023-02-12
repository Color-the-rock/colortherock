import styled from "styled-components";

export const ContainerWrap = styled.div`
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  background-color: transparent;

  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const Container = styled.div`
  width: 100%;
  height: 100%;
  max-width: 600px;
  padding-left: 1rem;
  padding-right: 1rem;
`;

export const ThumbnailList = styled.div`
  align-self: center;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 1rem;
  margin-top: 20px;
  @media (max-width: 992px) {
    grid-template-columns: 1fr 1fr 1fr;
  }
  @media (max-width: 768px) {
    grid-template-columns: 1fr 1fr;
  }
  @media (max-width: 350px) {
    grid-template-columns: 1fr;
  }
`;

export const ArrowLeftBtnWrap = styled.span`
  width: 100%;
  display: flex;
`;

export const CalendarWrap = styled.div`
  padding: 1rem 0 1rem 0;
`;

export const ComponentWrap = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 1rem;
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

export const TitleWrap = styled.h2``;
export const Message = styled.div``;
