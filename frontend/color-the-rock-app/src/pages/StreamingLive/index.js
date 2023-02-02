import React from "react";
import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
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
} from "react-icons/fi";

const APPLICATION_SERVER_URL = "http://localhost:5000/";

const StreamingLive = () => {
  // 기본 설정
  const ov = useSelector((state) => state.streaming.ov);
  const dispatch = useDispatch();
  const [currentVideoDevice, setCurrentVideoDevice] = useState(null);
  const [sessionTitle, setSessionTitle] = useState("SessionA");
  const [session, setSession] = useState(undefined);
  const [mainStreamManager, setMainStreamManager] = useState(undefined);
  const [publisher, setPublisher] = useState(undefined);
  const [subscribers, setSubscribers] = useState([]);
  const [userNickName, onChangeUserNickName] = useInput("");

  // 세션 종료 관리
  const navigate = useNavigate();

  // 채팅 관리
  const [isShowChattingModal, setShowChattingModal] = useState(false);
  const [messages, setMessages] = useState([]);

  // 비디오 설정 관리
  const [isOnVideo, setOnVideo] = useState(true);
  const [isOnMic, setOnMic] = useState(true);

  useEffect(() => {
    if (ov !== null && ov !== undefined) {
      setSession(ov.initSession());
    }
  }, [ov]);

  useEffect(() => {
    window.addEventListener("beforeunload", onbeforeunload);
  }, []);

  useEffect(() => {
    if (session !== undefined) {
      session.on("streamCreated", (event) => {
        const subscriber = session.subscribe(event.stream, undefined);
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

      // --- 4) Connect to the session with a valid user token ---
      getToken().then((token) => {
        session
          .connect(token, { clientData: userNickName })
          .then(async () => {
            onSessionCreated();

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

            session.publish(publisher);

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

            setCurrentVideoDevice(CurrentVideoDevice);
            setMainStreamManager(publisher);
            setPublisher(publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
    }
  }, [session]);

  const getToken = async () => {
    const sessionTitle = await createSession("SessionA");
    return await createToken(sessionTitle);
  };

  const createSession = async (sessionId) => {
    const response = await axios.post(
      APPLICATION_SERVER_URL + "api/sessions",
      { customSessionId: sessionId },
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    return response.data; // The sessionId
  };

  const createToken = async (sessionId) => {
    const response = await axios.post(
      APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
      {},
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    return response.data; // The token
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
    // --- 7) Leave the session by calling 'disconnect' method over the Session object ---

    if (session) {
      session.disconnect();
    }

    // dispatch ov를 null로 설정
    dispatch(setOV({}));
    setSession(undefined);
    setSubscribers([]);
    setSessionTitle("SessionA");
    setMainStreamManager(undefined);
    setPublisher(undefined);

    // 목록 페이지로 이동
    alert("방송이 종료되었습니다:)");
    navigate("/streaming");
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
          // Creating a new publisher with specific videoSource
          // In mobile devices the default and first camera is the front one
          let newPublisher = ov.initPublisher(undefined, {
            videoSource: newVideoDevice[0].deviceId,
            publishAudio: true,
            publishVideo: true,
            mirror: true,
          });

          //newPublisher.once("accessAllowed", () => {
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

      console.log("[onSessionCreated]: ", event.from);

      // test
      setMessages((prev) =>
        prev.concat({
          userName: event.from.connectionId,
          text: msg,
        })
      );
    });
  };
  return (
    <S.Container>
      {isShowChattingModal && (
        <S.DragModal>
          <S.CommentModalWrap>
            <ChattingModal
              setShowChattingModal={setShowChattingModal}
              getToken={getToken}
              onSessionCreated={onSessionCreated}
              session={session}
              messages={messages}
            />
          </S.CommentModalWrap>
        </S.DragModal>
      )}

      <S.OwnerVideoWrapper>
        <S.VideoMenu>
          <S.VideoMenuItem onClick={leaveSession}>
            <FiLogOut size="16px" />
          </S.VideoMenuItem>
          <S.VideoMenuItem onClick={handleSetVideo}>
            {isOnVideo ? <FiVideo size="16px" /> : <FiVideoOff size="16px" />}
          </S.VideoMenuItem>
          <S.VideoMenuItem onClick={handleSetAudio}>
            {isOnMic ? <FiMic size="16px" /> : <FiMicOff size="16px" />}
          </S.VideoMenuItem>
          <S.VideoMenuItem onClick={switchCamera}>
            <FiRefreshCcw size="16px" />
          </S.VideoMenuItem>
        </S.VideoMenu>
        {session !== undefined ? (
          mainStreamManager !== undefined ? (
            <UserVideoComponent streamManager={mainStreamManager} />
          ) : null
        ) : null}
      </S.OwnerVideoWrapper>
      <S.SettingWrapper>
        <S.CommentWrapper>
          <CommentBtn
            isReadOnly={true}
            onClick={() => setShowChattingModal(true)}
          />
        </S.CommentWrapper>
        <S.SettingMenuItem />
        <S.SettingMenuItem />
        <S.SettingMenuItem />
      </S.SettingWrapper>
    </S.Container>
  );
};
export default StreamingLive;
