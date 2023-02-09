import styled from "styled-components";

export const Container = styled.span`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 40px;
  width: 100%;
  background: #272727;
  border-radius: 20px;
  border: none;
  box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.7);
`;

export const InputBtn = styled.input`
  background-color: transparent;
  border: none;
  width: 80%;
  height: 100%;
  padding-left: 1rem;
  color: white;
`;

export const Ornament = styled.div`
  width: 20%;
  padding-right: 1rem;
  text-align: right;
`;

export const SendButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 1.75rem;
  height: 1.75rem;
  border-radius: 50%;
  border: none;
  color: var(--color-white);
  background: linear-gradient(135deg, #ff6cab 0%, #8533ff 100%);
`;
