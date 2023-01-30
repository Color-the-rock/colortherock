import styled from "styled-components"


export const Container = styled.span`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 36px;
  width: 100%;
  background: #272727;
  border-radius: 20px;
  border: 1px solid var(--color-border);
  /* box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.7); */
`

export const InputBtn = styled.input`
  background-color: transparent;
  border: none;
  width: 80%;
  padding-left: 1rem;
  color: white;
`

export const Ornament = styled.div`
  width: 20%;
  padding-right: 1rem;
  text-align: right;
`