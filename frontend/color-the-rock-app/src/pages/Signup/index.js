import React, { useState } from "react";
import * as S from "./style";
import { FiArrowLeft } from "react-icons/fi";
import { FiArrowRightCircle } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { useApi } from "../../api/user";

const SinUp = () => {
  const navigate = useNavigate();
  const [nickName, setNickname] = useState("");
  const [isValidateNickName, setValidateNickName] = useState(false);

  const handleChange = (e) => {
    setNickname(e.target.value);
    // 데이터 입력여부도 확인 필요
    // 여기에 정규표현식 적용해보자.
    // const regex =
  };

  const onClickConfirmButton = () => {
    // 여기서 닉네임 중복체크
    // 다른페이지로 이동
  };

  const onClickHandler = () => {
    navigate("/");
  };

  return (
    <div>
      <S.Container>
        <S.CloseBtnContainer>
          <S.CloseBtn>
            <FiArrowLeft onClick={onClickHandler} size="32px" />
          </S.CloseBtn>
        </S.CloseBtnContainer>
        <S.ContentWrap>
          <S.InputTitle>저는 닉네임</S.InputTitle>

          <S.InputWrapper>
            <S.InputWrap>
              <S.InputComp
                type="text"
                placeholder="사용하실 닉네임을 입력해주세요."
                onChange={handleChange}
              />
            </S.InputWrap>
            으로
          </S.InputWrapper>
          <S.InputButtonWrap>
            <S.InputButton
              onClick={onClickConfirmButton}
              isValidate={isValidateNickName}
            >
              시작할게요
              <FiArrowRightCircle className="FiArrowRightCircle" />
            </S.InputButton>
            <S.ErrorMessageWrap>
              {!isValidateNickName && (
                <S.ErrorMessage>
                  <div>!</div> <p>사용할 수 없는 닉네임</p>
                </S.ErrorMessage>
              )}
            </S.ErrorMessageWrap>
          </S.InputButtonWrap>
        </S.ContentWrap>
      </S.Container>
    </div>
  );
};

export default SinUp;
