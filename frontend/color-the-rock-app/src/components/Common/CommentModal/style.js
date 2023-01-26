import styled from "styled-components"



export const Container = styled.div`
  
  /* transform: translate(-16px, -40%); */
  transition: 1000;
  position: relative;
  left: -16px;
  top: 0;
  width: 100vw;
  height: 40vh;
  /* height: 30vh; */
  /* background-color: var(--color-border); */
  border-radius: 10px 10px 0 0;
  background: #272727;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.7);
  /* border-radius: 20px; */
  
`

export const CommentWrap = styled.div`
  height: 100%;
`

export const OrnamentWrap = styled.div`
  height: 0.75rem;
  display: flex;
  justify-content: center;
  align-items: center;
`

export const Ornament = styled.div`
  width: 20vw;
  height: 3px;
  border-radius: 1px;
  background-color: white;
  border: 1px solid white;
`

export const CloseBtnWrap = styled.div`
  margin: 0.25rem 1rem 0.25rem 1rem;
  display: flex;
  justify-content: space-between;
`

export const CommentBtnWrap = styled.div`
  margin: 0 1rem 0 1rem;
  /* width: 100%; */
  
  height: 2.25rem;
`

export const CommentListWrap = styled.div`
  padding: 0 1rem;
`
