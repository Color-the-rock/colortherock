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
  margin-left: 4px;
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
  @media (max-width: 992px) {
    grid-template-columns: 1fr 1fr 1fr;
  }
  @media (max-width: 768px) {
    grid-template-columns: 1fr 1fr;
  }
  @media (max-width: 350px) {
    grid-template-columns: 1fr;
  }
`;
export const LiveButton = styled.button`
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
