import styled from "styled-components";
export const Container = styled.div`
  width: 100%;
  height: 100vh;
  background-color: transparent;
  padding: 0px 1rem;
  display: flex;
  flex-direction: column;
  position: relative;
  margin-top: 5rem;
`;

export const Title = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  border-bottom: 1px solid #272727;
`;

export const LiveTag = styled.span`
  width: 3.25rem;
  height: 1.75rem;
  border-radius: 4px;
  padding: 4px;
  background-color: var(--color-badge-live);
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.75rem;
  text-align: center;
`;

export const Description = styled.div`
  font-style: normal;
  font-weight: 400;
  font-size: 0.875rem;
  line-height: 1.25rem;
  text-align: center;
  letter-spacing: -0.01em;
  margin: 1.25rem 0px;
  color: var(--color-tertiary);
`;

export const ThumbnailList = styled.div`
  align-self: center;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 1rem;
  margin-top: 20px;
  @media (max-width: 1070px) {
    grid-template-columns: 1fr 1fr;
  }
`;
export const RegisterButton = styled.button`
  width: 50px;
  height: 50px;
  border-radius: 50%;
  position: fixed;
  bottom: 16px;
  right: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: pink;
  border: 3px solid transparent;
  background-image: linear-gradient(var(--color-dark), var(--color-dark)),
    linear-gradient(
      to right,
      var(--color-brand-gradient-start) 0%,
      var(--color-brand-gradient-end) 100%
    );
  background-origin: border-box;
  background-clip: content-box, border-box;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
`;

export const Message = styled.p`
  width: 100%;
  text-align: center;
  margin-top: 2rem;
`;

export const RegisterModal = styled.div`
  width: 100vw;
  height: calc(var(--vh, 1vh) * 100);
  position: fixed;
  top: 0px;
  left: 0px;
  background-color: rgba(242, 242, 242, 0.3);
  overflow: hidden;
  z-index: 1000;
  padding-bottom: 32px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
`;

export const ModalButtonWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
`;

export const ModalButton = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 358px;
  max-width: 600px;
  height: 50px;
  border: 2px solid transparent;
  margin: 0.5rem 1rem;
  border-radius: 40px;
  position: relative;
  background-image: linear-gradient(
      var(--color-background),
      var(--color-background)
    ),
    linear-gradient(to right, #ff6cab, #8533ff);
  background-origin: border-box;
  background-clip: content-box, border-box;
  cursor: pointer;
`;

export const GradientText = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 28px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: -0.02em;
  background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
`;
