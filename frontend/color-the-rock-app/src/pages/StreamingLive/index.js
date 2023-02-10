import React from "react";
import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import UserVideoComponent from "../../components/Live/UserVideo";
import * as S from "./style";
import { useInput } from "../../hooks/useInput";
import { useDispatch, useSelector } from "react-redux";
import { setOV } from "../../stores/streaming/streamingSlice";
import CommentBtn from "../../components/Common/CommentBtn";
import ChattingModal from "../../components/Live/ChattingModal";
import {
  FiLogOut,
  FiMic,
  FiMicOff,
  FiRefreshCcw,
  FiVideoOff,
  FiVideo,
  FiEdit,
  FiFilm,
  FiDisc,
  FiLink,
  FiX,
} from "react-icons/fi";
import { Desktop, Mobile } from "../../components/layout/Template";
import streamingApi from "../../api/streaming";

const StreamingLive = () => {
  // 기본 설정
  const ov = useSelector((state) => state.streaming.ov);
  const roomInfo = useSelector((state) => state.streaming.info);
  const nickName = useSelector((state) => state.users.nickName);
  const dispatch = useDispatch();
  const [currentVideoDevice, setCurrentVideoDevice] = useState(null);
  const [sessionTitle, setSessionTitle] = useState("testTitle");
  const [userNickName, setUserNickName] = useState("");
  const [connectionId, setConnectionId] = useState("");
  const [session, setSession] = useState(undefined);
  const [mainStreamManager, setMainStreamManager] = useState(undefined);
  const [publisher, setPublisher] = useState(undefined);
  const [subscribers, setSubscribers] = useState([]);

  // 세션 종료 관리
  const navigate = useNavigate();
  // const { sessionId: _sessionId } = useParams();
  // 채팅 관리
  const [isShowChattingModal, setShowChattingModal] = useState(false);
  const [messages, setMessages] = useState([]);

  // 비디오 설정 관리
  const [isOnVideo, setOnVideo] = useState(true);
  const [isOnMic, setOnMic] = useState(true);
  const [isShowSettingModal, setShowSettingModal] = useState(false);
  const [isRecordStart, setRecordStart] = useState();

  // 라이브 API 연결 설정 관리
  const token = useSelector((state) => state.streaming.userOpenViduToken);
  const [sessionId, setSessionId] = useState("");
  // 녹화 설정 관리
  const [recordId, setRecordId] = useState("");
  let testRecordId = "";

  useEffect(() => {
    if (ov !== null && ov !== undefined) {
      console.log("ov 있음", ov, token);

      setSession(ov.initSession());
    }
  }, []);

  useEffect(() => {
    setUserNickName(nickName);
    console.log("nickName:: ", nickName);
  }, [nickName]);

  useEffect(() => {
    window.addEventListener("beforeunload", onbeforeunload);
  }, []);

  useEffect(() => {
    if (session !== undefined) {
      onSessionCreated();
      console.log("세션 존재, 세션: ", session);
      console.log("오픈비두 객체: ", ov);
      session
        .connect(token, { clientData: userNickName })
        .then(() => console.log("success Connect"))
        .catch((error) => console.log("error: ", error));

      session.on("streamCreated", (event) => {
        const testVideo = document.getElementById("test-video");
        const subscriber = session.subscribe(event.stream, undefined);

        if (testVideo !== null) {
          subscriber.addVideoElement(testVideo);
        }

        setSubscribers((prev) => [subscriber, ...prev]);
      });

      // On every Stream destroyed...
      session.on("streamDestroyed", (event) => {
        deleteSubscriber(event.stream.streamManager);
      });

      // On every asynchronous exception...
      session.on("exception", (exception) => {
        console.warn(exception);
      });
    }
  }, [session]);

  useEffect(() => {
    console.log("useEffect().....", session);
    if (token !== "" && session !== undefined) {
      console.log("??????");
      session.connect(token, { clientData: userNickName }).then(async () => {
        // onSessionCreated();

        // --- 5) Get your own camera stream ---
        let publisher = await ov.initPublisherAsync(undefined, {
          audioSource: undefined, // The source of audio. If undefined default microphone
          videoSource: undefined, // The source of video. If undefined default webcam
          publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
          publishVideo: true, // Whether you want to start publishing with your video enabled or not
          resolution: "640x480", // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
          mirror: false, // Whether to mirror your local video or not
        });

        session.publish(publisher); // publisher는 본인의 화면을 송출

        console.log("session???", session.sessionId);
        setSessionId(session.sessionId);
        setConnectionId(session.connection.connectionId);
        console.log("ov???", ov);

        // Obtain the current video device in use
        let devices = await ov.getDevices();
        let videoDevices = devices.filter(
          (device) => device.kind === "videoinput"
        );
        let currentVideoDeviceId = publisher.stream
          .getMediaStream()
          .getVideoTracks()[0]
          .getSettings().deviceId;
        let CurrentVideoDevice = videoDevices.find(
          (device) => device.deviceId === currentVideoDeviceId
        );

        setSubscribers((prev) => [publisher, ...prev]);
        setCurrentVideoDevice(CurrentVideoDevice);
        setMainStreamManager(publisher);
        setPublisher(publisher);
      });
    }
  }, [token]);

  const getToken = async () => {
    return token;
  };

  const onbeforeunload = (event) => {
    leaveSession();
  };

  const deleteSubscriber = (streamManager) => {
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      const res = subscribers.splice(index, 1);
      setSubscribers(res);
    }
  };

  // 비디오 설정 메뉴 관리
  const leaveSession = () => {
    streamingApi
      .leaveLiveSession(sessionId)
      .then(() => {
        console.log("라이브 종료 성공");
        if (session) {
          session.disconnect();
        }

        // dispatch ov를 null로 설정
        dispatch(setOV({}));
        setSession(undefined);
        setSubscribers([]);
        setSessionTitle("");
        setMainStreamManager(undefined);
        setPublisher(undefined);
        alert("방송이 종료되었습니다:)");
        navigate("/streaming");
      })
      .catch((error) => console.log(error));
  };

  const switchCamera = async () => {
    try {
      const devices = await ov.getDevices();
      let videoDevices = devices.filter(
        (device) => device.kind === "videoinput"
      );

      if (videoDevices && videoDevices.length > 1) {
        let newVideoDevice = videoDevices.filter(
          (device) => device.deviceId !== currentVideoDevice.deviceId
        );

        if (newVideoDevice.length > 0) {
          let newPublisher = ov.initPublisher(undefined, {
            videoSource: newVideoDevice[0].deviceId,
            publishAudio: true,
            publishVideo: true,
            mirror: true,
          });

          await session.unpublish(mainStreamManager);
          await session.publish(newPublisher);

          setCurrentVideoDevice(newVideoDevice[0]);
          setMainStreamManager(newPublisher);
          setPublisher(newPublisher);
        }
      }
    } catch (e) {
      console.error(e);
    }
  };

  // video 설정
  const handleSetVideo = () => {
    if (isRecordStart) {
      alert("녹화를 중지해주세요!");
      return;
    }

    publisher.publishVideo(!isOnVideo);
    setOnVideo((prev) => !prev);
  };

  // audio 설정
  const handleSetAudio = () => {
    publisher.publishAudio(!isOnMic);
    setOnMic((prev) => !prev);
  };

  // 채팅 관리
  const onSessionCreated = () => {
    console.log("onSessionCreated!");
    session.on(`signal:signal`, (event) => {
      const msg = JSON.parse(event.data).message;
      const userName = JSON.parse(event.from.data).clientData;

      // test
      setMessages((prev) =>
        prev.concat({
          userName: userName,
          text: msg,
        })
      );
    });
  };

  const handleStartVideoRecord = () => {
    // 카메라가 꺼져있다면
    if (!isOnVideo) {
      alert("카메라를 켜주세요:)");
      return;
    }

    if (sessionId === null || sessionId === undefined) return;

    const requestBody = {
      connectionId: connectionId,
    };
    streamingApi
      .startRecordVideo(sessionId, requestBody)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[녹화 시작] statusCode : 200 ", _result);
          setRecordId(_result);
          testRecordId = _result;
          console.log("녹화 시작 후 결과 [testRecordId] ", testRecordId);
          console.log("녹화 시작 후 결과 [recordId] ", recordId);
        }
      })
      .catch((error) => console.log(error));
    setRecordStart((prev) => !prev);
  };

  const handleQuitRecord = () => {
    console.log("녹화 종료 전 결과 [testRecordId] ", testRecordId);
    console.log("녹화 종료 전 결과 [recordId] ", recordId);

    console.log("recordId : ", testRecordId);
    const requestBody = {
      token: token,
      recordingId: recordId,
    };

    streamingApi
      .quitRecordVideo(sessionId, requestBody)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[quitRecordVideo] statusCode : 200 ", _result);
        }
      })
      .catch((error) => console.log(error));

    setRecordStart((prev) => !prev);
  };

  const handleCopyLink = () => {
    navigator.clipboard.writeText(window.location.href);
    alert("링크가 복사되었습니다:)");
  };
  return (
    <S.Container>
      <Mobile>
        {isShowChattingModal && (
          <S.DragModal>
            <S.CommentModalWrap>
              <ChattingModal
                setShowChattingModal={setShowChattingModal}
                getToken={getToken}
                onSessionCreated={onSessionCreated}
                session={session}
                messages={messages}
                isShowChattingModal={isShowChattingModal}
              />
            </S.CommentModalWrap>
          </S.DragModal>
        )}
      </Mobile>
      <S.OwnerVideoWrapper>
        <S.StreamTitle>{roomInfo.title}</S.StreamTitle>
        {mainStreamManager !== undefined ? (
          <S.VideoSettingsIcon
            color="#ffffff"
            size="24px"
            onClick={() => setShowSettingModal((prev) => !prev)}
          />
        ) : (
          <S.LeaveSessionButton
            color="#ffffff"
            size="24px"
            onClick={leaveSession}
          />
        )}

        {mainStreamManager !== undefined && isShowSettingModal && (
          <S.VideoSettingsMenu>
            <S.VideoSettingsMenuItem onClick={handleSetVideo}>
              {isOnVideo ? <FiVideo size="16px" /> : <FiVideoOff size="16px" />}
              <S.MenuTitle>카메라</S.MenuTitle>
            </S.VideoSettingsMenuItem>
            <S.VideoSettingsMenuItem onClick={handleSetAudio}>
              {isOnMic ? <FiMic size="16px" /> : <FiMicOff size="16px" />}
              <S.MenuTitle>마이크</S.MenuTitle>
            </S.VideoSettingsMenuItem>
            <S.VideoSettingsMenuItem onClick={switchCamera}>
              <FiRefreshCcw size="16px" />
              <S.MenuTitle>카메라 전환</S.MenuTitle>
            </S.VideoSettingsMenuItem>
            <S.VideoSettingsMenuItem onClick={leaveSession}>
              <FiLogOut size="16px" />
              <S.MenuTitle>방송종료</S.MenuTitle>
            </S.VideoSettingsMenuItem>
          </S.VideoSettingsMenu>
        )}

        {session !== undefined ? (
          mainStreamManager !== undefined ? (
            <UserVideoComponent streamManager={mainStreamManager} />
          ) : (
            <S.VideoTest id="test-video" />
          )
        ) : null}
      </S.OwnerVideoWrapper>
      <Mobile>
        <S.SettingWrapper>
          <S.CommentWrapper>
            <CommentBtn
              isReadOnly={true}
              onClick={() => setShowChattingModal(true)}
            />
          </S.CommentWrapper>
        </S.SettingWrapper>
        <S.VideoMenu position="bottom">
          {mainStreamManager !== undefined && (
            <S.VideoMenuItem>
              <S.IconWrapper>
                <FiEdit size="24px" />
              </S.IconWrapper>
              피드백
            </S.VideoMenuItem>
          )}

          <S.VideoMenuItem onClick={handleSetVideo}>
            <S.IconWrapper>
              <FiFilm size="24px" />
            </S.IconWrapper>
            이전 영상
          </S.VideoMenuItem>
          {mainStreamManager !== undefined && (
            <S.VideoMenuItem
              onClick={
                !isRecordStart ? handleStartVideoRecord : handleQuitRecord
              }
            >
              <S.IconWrapper>
                <FiDisc size="24px" color={isRecordStart ? "red" : "#ffffff"} />
              </S.IconWrapper>
              녹화 시작
            </S.VideoMenuItem>
          )}

          <S.VideoMenuItem onClick={handleCopyLink}>
            <S.IconWrapper>
              <FiLink size="24px" />
            </S.IconWrapper>
            링크 공유
          </S.VideoMenuItem>
        </S.VideoMenu>
      </Mobile>
    </S.Container>
  );
};
export default StreamingLive;
