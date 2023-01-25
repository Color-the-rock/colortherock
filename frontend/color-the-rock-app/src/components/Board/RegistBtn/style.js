import styled from "styled-components"

export const Container = styled.div`
    @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid transparent;
    margin: 30px 16px;
    color: var(--color-secondary);
    border-radius: 40px;
    height: 50px;
    background-image: linear-gradient(var(--color-background), var(--color-background)), linear-gradient(to right, #ff6cab, #8533ff);
    background-origin: border-box;
    background-clip: content-box, border-box;
  }
`

export const ButtonWrap = styled.div`
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 18px;
    line-height: 28px;
    background: linear-gradient(135deg, #FF6CAB 0%, #8533FF 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-fill-color: transparent;
  }
`