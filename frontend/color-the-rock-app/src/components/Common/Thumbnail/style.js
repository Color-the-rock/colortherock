import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  max-width: 10.75rem;
  min-height: 20.375rem;
  background-color: transparent;
`;

export const ThumbnailImg = styled.img`
  width: 100%;
  min-height: 16rem;
  border-radius: 0.625rem;
  margin-bottom: 0.5rem;
`;

export const VideoText = styled.div`
  display: ${(props) => (props.isLive ? "flex" : "none")};
  font-style: normal;
  font-weight: 700;
  font-size: 0.9rem;
  line-height: 0.9rem;
  letter-spacing: -0.005em;
  color: var(--color-white);
  align-items: center;
  margin-bottom: 0.5rem;
  @media (max-width: 1070px) {
    font-size: 0.75rem;
    line-height: 0.75rem;
  }
`;

export const LiveBadge = styled.div`
  width: 6px;
  height: 6px;
  background: var(--color-badge-live);
  border-radius: 6px;
  margin-right: 4px;
`;

export const Tag = styled.label`
  background: var(--color-background);
  border-radius: 10px;
  height: 1.25rem;
  color: var(--color-secondary);
  font-style: normal;
  font-weight: 400;
  font-size: 0.75rem;
  line-height: 1rem;
  letter-spacing: -0.005em;
  padding: 0.25rem 0.75rem;
  margin: 0.5rem 0px;
  margin-right: 4px;
`;
