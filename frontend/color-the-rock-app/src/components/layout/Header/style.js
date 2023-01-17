import styled, { keyframes } from "styled-components";
import AppLogo from "../../../assets/common/app-logo.png";
import WebLogo from "../../../assets/common/web-logo.png";
import { HiOutlineMenu, HiOutlineX } from "react-icons/hi";
import { Link } from "react-router-dom";
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

export const Container = styled.header`
  position: relative;
  display: flex;
  width: 100%;
  height: 6rem;
  background-color: var(--color-dark);
  padding: 1rem 2rem;
  justify-content: space-between;
  align-items: center;
`;

export const LogoImg = styled.div`
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

export const HeaderMenu = styled(HiOutlineMenu)`
  display: none;
  color: var(--color-brand-primary);
  @media (max-width: 1070px) {
    display: inline;
  }
`;

export const Menu = styled.ul`
  background-color: transparent;
  line-height: 28px;
  @media (max-width: 1070px) {
    display: none;
  }
`;

export const MenuItem = styled.li`
  display: inline-block;
`;

export const SLink = styled(Link)`
  font-size: 1.4rem;
  margin: auto 18px;
  font-weight: 700;
  &:last-child {
    margin-right: 0px;
  }
`;

export const NavBar = styled.div`
  position: absolute;
  top: 0px;
  right: 0px;
  width: 48%;
  height: 100vh;
  background-color: var(--color-background);
  animation-duration: 1s;
  animation-name: ${slideIn};
`;

export const CancelButton = styled(HiOutlineX)`
  margin: 16px;
`;

export const SideMenu = styled.ul`
  width: 100%;
  min-width: 180px;
`;

export const SideMenuItem = styled.li`
  font-size: 18px;
  font-style: normal;
  font-weight: 700;
  line-height: 28px;
  margin: 16px;
`;
