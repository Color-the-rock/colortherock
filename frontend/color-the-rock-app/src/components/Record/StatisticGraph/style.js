import styled from "styled-components";

export const GraphWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-width: 300px;
  padding: 1rem;
  background-color: var(--color-background);
  border-radius: 10px;
  @media (max-width: 992px) {
    min-width: 200px;
  }
`;

export const GraphTitle = styled.h1`
  font-style: normal;
  font-weight: 700;
  font-size: 0.875rem;
  line-height: 1.5rem;
  margin-bottom: 0.5rem;
`;

export const ChallengeBar = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  width: 100%;
  height: 1.6rem;
  border-radius: 2rem;
  background-color: var(--color-border);
  margin-bottom: 3rem;
`;

export const BarLabel = styled.label`
  position: absolute;
  right: ${(props) => (props.right !== undefined ? `${props.right}px` : "0px")};
  bottom: -28px;
  font-style: normal;
  font-weight: 500;
  font-size: 0.75rem;
  line-height: 1.5rem;
  letter-spacing: -0.01em;
  color: var(--color-tertiary);
`;

export const Success = styled.div`
  display: inline-block;
  background: linear-gradient(
    135deg,
    var(--color-brand-gradient-start) 0%,
    var(--color-brand-gradient-end) 100%
  );
  width: ${(props) =>
    props.successCount !== 0
      ? `${(props.successCount / props.videoCount) * 100}%`
      : "0%"};
  height: 100%;
  border-radius: 2rem;
`;

// home gym graph
export const HomeGymGraph = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  width: 100%;
  height: 1.6rem;
  border-radius: 2rem;
  background-color: ${(props) => {
    return props.length !== 0
      ? "var(--color-brand-shade)"
      : "var(--color-border)";
  }};
  margin-bottom: 1rem;
`;

export const VisitedState = styled.div`
  display: inline-block;
  background-color: ${(props) =>
    props.count ? `rgba(194, 80, 214, ${props.count})` : "rgb(194, 80, 214)"};
  width: ${(props) => (props.percent ? `${props.percent}%` : "0%")};
  height: 100%;

  &:first-child {
    border-top-left-radius: 2rem;
    border-bottom-left-radius: 2rem;
  }
  &:last-child {
    border-top-right-radius: 2rem;
    border-bottom-right-radius: 2rem;
  }
`;
