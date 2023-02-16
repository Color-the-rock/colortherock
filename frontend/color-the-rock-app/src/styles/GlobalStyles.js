import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyles = createGlobalStyle`
  ${reset}

html {
  --color-dark: #191A20;
  --color-background: #272727;
  --color-border: #4D4D4D;
  --color-tertiary: #B2B3B9;
  --color-secondary: #E0E2E7;
  --color-primary: #F7F8FA;
  --color-white: #ffffff;
  --color-brand-primary : #C250D6;
  --color-brand-shade: #8A3B98;
  --color-brand-tint: #CC98D6;
  --color-brand-gradient-start: #FF6CAB;
  --color-brand-gradient-end: #8533FF;
  --color-level-v0-start: #FFFFFF;
  --color-level-v0-end: #B1B1B1;
  --color-level-v1-start: #FF4E36;
  --color-level-v1-end: #9D2038;
  --color-level-v2-start: #FFA62E;
  --color-level-v2-end: #EA4D2C;
  --color-level-v3-start: #FFCF1B;
  --color-level-v3-end: #FF881B;
  --color-level-v4-start: #C0FA87;
  --color-level-v4-middle: #3BB2B8;
  --color-level-v4-end: #ADF99A;
  --color-level-v5-start: #6EE2F5;
  --color-level-v5-end: #6454F0;
  --color-level-v6-start: #3C5DD3;
  --color-level-v6-end: #090E82;
  --color-level-v7-start: #FF6CAB;
  --color-level-v7-end: #8533FF;
  --color-level-v8-start: #695F54;
  --color-level-v8-end: #403121;
  --color-badge-live:#FF4E36
}

body {

  margin: 0;
  font-family: 'Noto Sans KR', -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: var(--color-primary);
  background-color: var(--color-dark);
  font-size: 1rem;
} 

 a{
      text-decoration: none;
      color: inherit;
  }
  *{
      box-sizing: border-box;
  }
  input, textarea { 
    -moz-user-select: auto;
    -webkit-user-select: auto;
    -ms-user-select: auto;
    user-select: auto;
  }
  input:focus {
    outline: none;
  }
  button {
    border: none;
    background: none;
    padding: 0;
    cursor: pointer;
  }
  select {
    border: none;
    background: none;
    -webkit-appearance: none;
    -moz-appearance: none;
	appearance: none;
  }
`;

export default GlobalStyles;
