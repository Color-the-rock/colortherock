import styled from "styled-components"
import WebLogo from "../../assets/img/common/web-logo.png"

export const LoginContainer = styled.div`
  padding: 16px;
  display: flex;
  justify-content: center;
  text-align: center;
  align-content: center;
  @media (min-width: 480px) {
    margin-top: 100px;
  }

`;

export const Container = styled.div`
      
  height: 80vh;
  display: flex;
  flex-direction: column;
    
  @media (min-width: 992px) {
    justify-content: center;
    align-content: center;
  }
  
  @media (max-width: 991px) {
    height: 80vh;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 16px;
  }
  
`;

export const Title = styled.div`
  margin-bottom: 50px;
  text-align: center;
  font-size: 20px;
  `

  
  export const ContentWrap = styled.div`
    display: flex;
    text-align: center;
    flex-direction: column;
    justify-content: space-around;
    align-content: center;
  `;
  
  
  export const LoginWrap = styled.a`
  display: flex;
  text-align: center;
  margin-bottom: 20px;
  `
  
  export const LogoImg = styled.div`
  display: flex;
  width: 250px;
  min-height: 42px;
  margin-bottom: 20px;
  text-align: center;
  background-image: url(${WebLogo});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  `