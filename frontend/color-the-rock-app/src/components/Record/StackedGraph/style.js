import styled from "styled-components";

export const GraphWrapper = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 400px;
  padding: 1rem;
  background-color: var(--color-background);
  @media (max-width: 992px) {
    height: 240px;
    max-height: 280px;
  }
`;

export const GraphLabels = styled.div`
  min-width: 60px;
  background-color: transparent;
  color: var(--color-white);
  @media (max-width: 992px) {
    margin-left: 1rem;
  }
`;

export const Label = styled.div`
  display: flex;
  justify-content: start;
  align-items: center;
  margin-bottom: 0.75rem;
`;

export const LabelColor = styled.div`
  display: inline-block;
  width: 1rem;
  height: 1rem;
  border-radius: 0.25rem;
  background-color: ${(props) => (props.color ? props.color : "red")};
`;

export const LabelText = styled.label`
  margin-left: 0.25rem;
  font-size: 0.8rem;
`;
