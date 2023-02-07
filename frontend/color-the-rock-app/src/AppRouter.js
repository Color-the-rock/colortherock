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
import Preview from "./pages/Preview/inedx";
import RecordForm from "./pages/Record/RecordForm";
import StreamingLive from "./pages/StreamingLive";
import UploadS3Form from "./pages/Board/UploadS3Form";
import ErrorPage from "./pages/Error";

const Layout = () => {
  return (
    <div>
      <Header />
      <Outlet />
    </div>
  );
};

const AppRouter = () => {
  console.log("로그인 상태", sessionStorage.getItem("accessToken"));
  if (sessionStorage.getItem("accessToken") === null) {
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
          {/* <Route path="*" element={<ErrorPage />} /> */}
        </Routes>
        {/* <Footer /> */}
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
        <Route path="/signup" element={<Signup />} />
        <Route path="/board/form" element={<BoardRegist />} />
        <Route path="/board/modify/:id" element={<BoardModify />} />
        <Route path="/board/detail/:id" element={<BoardDetail />} />
        <Route path="/oauth" element={<Oauth />} />
        <Route path="/streaming/:streamingId" element={<Streaming />} />
        <Route path="/streaming/form" element={<StreamingForm />} />
        <Route path="preview" element={<Preview />} />
        <Route path="/record/form" element={<RecordForm />} />
        <Route path="/streaming/live/:sessionId" element={<StreamingLive />} />
        <Route path="/board/s3form" element={<UploadS3Form />} />
        <Route path="*" element={<ErrorPage />} />
      </Routes>
      {/* <Footer /> */}
    </Router>
  );
};

export default AppRouter;
