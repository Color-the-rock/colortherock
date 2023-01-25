import React, { useState } from 'react'
import * as S from "./style";
import { Mobile, Desktop } from "../../components/layout/Template";
import  { FiArrowLeft } from "react-icons/fi";
import { FiArrowRightCircle } from "react-icons/fi";
import { useNavigate } from 'react-router-dom';


export default function SinUp() {
  const navigate = useNavigate();
  const [nickName, setNickname] = useState("");
  const [nickNameValid, setNickNameValid] = useState(false);
  
  const handleChange = (e) => {
    setNickname(e.target.value);
    // 여기에 정규표현식 적용해보자.
    // const regex = 
  }

  const onClickConfirmButton = () => {
    // 여기서 닉네임 중복체크
    // 다른페이지로 이동
  };

  const onClickHandler = () => {
    navigate("/");
  }

  return (
    <div>
      <Desktop>

      </Desktop>
      
      <Mobile>
      <S.Container>
        <S.CloseBtnContainer>
          <S.CloseBtn>
            <FiArrowLeft
              onClick={onClickHandler}/>
          </S.CloseBtn>
        </S.CloseBtnContainer>
        <S.ContentWrap>
          <S.InputTitle>저는 닉네임</S.InputTitle>
          <S.InputWrap>
            <S.InputComp
              type="text"
              placeholder="사용하실 닉네임을 입력해주세요."
              onChange={handleChange}
            />
          </S.InputWrap>
          <S.InputButttonWrap>
            <S.InputButton onClick={onClickConfirmButton}>
              시작할게요
                <FiArrowRightCircle className='FiArrowRightCircle'/>
            </S.InputButton>
            <S.ErrorMessageWrap>
              {
                !nickNameValid && nickName.length > 0 && (
                  <S.ErrorMessage>
                    <div>!</div> <p>잘못된 형식의 닉네임</p>
                  </S.ErrorMessage>
                )
              }
            </S.ErrorMessageWrap>
          </S.InputButttonWrap>
        </S.ContentWrap>
        </S.Container>
      </Mobile>
    </div>
  )
}
