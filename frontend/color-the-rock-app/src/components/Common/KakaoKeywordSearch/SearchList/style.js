import styled from "styled-components"

export const Container = styled.div`
  @media(min-width: 992px) {
  
  }

  @media(max-width: 991px) {
    margin: 1px 1rem 0 1rem;
  }
`;

export const OutSideArea = styled.div`
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  opacity: 0%;
  z-index: 1000;

`

export const SearchResultWrap = styled.div`
  @media(min-width: 992px) {
  
  }

  @media(max-width: 991px) {
    z-index: 2000;
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    border: 1px solid white;
    border-radius: 10px;
    padding: 10px;
    
    .button {
      border: 1px solid white;
    }
  }
`;
