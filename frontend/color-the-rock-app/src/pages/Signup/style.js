/*
  Singup Style.js
*/

import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  overflow-x: hidden;
`;

export const CloseBtnContainer = styled.div`
  display: flex;
  align-items: center;
  height: 10vh;
`;

export const CloseBtn = styled.div`
  display: flex;
  align-items: center;
  margin-left: 1rem;
  font-weight: 500;
  color: var(--color-secondary);
  font-size: 2rem;
`;

export const ContentWrap = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
  margin: 0px 1rem;

  @media (max-width: 991px) {
    align-items: flex-start;
  }
`;

export const InputTitle = styled.div`
  width: 352px;
  padding-bottom: 0.25rem;
  margin-left: 10px;
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 700;
  font-size: 1.5rem;
  letter-spacing: -0.01em;
`;
// gradation + border가 충동이 나지 않도록 적용하는 법 배우기!!!
export const InputWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  border: 2px solid transparent;
  margin: 10px 0px;
  margin-right: 0.5rem;
  color: var(--color-secondary);
  border-radius: 20px;
  width: 300px;
  height: 40px;
  background-image: linear-gradient(
      var(--color-background),
      var(--color-background)
    ),
    linear-gradient(to right, #ff6cab, #8533ff);
  background-origin: border-box;
  background-clip: content-box, border-box;
`;

export const InputComp = styled.input`
  border: none;
  background-color: var(--color-background);
  color: var(--color-secondary);
  width: 244px;
  margin: 0 10px;
  font-style: normal;
  font-weight: 400;
  font-size: 1rem;
  line-height: 1.5rem;
`;
export const InputButtonWrap = styled.div`
  width: 352px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  border-radius: 20px;
  @media (max-width: 991px) {
    .FiArrowRightCircle {
      font-size: 1.25rem;
      margin: 5px 3px 0.25rem 0.25rem;
      /* margin-top: 0.05px; */
      color: var(--color-level-v1-start);
    }
  }
`;

export const InputButton = styled.button`
  display: flex;
  align-items: center;
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 700;
  font-size: 1.5rem;
  letter-spacing: -0.01em;
  color: ${(props) =>
    props.isValidate === true
      ? "var(--color-white)"
      : "var(--color-level-v1-start)"};

  .p {
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;
export const ErrorMessageWrap = styled.div`
  @media (min-width: 992px) {
  }

  @media (max-width: 991px) {
    display: flex;
    justify-content: center;
    align-items: center;
    .p {
      font-family: "Noto Sans KR";
      font-style: normal;
      font-size: 1.5rem;
      letter-spacing: -0.01em;
      color: #e0e2e7;
    }
  }
`;

export const ErrorMessage = styled.div`
  display: flex;
  align-items: center;
  margin-left: 5px;

  div {
    margin: 0px 5px;
    display: flex;
    width: 0.75rem;
    height: 0.75rem;
    font-weight: 400;
    justify-content: center;
    align-items: center;
    font-size: 0.5rem;
    color: white;
    background-color: var(--color-level-v1-start);
    /* border: 1px solid white; */
    border-radius: 100%;
  }

  p {
    /* small/500 */

    font-family: "Noto Sans KR";
    font-style: normal;
    font-weight: 400;
    font-size: 0.75rem;
    font-size: 1.2rem;
    color: var(--color-level-v1-start);
    /* identical to box height, or 171% */

    display: flex;
    align-items: center;
    letter-spacing: -0.01em;
  }
`;

export const InputWrapper = styled.div`
  display: flex;
  align-items: center;
  font-style: normal;
  font-weight: 700;
  font-size: 1.5rem;
  letter-spacing: -0.01em;
`;
