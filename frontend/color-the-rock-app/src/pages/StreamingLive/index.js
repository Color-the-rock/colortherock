import React from "react";
import OpenViduLive from "../../components/Live/OpenViduLive";
import { OpenVidu, Session } from "openvidu-browser";
import axios from "axios";
import { useState, useEffect } from "react";
import Chatting from "../../components/Live/Chatting";
import UserVideoComponent from "../../components/Live/UserVideo";
import * as S from "./style";
const APPLICATION_SERVER_URL = "http://localhost:5000/";

const StreamingLive = () => {
  // 기본 설정
  const [OV, setOV] = useState(null);
  const [currentVideoDevice, setCurrentVideoDevice] = useState(null);
  const [sessionTitle, setSessionTitle] = useState("SessionA");
  const [session, setSession] = useState(undefined);
  const [mainStreamManager, setMainStreamManager] = useState(undefined);
  const [publisher, setPublisher] = useState(undefined);
  const [subscribers, setSubscribers] = useState([]);

  // 채팅 관련 설정
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState([]);
  const [isShowChatting, setShowChatting] = useState(false);

  // test
  useEffect(() => {
    console.log("[Streaming] messages: ", messages);
  }, [messages]);

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
          .connect(token, { clientData: "싸피" })
          .then(async () => {
            onSessionCreated();

            // --- 5) Get your own camera stream ---
            let publisher = await OV.initPublisherAsync(undefined, {
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
            let devices = await OV.getDevices();
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

  const handleChangeSessionTitle = (e) => {
    setSessionTitle(e.target.value);
  };

  const handleMainVideoStream = (stream) => {
    if (mainStreamManager !== stream) {
      setMainStreamManager(stream);
    }
  };

  const deleteSubscriber = (streamManager) => {
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      const res = subscribers.splice(index, 1);
      setSubscribers(res);
    }
  };

  const joinSession = (e) => {
    e.preventDefault();
    const ov = new OpenVidu();
    console.log(ov);
    setOV(ov);
    console.log("OV: ", ov);

    setSession(ov.initSession());
  };

  const leaveSession = () => {
    // --- 7) Leave the session by calling 'disconnect' method over the Session object ---

    if (session) {
      session.disconnect();
    }

    // Empty all properties...
    // this.OV = null;
    setOV(null);
    setSession(undefined);
    setSubscribers([]);
    setSessionTitle("SessionA");
    setMainStreamManager(undefined);
    setPublisher(undefined);
  };

  const switchCamera = async () => {
    try {
      const devices = await OV.getDevices();
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
          let newPublisher = OV.initPublisher(undefined, {
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

  // 채팅 관련 함수 설정
  const onSessionCreated = () => {
    console.log("onSessionCreated!");
    session.on(`signal:signal`, (event) => {
      const msg = JSON.parse(event.data).message;
      setMessages([
        ...messages,
        {
          userName: event.from.connectionId,
          text: msg,
        },
      ]);
    });
  };

  const sendMessage = () => {
    if (session !== undefined && message !== undefined) {
      const signalOptions = {
        data: JSON.stringify({ message: message }),
        type: "signal",
        to: [], // everyone
      };
      session.signal(signalOptions);
    }
  };

  return (
    <div className="container">
      <S.Button onClick={() => setShowChatting((prev) => !prev)}>
        채팅하기
      </S.Button>

      {session === undefined ? (
        <div id="join">
          <div id="img-div">
            <img
              src="resources/images/openvidu_grey_bg_transp_cropped.png"
              alt="OpenVidu logo"
            />
          </div>
          <div id="join-dialog" className="jumbotron vertical-center">
            <h1> Join a video session </h1>
            <form className="form-group" onSubmit={joinSession}>
              <p>
                <label>Participant: </label>
                <input
                  className="form-control"
                  type="text"
                  id="userName"
                  defaultValue="서비스 내 사용자 이름"
                  required
                />
              </p>
              <p>
                <label> Session: </label>
                <input
                  className="form-control"
                  type="text"
                  id="sessionId"
                  value={sessionTitle}
                  onChange={handleChangeSessionTitle}
                  required
                />
              </p>
              <p className="text-center">
                <input
                  className="btn btn-lg btn-success"
                  name="commit"
                  type="submit"
                  value="JOIN"
                />
              </p>
            </form>
          </div>
        </div>
      ) : null}

      {session !== undefined ? (
        <div id="session">
          <div id="session-header">
            <h1 id="session-title">{sessionTitle}</h1>
            <input
              className="btn btn-large btn-danger"
              type="button"
              id="buttonLeaveSession"
              onClick={leaveSession}
              value="Leave session"
            />
          </div>

          {mainStreamManager !== undefined ? (
            <div id="main-video" className="col-md-6">
              <UserVideoComponent streamManager={mainStreamManager} />
              <input
                className="btn btn-large btn-success"
                type="button"
                id="buttonSwitchCamera"
                onClick={switchCamera}
                value="Switch Camera"
              />
            </div>
          ) : null}
        </div>
      ) : null}

      {/* chat */}
      {isShowChatting !== undefined && isShowChatting && (
        <>
          <Chatting messages={messages} />
          <ov-videoconference
            tokens={getToken}
            toolbarDisplaySessionName={false}
          >
            <S.InputWrapper id="my-panel">
              <S.Input
                type="text"
                placeholder="입력하세요!"
                value={message}
                onChange={(e) => {
                  setMessage(e.target.value);
                }}
              />
              <S.Button onClick={sendMessage}>Send</S.Button>
            </S.InputWrapper>
          </ov-videoconference>
        </>
      )}
    </div>
  );
};
export default StreamingLive;
