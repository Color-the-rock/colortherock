import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyles = createGlobalStyle`
  ${reset}

  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700;900&display=swap');

html {
  --color-dark: #191A20;
  --color-background: #272727;
  --color-border: #4D4D4D;
  --color-tertiary: #B2B3B9;
  --color-secondary: #E0E2E7;
  --color-primary: #F7F8FA;
  --color-white: #ffffff;
  --color-brand-primary : #C250D6;
  /* font-size: 62.5%;  */
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
