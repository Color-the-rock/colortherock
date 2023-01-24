import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Outlet,
} from "react-router-dom";
import Intro from "./pages/Intro";
import Login from "./pages/Login";
import Streaming from "./pages/Streaming";
import BoardList from "./pages/Board/BoardList";
import Record from "./pages/Record";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Oauth from "./components/LogIn/index";
const Layout = () => {
  return (
    <div>
      <Header />

      <Outlet />
    </div>
  );
};

const AppRouter = () => {
  // 로그인 여부에 따라 router 설정
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Intro />} />
          <Route path="/board" element={<BoardList />} />
          <Route path="/record" element={<Record />} />
          <Route path="/streaming" element={<Streaming />} />
        </Route>

        <Route path="/login" element={<Login />} />
        <Route path="/oauth" element={<Oauth />} />
        <Route path="/streaming/:streamingId" element={<Streaming />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
