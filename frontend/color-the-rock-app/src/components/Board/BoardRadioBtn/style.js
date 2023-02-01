import styled, {css} from "styled-components"

export const Container = styled.span`
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  grid-template-columns: 1fr 1fr;
  opacity: ${(props) => 
    props.opacity !== "100" ? `${props.opacity}%` : "100%"};
`

export const ContentWrap = styled.label`
    width: 100%;
    height: 100%;
    display: flex;
    z-index: 0;
    justify-content: center;
    align-items: center;
    text-align: center;
    border: 1px solid transparent;
    cursor: pointer;
    font-weight: 700;
    &:nth-child(1) {
    border-radius: 20px 0 0 20px;
  }

  &:nth-child(2) {
    border-radius: 0 20px 20px 0;
  }

    ${(props) =>
      props.checked ?
      css`
        border: 2px solid transparent;
        background-image: linear-gradient(var(--color-background), var(--color-background)), linear-gradient(to right, #ff6cab, #8533ff);
        background-origin: border-box;
        background-clip: content-box, border-box;

        span {
          color: transparent;
          background: linear-gradient(135deg, #FF6CAB 0%, #8533FF 100%);
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
        }
        
      `
      :
      css`
        background: var(--color-background);
        border: 2px solid var(--color-border);
        color: var(--color-secondary);
      `
    }

`

export const RadioButton = styled.input`
  appearance: none;

  
`;