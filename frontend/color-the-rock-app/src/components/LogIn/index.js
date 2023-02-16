import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import { setPendingLogin, setLogin } from "../../stores/users/userSlice";

const Oauth = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [cookies, setCookie] = useCookies(["refreshToken"]);

  useEffect(() => {
    const params = new URL(window.location.href).searchParams;
    const nickname = decodeURIComponent(params.get("nickname"));
    const email = params.get("email");
    const registrationId = params.get("registrationId");

    if (nickname === "null") {
      dispatch(setPendingLogin({ email, registrationId }));
      navigate("/signup");
    } else {
      dispatch(setLogin({ nickname, email, registrationId }));
      const accessToken = params.get("access");
      const refreshToken = params.get("refresh");
      sessionStorage.setItem("accessToken", accessToken);
      setCookie("refreshToken", refreshToken);
      navigate("/");
    }
  }, []);

  return <div>로그인 중입니다.</div>;
};

export default Oauth;
