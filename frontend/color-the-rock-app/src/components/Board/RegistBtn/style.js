import styled from "styled-components"

export const Container = styled.div`
      width: 100%;
      height: 100%;
      border: 1px solid transparent;
      /* margin: 30px 16px; */
      min-width: 328px;
      max-width: 600px;
      color: var(--color-secondary);
      border-radius: 40px;
      height: 50px;
      position: relative;
      background-image: linear-gradient(var(--color-background), var(--color-background)), linear-gradient(to right, #ff6cab, #8533ff);
      background-origin: border-box;
      background-clip: content-box, border-box;
      cursor: pointer;

`

export const ButtonWrap = styled.div`
    position: absolute;
    transform: translateY(-50%);
    top:50%;
    width: 100%;
    height: auto;
    text-align: center;
    border: none;
    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 18px;
    line-height: 28px;
    background: linear-gradient(135deg, #FF6CAB 0%, #8533FF 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
`