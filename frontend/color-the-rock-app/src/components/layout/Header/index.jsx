import React, { useState } from "react";

import * as S from "./style";
const Header = () => {
  const [isShowNav, setShowNav] = useState(false);
  const handleSetShowNav = () => {
    setShowNav((prev) => !prev);
  };
  return (
    <S.Container>
      <S.LogoImg />
      <S.HeaderMenu size="36px" onClick={handleSetShowNav} />
      <S.Menu>
        <S.MenuItem>Home</S.MenuItem>
        <S.MenuItem>실시간 도전</S.MenuItem>
        <S.MenuItem>완등 영상 모음</S.MenuItem>
        <S.MenuItem>운동 기록</S.MenuItem>
        <S.MenuItem>로그인/회원가입</S.MenuItem>
      </S.Menu>
      <S.Menu size="36px" onClick={handleSetShowNav} />
      {isShowNav ? (
        <S.NavBar isShowNav>
          <S.CancelButton size="36px" onClick={handleSetShowNav} />
          <S.SideMenu isShowNav>
            <S.SideMenuItem>Home</S.SideMenuItem>
            <S.SideMenuItem>실시간 도전</S.SideMenuItem>
            <S.SideMenuItem>완등 영상 모음</S.SideMenuItem>
            <S.SideMenuItem>운동 기록</S.SideMenuItem>
            <S.SideMenuItem>로그인/회원가입</S.SideMenuItem>
          </S.SideMenu>
        </S.NavBar>
      ) : null}
    </S.Container>
  );
};
export default Header;
