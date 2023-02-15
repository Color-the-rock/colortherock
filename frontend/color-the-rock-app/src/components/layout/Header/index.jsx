import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { Mobile, Desktop } from "../Template";
import * as S from "./style";
import { useSelector } from "react-redux";
import { logOut } from "../../../stores/users/userSlice";
import { useDispatch } from "react-redux";
const Header = () => {
  const dispatch = useDispatch();
  const isLogin = useSelector((state) => state.users.isLogin);
  const [isShowNav, setShowNav] = useState(false);
  const [scrollPosition, setScrollPosition] = useState(0);
  const location = useLocation();

  const updateScrollPosition = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };

  const handleSetShowNav = () => {
    setShowNav((prev) => !prev);
  };

  useEffect(() => {
    if (scrollPosition > 72 && isShowNav) {
      setShowNav(false);
    }
  }, [scrollPosition, isShowNav]);

  useEffect(() => {
    window.addEventListener("scroll", updateScrollPosition);
    if (sessionStorage.getItem("accessToken") === null) {
      dispatch(logOut());
    }
  }, []);

  return (
    <S.Container scrollPosition={scrollPosition}>
      <S.SLink to="/">
        <S.LogoImg />
      </S.SLink>
      <Desktop>
        <nav>
          <S.Menu>
            <S.MenuItem isLogin={true}>
              <S.SLink
                to="/streaming"
                current={"/streaming" === location.pathname ? "true" : "false"}
              >
                실시간 라이브
              </S.SLink>
            </S.MenuItem>
            <S.MenuItem isLogin={true}>
              <S.SLink
                to="/board"
                current={"/board" === location.pathname ? "true" : "false"}
              >
                완등 영상
              </S.SLink>
            </S.MenuItem>
            <S.MenuItem isLogin={isLogin}>
              <S.SLink
                to="/record"
                current={"/record" === location.pathname ? "true" : "false"}
              >
                운동 기록
              </S.SLink>
            </S.MenuItem>
            <S.MenuItem isLogin={!isLogin ? true : false}>
              <S.SLink
                to="/login"
                current={"/login" === location.pathname ? "true" : "false"}
              >
                로그인
              </S.SLink>
            </S.MenuItem>
            <S.MenuItem isLogin={isLogin}>
              <S.SLink
                to="/mypage"
                current={"/mypage" === location.pathname ? "true" : "false"}
              >
                마이페이지
              </S.SLink>
            </S.MenuItem>
          </S.Menu>
        </nav>
      </Desktop>
      <Mobile>
        <S.HeaderMenu size="32px" onClick={handleSetShowNav} />
        {isShowNav ? (
          <S.NavBar isShowNav>
            <S.CancelButton size="28px" onClick={handleSetShowNav} />
            <S.SideMenu isShowNav>
              <S.SideMenuItem isLogin={true} onClick={handleSetShowNav}>
                <S.SLink
                  to="/streaming"
                  current={
                    "/streaming" === location.pathname ? "true" : "false"
                  }
                >
                  실시간 라이브
                </S.SLink>
              </S.SideMenuItem>
              <S.SideMenuItem isLogin={true} onClick={handleSetShowNav}>
                <S.SLink
                  to="/board"
                  current={"/board" === location.pathname ? "true" : "false"}
                >
                  완등 영상
                </S.SLink>
              </S.SideMenuItem>
              <S.SideMenuItem isLogin={isLogin} onClick={handleSetShowNav}>
                <S.SLink
                  to="/record"
                  current={"/record" === location.pathname ? "true" : "false"}
                >
                  운동 기록
                </S.SLink>
              </S.SideMenuItem>
              <S.SideMenuItem
                isLogin={!isLogin ? true : false}
                onClick={handleSetShowNav}
              >
                <S.SLink
                  to="/login"
                  current={"/login" === location.pathname ? "true" : "false"}
                >
                  로그인
                </S.SLink>
              </S.SideMenuItem>
              <S.SideMenuItem isLogin={isLogin} onClick={handleSetShowNav}>
                <S.SLink
                  to="/mypage"
                  current={"/mypage" === location.pathname ? "true" : "false"}
                >
                  마이페이지
                </S.SLink>
              </S.SideMenuItem>
            </S.SideMenu>
          </S.NavBar>
        ) : null}
      </Mobile>
    </S.Container>
  );
};
export default Header;
