import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import { setPendingLogin, setLogin } from "../../stores/users/userSlice";

export default function Oauth() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [cookies, setCookie] = useCookies(["refreshToken"]);

  useEffect(() => {
    console.log("Oauth Page");
    const params = new URL(window.location.href).searchParams;
    const nickname = params.get("nickname");
    const email = params.get("email");
    const registrationId = params.get("registrationId");

    if (nickname === null) {
      dispatch(setPendingLogin({ email, registrationId }));
      navigate("/signup");
    } else {
      dispatch(setLogin({ nickname, email, registrationId }));
      console.log(params.get("access"));
      console.log(params.get("refresh"));
      const accessToken = params.get("access");
      const refreshToken = params.get("refresh");
      sessionStorage.setItem("accessToken", accessToken);
      setCookie("refreshToken", refreshToken);
      navigate("/");
    }
  }, []);

  return <div>로그인 중입니다.</div>;
}
