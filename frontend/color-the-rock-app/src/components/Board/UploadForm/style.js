import styled from "styled-components";


export const Container = styled.div`
   @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    display: flex;
    height: 35vh;
    min-width: 328px;
    border: 1px solid var(--color-border);
    border-radius: 20px;
    margin: 0 16px;
    margin-bottom: 8px;
  }
`

export const UploadArea = styled.div`
  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    display:flex;
    width:100%;
    height: 100%;
    justify-content: center;
    align-items: center;
  }
`

export const Input = styled.input`
  @media(min-width: 992px) {

  }

  @media(max-width: 991px) {
    border: none;
  }
`