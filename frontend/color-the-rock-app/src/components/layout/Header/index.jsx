import React, { useState } from "react";
import { Mobile, Desktop } from "../Template";
import * as S from "./style";
const menuItems = [
  {
    name: "실시간 도전",
    path: "/streaming",
  },
  {
    name: "완등 영상 모음",
    path: "/board",
  },
  {
    name: "운동 기록",
    path: "/record",
  },
  {
    name: "로그인/회원가입",
    path: "/login",
  },
];

const Header = () => {
  const [isShowNav, setShowNav] = useState(false);
  const handleSetShowNav = () => {
    setShowNav((prev) => !prev);
  };
  return (
    <S.Container>
      <S.LogoImg />
      <Desktop>
        <nav>
          <S.Menu>
            {menuItems &&
              menuItems.length > 0 &&
              menuItems.map((item, index) => (
                <S.MenuItem key={index}>
                  <S.SLink to={item.path}>{item.name}</S.SLink>
                </S.MenuItem>
              ))}
          </S.Menu>
        </nav>
      </Desktop>
      <Mobile>
        <S.HeaderMenu size="36px" onClick={handleSetShowNav} />
        {isShowNav ? (
          <S.NavBar isShowNav>
            <S.CancelButton size="36px" onClick={handleSetShowNav} />
            <S.SideMenu isShowNav>
              <S.SideMenuItem>실시간 도전</S.SideMenuItem>
              <S.SideMenuItem>완등 영상 모음</S.SideMenuItem>
              <S.SideMenuItem>운동 기록</S.SideMenuItem>
              <S.SideMenuItem>로그인/회원가입</S.SideMenuItem>
            </S.SideMenu>
          </S.NavBar>
        ) : null}
      </Mobile>
    </S.Container>
  );
};
export default Header;
