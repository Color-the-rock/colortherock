import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { Mobile, Desktop } from "../Template";
import * as S from "./style";
import { useNavigate } from "react-router-dom";

let menuItems = [
  {
    id: 1,
    name: "실시간 도전",
    path: "/streaming",
  },
  {
    id: 2,
    name: "완등 영상",
    path: "/board",
  },
  {
    id: 3,
    name: "운동 기록",
    path: "/record",
  },
  {
    id: 4,
    name: "로그인",
    path: "/login",
  },
];

const Header = () => {
  const [isShowNav, setShowNav] = useState(false);
  const [scrollPosition, setScrollPosition] = useState(0);
  const location = useLocation();
  const navigate = useNavigate();

  const updateScrollPosition = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };
  useEffect(() => {
    window.addEventListener("scroll", updateScrollPosition);

    // test
    if (sessionStorage.getItem("accessToken") !== null) {
      menuItems = menuItems.filter((item) => {
        if (item.id === 4) {
          item.name = "마이페이지";
          item.path = "/mypage";
        }
        return item;
      });
    }

    if (
      location.pathname === "/record" &&
      sessionStorage.getItem("accessToken") === null
    ) {
      alert("로그인이 필요한 서비스입니다:)");
      navigate("/login");
    }
  });

  const handleSetShowNav = () => {
    setShowNav((prev) => !prev);
  };
  return (
    <S.Container scrollPosition={scrollPosition}>
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
        <S.HeaderMenu size="32px" onClick={handleSetShowNav} />
        {isShowNav ? (
          <S.NavBar isShowNav>
            <S.CancelButton size="28px" onClick={handleSetShowNav} />
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
