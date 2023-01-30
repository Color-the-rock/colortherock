import styled from "styled-components";

export const Container = styled.div`
  min-width: 328px;
  max-width: 600px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  
  height: 40px;
  
  background-color: var(--color-background);
  color: var(--color-secondary);
  border: 1px solid var(--color-border);
  border-radius: 20px;
`

export const InputWrap = styled.div`


`

export const InputContent = styled.input`
  border: none;
  width: 100%;
  height: 100%;
  padding: 16px;
  min-width: 280px;
  background-color: var(--color-background);
  color: var(--color-tertiary); 
  letter-spacing: -0.01em;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 1.5rem;
  @media(min-width: 992px) {
  }

  @media(max-width: 991px) {
  }`