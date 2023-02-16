import styled, { keyframes } from "styled-components";
import AppLogo from "../../../assets/img/common/app-logo.svg";
import WebLogo from "../../../assets/img/common/web-logo.svg";
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
  position: fixed;
  top: ${(props) => (props.scrollPosition < 10 ? "0" : "-4.5rem")};
  left: 0;
  width: 100%;
  height: 4.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 1rem 0 0;
  background-color: var(--color-dark);
  z-index: 10;
  transition: ${(props) =>
    props.scrollPosition < 80 ? "0.5s ease-in" : "0.5s ease-in-out"};
`;

export const LogoImg = styled.div`
  width: 207px;
  min-height: 34px;
  background-image: url(${WebLogo});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  @media (max-width: 992px) {
    width: 82px;
    min-height: 48px;
    background-image: url(${AppLogo});
  }
`;

export const HeaderMenu = styled(HiOutlineMenu)`
  display: none;
  color: var(--color-brand-primary);
  @media (max-width: 992px) {
    display: inline;
  }
`;

export const Menu = styled.ul`
  background-color: transparent;
  line-height: 28px;
  @media (max-width: 992px) {
    display: none;
  }
`;

export const MenuItem = styled.li`
  display: ${(props) => (props.isLogin ? "inline-block" : "none")};
`;

export const SLink = styled(Link)`
  font-size: 1.125rem;
  margin: auto 18px;
  font-weight: 500;
  text-decoration: ${(props) =>
    props.current === "true" ? "underline" : "none"};
  text-decoration-thickness: 5px;
  text-decoration-color: var(--color-brand-primary);

  &:last-child {
    margin-right: 0px;
  }

  @media (max-width: 992px) {
    font-size: 1.125rem;
  }
`;

export const NavBar = styled.div`
  position: absolute;
  top: 0px;
  right: 0px;
  width: 48%;
  height: calc(100vh + 72px);
  z-index: 100;
  filter: drop-shadow(-4px 4px 4px rgba(0, 0, 0, 0.25));
  background-color: var(--color-background);
  animation-duration: 1s;
  animation-name: ${slideIn};
`;

export const CancelButton = styled(HiOutlineX)`
  margin: 1rem;
`;

export const SideMenu = styled.ul`
  width: 100%;
  min-width: 180px;
  display: flex;
  flex-direction: column;
`;

export const SideMenuItem = styled.li`
  display: ${(props) => (props.isLogin ? "inline-block" : "none")};
  font-style: normal;
  font-weight: 500;
  line-height: 28px;
  margin: 0.5rem 0.5rem;
`;
