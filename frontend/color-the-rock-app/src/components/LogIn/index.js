import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import { setPendingLogin, setLogin } from "../../stores/users/userSlice";

export default function Oauth() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [cookies, setCookie] = useCookies(["refreshToken"]);

  //  refresh , access , email , registraionId, nickname.
  // email, registraionId
  useEffect(() => {
    console.log("Oauth Page");
    const params = new URL(window.location.href).searchParams;

    console.log("params :", params);

    const nickName = params.get("nickName");
    const email = params.get("email");
    const registraionId = params.get("registraionId");

    if (nickName === null) {
      //로그인 상태관리
      dispatch(setPendingLogin({ email, registraionId }));
      navigate("/signup");
    } else {
      // 로그인 상태관리
      dispatch(setLogin({ nickName, email, registraionId }));

      const accessToken = params.get("accessToken");
      const refreshToken = params.get("refreshToken");
      sessionStorage.setItem("accessToken", accessToken);
      setCookie(refreshToken);
      navigate("/");
    }
  }, []);

  return <div>로그인 중입니다.</div>;
}
