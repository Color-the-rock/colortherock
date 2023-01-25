import styled from "styled-components";
import { HiOutlineSearch } from "react-icons/hi";
export const Container = styled.div`
  width: 100%;
  max-width: 600px;
  margin: 0px auto;
  display: flex;
  position: relative;
  background: var(--color-background);
  border: 1px solid var(--color-border);
  border-radius: 1.25rem;
`;

export const InputBar = styled.input`
  width: 100%;
  max-width: 600px;
  height: 2.5rem;
  background: transparent;
  border: none;
  margin: 0px auto;
  font-style: normal;
  font-weight: 500;
  font-size: 0.813rem;
  line-height: 1.25rem;
  align-items: center;
  letter-spacing: -0.01em;
  color: var(--color-tertiary);
  padding: 0px 1rem;
`;

export const SearchButton = styled(HiOutlineSearch)`
  width: 1.25rem;
  height: 1.25rem;
  color: var(--color-brand-primary);
  position: absolute;
  right: 16px;
  top: 50%;
  transform: translateY(-50%);
`;
