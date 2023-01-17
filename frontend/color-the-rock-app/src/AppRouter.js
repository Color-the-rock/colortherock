import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Intro from "./pages/Intro";
import Login from "./pages/Login";
import Streaming from "./pages/Streaming";
import Board from "./pages/Board";
import Record from "./pages/Record";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
const AppRouter = () => {
  // 로그인 여부에 따라 router 설정
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Intro />} />
        <Route path="/login" element={<Login />} />
        <Route path="/streaming" element={<Streaming />} />
        <Route path="/board" element={<Board />} />
        <Route path="/record" element={<Record />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default AppRouter;
