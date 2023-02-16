import React from "react";
import * as S from "./style";
import { adminApi } from "../../../api/admin";
import { useInput } from "../../../hooks/useInput";
import PropTypes from "prop-types";

const AdminLogin = ({ setAdmin }) => {
  const [adminId, onChangeAdminId] = useInput("");
  const [adminPw, onChangeAdminPw] = useInput("");

  // 관리자 로그인
  const handleAdminLogin = () => {
    if (!adminId || !adminPw) {
      alert("아이디와 비밀번호를 입력해주세요!");
      return;
    }

    const admin = {
      id: adminId,
      password: adminPw,
    };
    adminApi
      .loginAdmin(admin)
      .then(({ data }) => {
        sessionStorage.setItem("adminToken", data.result);
        setAdmin(true);
      })
      .catch(() => console.log("관리자 로그인 실패"));
  };
  return (
    <>
      <S.LoginInput
        type="text"
        placeholder="ID"
        value={adminId}
        onChange={onChangeAdminId}
      />
      <S.LoginInput
        type="password"
        placeholder="Password"
        value={adminPw}
        onChange={onChangeAdminPw}
      />
      <S.LoginButton onClick={handleAdminLogin}>로그인</S.LoginButton>
    </>
  );
};
export default AdminLogin;

AdminLogin.propTypes = {
  setAdmin: PropTypes.func,
};
