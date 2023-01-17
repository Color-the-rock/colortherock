import React, { useState } from "react";
import styled, { keyframes } from "styled-components";
import AppLogo from "../../assets/common/app-logo.png";
import WebLogo from "../../assets/common/web-logo.png";
import { HiOutlineMenu, HiOutlineX } from "react-icons/hi";

const Header = () => {
  const [isShowNav, setShowNav] = useState(false);
  const handleSetShowNav = () => {
    setShowNav((prev) => !prev);
  };
  return (
    <Container>
      <LogoImg />
      <Menu>
        <MenuItem>Home</MenuItem>
        <MenuItem>실시간 도전</MenuItem>
        <MenuItem>완등 영상 모음</MenuItem>
        <MenuItem>운동 기록</MenuItem>
        <MenuItem>로그인/회원가입</MenuItem>
      </Menu>
      <HeaderMenu size="36px" onClick={handleSetShowNav} />
      {isShowNav ? (
        <NavBar isShowNav>
          <CancelButton size="36px" onClick={handleSetShowNav} />
          <SideMenu isShowNav>
            <SideMenuItem>Home</SideMenuItem>
            <SideMenuItem>실시간 도전</SideMenuItem>
            <SideMenuItem>완등 영상 모음</SideMenuItem>
            <SideMenuItem>운동 기록</SideMenuItem>
            <SideMenuItem>로그인/회원가입</SideMenuItem>
          </SideMenu>
        </NavBar>
      ) : null}
    </Container>
  );
};
export default Header;

const slideIn = keyframes`
from {
      margin-left: 100%;
      width: 0%;
    }

    to {
      margin-left: 0%;
      width: 48%;
    }
`;

const Container = styled.header`
  position: relative;
  display: flex;
  width: 100%;
  height: 96px;
  background-color: var(--color-dark);
  padding: 16px;
  justify-content: space-between;
  align-items: center;
`;

const LogoImg = styled.div`
  width: 250px;
  min-height: 42px;
  background-image: url(${WebLogo});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  @media (max-width: 1070px) {
    width: 96px;
    min-height: 54px;
    background-image: url(${AppLogo});
  }
`;

const HeaderMenu = styled(HiOutlineMenu)`
  display: none;
  color: var(--color-brand-primary);
  @media (max-width: 1070px) {
    display: inline;
  }
`;

const Menu = styled.ul`
  background-color: transparent;
  line-height: 28px;
  height: 28px;
  @media (max-width: 1070px) {
    display: none;
  }
`;

const MenuItem = styled.li`
  display: inline-block;
  font-size: 1.5rem;
  margin: auto 12px;
  font-weight: 700;
  &:last-child {
    margin-right: 0px;
  }
`;

const NavBar = styled.div`
  position: absolute;
  top: 0px;
  right: 0px;
  width: 48%;
  height: 100vh;
  background-color: var(--color-background);
  animation-duration: 1s;
  animation-name: ${slideIn};
`;

const CancelButton = styled(HiOutlineX)`
  margin: 16px;
`;

const SideMenu = styled.ul`
  width: 100%;
  min-width: 180px;
`;

const SideMenuItem = styled.li`
  font-size: 18px;
  font-style: normal;
  font-weight: 700;
  line-height: 28px;
  margin: 16px;
`;
