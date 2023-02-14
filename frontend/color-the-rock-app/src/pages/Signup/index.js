import React, { useState, useEffect } from "react";
import * as S from "./style";
import { FiArrowLeft } from "react-icons/fi";
import { FiArrowRightCircle } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import UserApi from "../../api/user";
import { setfulfilledLogin } from "../../stores/users/userSlice";
import { useSelector, useDispatch } from "react-redux";
import { useCookies } from "react-cookie";

const SignUp = () => {
  const users = useSelector((state) => state.users);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [nickname, setNickname] = useState("");
  const [nickNameValid, setNickNameValid] = useState(false);
  const [oK, setOK] = useState(false);
  const [cookies, setCookie] = useCookies(["refreshToken"]);

  useEffect(() => {
    if (oK) {
      const data = {
        email: users.email,
        registrationId: users.registrationId,
        nickname: nickname,
      };

      UserApi.SignUp(data)
        .then((res) => {
          alert("회원가입에 성공했습니다:)");
          dispatch(setfulfilledLogin({ nickname }));
          const accessToken = res.data.result.accessToken;
          sessionStorage.setItem("accessToken", accessToken);
          const refreshToken = res.data.result.refreshToken;
          setCookie("refreshToken", refreshToken);
          navigate("/");
        })
        .catch((err) => {
          alert("로그인 실패");
          console.log("err: ", err);
          navigate("/");
        });
    }
  }, [oK]);

  // 닉네임 정규표현식 적용.
  const handleChange = (e) => {
    setNickname(e.target.value);
    const regex = /^(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9가-힣]{2,16}$/;
    if (regex.test(e.target.value)) {
      setNickNameValid(true);
    } else {
      setNickNameValid(false);
    }
  };

  // 중복확인을 하고, 중복확인되면
  const onClickConfirmButton = () => {
    UserApi.CheckNickname(nickname)
      .then((res) => {
        if (res.data.result === true) setOK(true);
        else setOK(false);
      })
      .catch((err) => {
        setOK(false);
        alert("중복된 닉네임입니다.");
      });
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
                placeholder="닉네임을 입력해주세요."
                onChange={handleChange}
              />
            </S.InputWrap>
            으로
          </S.InputWrapper>
          <S.InputButtonWrap>
            <S.InputButton
              disabled={!nickNameValid}
              onClick={onClickConfirmButton}
              isValidate={nickNameValid}
            >
              시작할게요
              <FiArrowRightCircle className="FiArrowRightCircle" />
            </S.InputButton>
            <S.ErrorMessageWrap>
              {!nickNameValid && nickname.length > 0 && (
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

export default SignUp;
