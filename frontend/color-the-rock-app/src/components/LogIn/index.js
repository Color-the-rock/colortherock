import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

export default function Oauth() {
  const navigate = useNavigate();
  const [cookies, setCookie] = useCookies(["refreshToken"]);

  useEffect(() => {
    console.log("여기오나요?");
    const params = new URL(window.location.href).searchParams;
    const params2 = new URL(window.location.href);
    console.log("params :", params);
    console.log("params2 :", params2);
    const nickName = params.get("nickName");
    const email = params.get("email");
    console.log("nickName: ", nickName);
    console.log("email: ", email);
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
