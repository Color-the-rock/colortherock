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
import Board from "./pages/Board";
import BoardDetail from "./pages/Board/BoardDetail";
import Record from "./pages/Record";
import MyPage from "./pages/MyPage";
import Header from "./components/layout/Header";
import Oauth from "./components/LogIn/index";
import Signup from "./pages/Signup/index";
import BoardRegist from "./pages/Board/BoardRegist/index";
import BoardModify from "./pages/Board/BoardModify/index";
import StreamingForm from "./pages/StreamingForm";
import Preview from "./pages/Preview/index";
import RecordForm from "./pages/Record/RecordForm";
import StreamingLive from "./pages/StreamingLive";
import UploadS3Form from "./pages/Board/UploadS3Form";
import ErrorPage from "./pages/Error";
import { useSelector } from "react-redux";
import Admin from "./pages/Admin";

const Layout = () => {
  return (
    <div>
      <Header />
      <Outlet />
    </div>
  );
};

const AppRouter = () => {
  const isLogin = useSelector((state) => state.users.isLogin);
  if (!isLogin) {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Intro />} />
            <Route path="/board" element={<Board />} />
            <Route path="/streaming" element={<Streaming />} />
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/oauth" element={<Oauth />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="*" element={<ErrorPage />} />
        </Routes>
      </Router>
    );
  }
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Intro />} />
          <Route path="/board" element={<Board />} />
          <Route path="/record" element={<Record />} />
          <Route path="/streaming" element={<Streaming />} />
          <Route path="/mypage" element={<MyPage />} />
        </Route>

        <Route path="/login" element={<Login />} />
        <Route path="/oauth" element={<Oauth />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/board/form" element={<BoardRegist />} />
        <Route path="/board/modify/:id" element={<BoardModify />} />
        <Route path="/board/detail/:id" element={<BoardDetail />} />
        <Route path="/streaming" element={<Streaming />} />
        <Route path="/streaming/form" element={<StreamingForm />} />
        <Route path="preview" element={<Preview />} />
        <Route path="/record/form" element={<RecordForm />} />
        <Route path="/streaming/live" element={<StreamingLive />} />
        <Route path="/board/s3form" element={<UploadS3Form />} />
        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
