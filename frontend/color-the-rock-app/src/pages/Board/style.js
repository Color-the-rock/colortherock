import styled from "styled-components";
import { Link } from "react-router-dom";
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
export const LiveButton = styled(Link)`
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
