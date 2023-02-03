import React, { useEffect } from "react";
// import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

export default function Oauth() {
  const navigate = useNavigate();
  const [cookies, setCookie] = useCookies(["refreshToken"]);

  useEffect(() => {
    const params = new URL(window.location.href).searchParams;
    const nickName = params.get("nickName");
    const email = params.get("email");
    if (nickName === null) {
      navigate("/signup");
    } else {
      const accessToken = params.get("accessToken");
      const refreshToken = params.get("refreshToken");
      sessionStorage.setItem("accessToken", accessToken);
      setCookie(refreshToken);
      navigate("/");
    }
  });

  return <div>로그인 중입니다.</div>;
}
