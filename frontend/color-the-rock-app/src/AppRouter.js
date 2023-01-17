import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Intro from "./pages/Intro";
import Login from "./pages/Login";
import Streaming from "./pages/Streaming";
import Board from "./pages/Board";
const AppRouter = () => {
  // 로그인 여부에 따라 router 설정
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Intro />} />
        <Route path="/login" element={<Login />} />
        <Route path="/streaming" element={<Streaming />} />
        <Route path="/board" element={<Board />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
