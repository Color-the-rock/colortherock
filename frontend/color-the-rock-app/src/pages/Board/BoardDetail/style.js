import styled, { keyframes } from "styled-components"


const slideIn = keyframes`
  from {
    margin-top: 100%;
  }
  to {
    margin-top: 0%;
  }
`

export const HeaderWrap = styled.div`
  height: 10vh;
`

export const Container = styled.div`
  width: 100vw;
  height: 90vh;
`

export const ContentContainer = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
`

export const ContentWrap = styled.div`
  width: 100%;
  height: 90vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 360px;
  max-width: 600px;
`

export const ArrowLeftBtnWrap = styled.div`
  padding-left: 1rem;
  `

export const VideoWrap = styled.div`
  display: block;
  position: relative;
  width: 100%;
  /* height: 600px; */
`

export const Video = styled.video`
  /* display: block; */
  width: 100%;
  height: 45vh;

  object-fit: cover;
`

export const ComponenentWrap = styled.div`
  width: 100%;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
`

export const FalseWrap = styled.div`
  width: 100%;
  /* height: 100%; */
  min-width: 328px;
  max-width: 600px;
  `

export const SelectButtonWrap = styled.div`
  width: 100%;
  margin-bottom: 1rem;
  
  `

export const selectBtnContent = styled.span`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-self: center;
  grid-template-columns: 1fr 1fr;
  grid-gap: 1rem;
  `

export const CommentModalWrap = styled.div`
  width: 100%;
  height: 50vh;
  padding-top: 5px;
  background-color: transparent;
  animation-duration: 1s;
  animation-name: ${slideIn};
  `

export const CommentWrap = styled.div`
  width: 100%;
  padding-left: 1rem;
  padding-right: 1rem;
  height: 40px;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  `

export const ThumbnailList = styled.div`
  align-self: center;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 1rem;
  /* margin-top: 20px; */
  padding-left: 1rem;
  padding-right: 1rem;
  
  @media (max-width: 1070px) {
    grid-template-columns: 1fr 1fr;
  }
  `
