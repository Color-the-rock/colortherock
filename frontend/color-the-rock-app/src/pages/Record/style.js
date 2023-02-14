import styled from "styled-components";
import { Link } from "react-router-dom";
import { HiOutlinePlusSm } from "react-icons/hi";
import { MdInfo } from "react-icons/md";
export const Container = styled.div`
  width: 100%;
  height: 100vh;
  background-color: transparent;
  padding: 0 16px;
  margin-top: 5rem;
  margin-bottom: 1rem;
  padding-bottom: 2rem;
  overflow-x: hidden;
  overflow-y: scroll;
  -ms-overflow-style: none;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const Title = styled.h1`
  font-size: 1.5rem;
  font-weight: 700;
  line-height: 2.125rem;
  letter-spacing: -0.01em;
  padding-bottom: 0.4rem;
  border-bottom: 1px solid var(--color-border);
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

export const TextWrapper = styled.div`
  margin: 32px 0px;
`;

export const Text = styled.p`
  width: 100%;
  text-align: center;
  font-style: normal;
  font-weight: 500;
  font-size: 1.125rem;
  line-height: 1.75rem;
  letter-spacing: -0.02em;
`;

export const GradientText = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.75rem;
  letter-spacing: -0.02em;
  background: linear-gradient(
    135deg,
    var(--color-brand-gradient-start) 0%,
    var(--color-brand-gradient-end) 100%
  );
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
`;

export const RadioLabel = styled.label`
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.625rem;
  margin-bottom: 1rem;
  background-color: transparent;
  color: ${(props) =>
    props.checked ? "var(--color-white)" : "var(--color-tertiary)"};

  margin-right: 1rem;
`;
export const RadioButton = styled.input`
  appearance: none;
  margin: 0;
  display: none;
`;

export const RadioGroup = styled.div`
  margin: 0 0 1rem 0;
  background-color: transparent;
  @media (max-width: 992px) {
    margin: 2rem 0 1rem 0;
  }
`;

export const CalendarWrapper = styled.div`
  min-width: 358px;
  max-width: 400px;
  margin: 0 auto;
`;

export const UploadIcon = styled(HiOutlinePlusSm)`
  font-size: 1.125rem;
  line-height: 1.625rem;
  background-color: transparent;
`;

export const UploadButton = styled(Link)`
  font-style: normal;
  font-weight: 700;
  font-size: 1.125rem;
  line-height: 1.625rem;
  text-align: center;
  margin-bottom: 1rem;
  background-color: transparent;
  float: right;
  display: flex;
  align-items: center;
`;

export const InfoWrapper = styled.div`
  position: relative;
  width: 100%;
  z-index: 100;
`;
export const InfoButton = styled(MdInfo)`
  align-self: center;
  margin-left: 0.25rem;
  font-size: 1.3rem;
  color: var(--color-secondary);
`;

export const InfoGuideImg = styled.img`
  position: absolute;
  top: 0px;
  left: 152px;
  width: 200px;
  height: 190px;
  background-color: rgba(0, 0, 0, 0.2);
  border-radius: 20px;
`;

// Desktop CSS
export const ContentWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  max-width: 736px;
  background-color: transparent;
  margin: 0 auto;
`;

export const RecordWrapper = styled.div`
  display: flex;
  justify-content: space-around;
`;
