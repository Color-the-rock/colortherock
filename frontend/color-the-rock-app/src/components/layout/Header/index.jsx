import React, { useState } from "react";
import { useLocation } from "react-router-dom";
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
  const location = useLocation();
  const handleSetShowNav = () => {
    setShowNav((prev) => !prev);
  };
  return (
    <S.Container>
      <S.SLink to="/">
        <S.LogoImg />
      </S.SLink>
      <Desktop>
        <nav>
          <S.Menu>
            {menuItems &&
              menuItems.length > 0 &&
              menuItems.map((item, index) => (
                <S.MenuItem key={index}>
                  <S.SLink
                    to={item.path}
                    current={item.path === location.pathname ? "true" : "false"}
                  >
                    {item.name}
                  </S.SLink>
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
              {menuItems &&
                menuItems.length > 0 &&
                menuItems.map((item, index) => (
                  <S.SideMenuItem key={index}>
                    <S.SLink
                      to={item.path}
                      onClick={handleSetShowNav}
                      current={
                        item.path === location.pathname ? "true" : "false"
                      }
                    >
                      {item.name}
                    </S.SLink>
                  </S.SideMenuItem>
                ))}
            </S.SideMenu>
          </S.NavBar>
        ) : null}
      </Mobile>
    </S.Container>
  );
};
export default Header;
