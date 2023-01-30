import React, { useEffect } from 'react';
// import axios from 'axios';
import { useNavigate } from 'react-router-dom';


export default function Oauth() {
  const navigate = useNavigate();
  
  useEffect(() => {
    const code = new URL(window.location.href).searchParams.get('code');
    console.log("code: ", code);
    navigate('/signup');

  })
  // const parseHash = new URLSearchParams(window.location.hash.substring(1));
  // const accessToken = parseHash.get("access_token");
  // console.log("parseHash: ", parseHash);
  // console.log("url: ", new URL(window.location.href));
 

  // const getToken = async () => {
  //   try {
  //     console.log("요청보냄")
      // const refreshToken = sessionStorage.getItem("refreshToken");
      // const res = await axios.get(`_____`, {
      //   headers: {'refreshToken': refreshToken },
      // });
  //     const accessToken = res.headers.authorization.accessToken;
  //     const refleshToken = res.headers.authorization.refleshToken;
  //     const expiredTime = res.data.curtime;
  //     window.sessionStorage.setItem('AccessToken', accessToken);
  //     window.sesisonStorate.setItem('refleshToken', refleshToken);
  //     window.sessionStorage.setItem('expiredTime', expiredTime);
  //          
  //
              // nickname을 받아오는 요청을 보내고,
              // nickname이 없으면 nickname 설정 페이지로 이동하고,
              // nickname이 있으면 Intro 페이지로 이동하는 코드를 작성하는 부분.
  //
  //            
  //     navigate('/');
  //   } catch (e) {
  //     console.error(e);
  //     navigate('/');
  //   }
  // }

  // useEffect(() => {
  //   getToken();
  // }, []);


  return (
    <div>
      로그인 중입니다.
    </div>
  )
}
