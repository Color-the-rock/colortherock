import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import UserVideoComponent from "../../components/Live/UserVideo";
import * as S from "./style";
import { useDispatch, useSelector } from "react-redux";
import { setOV } from "../../stores/streaming/streamingSlice";
import CommentBtn from "../../components/Common/CommentBtn";
import ChattingModal from "../../components/Live/ChattingModal";
import FeedbackModal from "../../components/Streaming/FeedbackModal";
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
} from "react-icons/fi";
import { Desktop, Mobile } from "../../components/layout/Template";
import streamingApi from "../../api/streaming";
import RecordVideoFormModal from "../../components/Streaming/RecordVideoFormModal";
import VideoClip from "../../components/Streaming/VideoClip";

const StreamingLive = () => {
  // 기본 설정
  const dispatch = useDispatch();
  const ov = useSelector((state) => state.streaming.ov);
  const roomInfo = useSelector((state) => state.streaming.info);
  const nickName = useSelector((state) => state.users.nickName);
  const [currentVideoDevice, setCurrentVideoDevice] = useState(null);
  const [sessionTitle, setSessionTitle] = useState("");
  const [connectionId, setConnectionId] = useState("");
  const [session, setSession] = useState(undefined);
  const [mainStreamManager, setMainStreamManager] = useState(undefined);
  const [publisher, setPublisher] = useState(undefined);
  const [subscribers, setSubscribers] = useState([]);

  // 세션 종료 관리
  const navigate = useNavigate();

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
  const [recordModal, setRecordModal] = useState(false);

  // 이전 영상 보기 관리
  const [recordListModal, setRecordListModal] = useState(false);
  const [isFrontCamera, setFrontCamera] = useState(false);

  // 피드백 설정 관리
  const [picture, setPicture] = useState([]);
  const [feedbackModal, setFeedbackModal] = useState(false);

  const preventGoBack = () => {
    window.history.pushState(null, "", window.location.href);
    alert("방송종료를 눌러주세요:)");
  };

  const preventClose = (e) => {
    e.preventDefault();
    e.returnValue = ""; // 크롬에서 필요함
  };

  // 새로고침 및 뒤로가기시 처리
  useEffect(() => {
    (() => {
      window.addEventListener("beforeunload", preventClose);
      window.history.pushState(null, "", window.location.href);
      window.addEventListener("popstate", preventGoBack);
    })();

    return () => {
      window.removeEventListener("beforeunload", preventClose);
      window.removeEventListener("popstate", preventGoBack);
    };
  }, []);

  useEffect(() => {
    if (ov !== null && ov !== undefined) {
      setSession(ov.initSession());
    } else {
      navigate("/streaming");
    }
  }, []);

  useEffect(() => {
    if (session !== undefined) {
      onSessionCreated(); // chatting signal
      onFeedbackSignal(); // feedback signal
      onFeedBackReset();
      session
        .connect(token, { clientData: nickName })
        .then(() => console.log("success Connect"))
        .catch((error) => console.log("error: ", error));

      session.on("streamCreated", (event) => {
        const testVideo = document.getElementById("test-video");
        const subscriber = session.subscribe(event.stream, undefined);
        if (testVideo !== null) {
          subscriber.addVideoElement(testVideo);
          testVideo.play();
        }

        setSubscribers((prev) => [subscriber, ...prev]);
      });

      // On every Stream destroyed...
      session.on("streamDestroyed", (event) => {
        deleteSubscriber(event.stream.streamManager);
        leaveSessionPart();
      });

      // On every asynchronous exception...
      session.on("exception", (exception) => {
        console.warn(exception);
      });
    }
  }, [session]);
  useEffect(() => {
    if (token !== "" && session !== undefined) {
      session.connect(token, { clientData: nickName }).then(async () => {
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

        setSessionId(session.sessionId);
        setConnectionId(session.connection.connectionId);

        // Obtain the current video device in use
        let devices = await ov.getDevices();
        let videoDevices = devices.filter(
          (device) => device.kind === "videoinput"
        );

        let currentVideoDeviceId = publisher.stream
          .getMediaStream()
          .getVideoTracks()
          [videoDevices.length - 1].getSettings().deviceId;
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
        if (session) {
          session.disconnect();
        }

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

  const leaveSessionPart = () => {
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
    navigate("/streaming");
  };

  const switchCamera = async () => {
    setShowSettingModal(false);
    let devices = await ov.getDevices();
    // Getting only the video devices
    let videoDevices = devices.filter((device) => device.kind === "videoinput");

    if (videoDevices && videoDevices.length > 1) {
      const testVideo = document.getElementById("test-video");
      let newPublisher = ov.initPublisher(testVideo, {
        videoSource: isFrontCamera
          ? videoDevices[1].deviceId
          : videoDevices[0].deviceId,
        publishAudio: true,
        publishVideo: true,
        mirror: isFrontCamera, // Setting mirror enable if front camera is selected
      });

      // Changing isFrontCamera value
      setFrontCamera((prev) => !prev);
      session.publish(newPublisher);
      setPublisher(newPublisher);
    }
  };

  // video 설정
  const handleSetVideo = () => {
    setShowSettingModal(false);
    if (isRecordStart) {
      alert("녹화를 중지해주세요!");
      return;
    }

    publisher.publishVideo(!isOnVideo);
    setOnVideo((prev) => !prev);
  };

  // audio 설정
  const handleSetAudio = () => {
    setShowSettingModal(false);
    publisher.publishAudio(!isOnMic);
    setOnMic((prev) => !prev);
  };

  // 채팅 관리
  const onSessionCreated = () => {
    session.on(`signal:signal`, (event) => {
      const msg = JSON.parse(event.data).message;
      const userName = JSON.parse(event.from.data).clientData;

      console.log("userName:", nickName, "", event);

      // test
      setMessages((prev) =>
        prev.concat({
          userName: userName,
          text: msg,
        })
      );
    });
  };

  // 피드백 관리
  const onFeedbackSignal = () => {
    session.on(`signal:drawingSignal`, (event) => {
      const data = JSON.parse(event.data);
      setPicture((prev) => [...prev, data]);
    });
  };

  const onFeedBackReset = () => {
    session.on(`signal:reset`, (event) => {
      setPicture([]);
    });
  };

  // 이전 영상 모달 관리
  const openRecordList = () => {
    setRecordListModal((prev) => !prev);
  };

  // 녹화 모달 관리
  const openRecord = () => {
    setRecordModal((prev) => !prev);
  };

  // 피드백 모달 관리
  const openFeedback = () => {
    setFeedbackModal((prev) => !prev);
  };

  const handleStartVideoRecord = () => {
    // 카메라가 꺼져있다면
    if (!isOnVideo) {
      alert("카메라를 켜주세요:)");
      return;
    }

    if (sessionId === null || sessionId === undefined) return;
    const toggleButton = document.getElementById("toggle-record-btn");
    toggleButton.disabled = true;
    const requestBody = {
      connectionId: connectionId,
    };

    streamingApi
      .startRecordVideo(sessionId, requestBody)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          setRecordId(_result);
        }
      })
      .catch((error) => console.log(error));
    setRecordStart((prev) => !prev);
  };

  const handleQuitRecord = () => {
    if (recordId === "") {
      alert("녹화 시작 3초후에 중지 가능합니다:)");
      return;
    }

    const requestBody = {
      token: token,
      recordingId: recordId,
    };

    streamingApi
      .quitRecordVideo(sessionId, requestBody)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          setRecordModal(true);
        }
      })
      .catch((error) => console.log(error));

    setRecordStart((prev) => !prev);
  };

  return (
    <S.Container>
      {feedbackModal && (
        <FeedbackModal
          session={session}
          picture={picture}
          closeFeedback={openFeedback}
        />
      )}
      {recordModal && (
        <RecordVideoFormModal
          sessionId={sessionId}
          recordingId={recordId}
          setModalOpen={openRecord}
        />
      )}
      {recordListModal && (
        <VideoClip sessionId={sessionId} setModalOpen={openRecordList} />
      )}
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
      <Desktop>
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
      </Desktop>
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
            onClick={leaveSessionPart}
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
          {!isShowChattingModal && (
            <S.CommentWrapper>
              <CommentBtn
                isReadOnly={true}
                onClick={() => setShowChattingModal(true)}
              />
            </S.CommentWrapper>
          )}
        </S.SettingWrapper>
      </Mobile>
      {!feedbackModal ? (
        <S.VideoMenu position="bottom">
          <S.VideoMenuItem onClick={openFeedback}>
            <S.IconWrapper>
              <FiEdit size="24px" />
            </S.IconWrapper>
            피드백
          </S.VideoMenuItem>
          <S.VideoMenuItem onClick={openRecordList}>
            <S.IconWrapper>
              <FiFilm size="24px" />
            </S.IconWrapper>
            이전 영상
          </S.VideoMenuItem>
          {mainStreamManager !== undefined && (
            <S.VideoMenuItem
              id="toggle-record-btn"
              onClick={
                !isRecordStart ? handleStartVideoRecord : handleQuitRecord
              }
            >
              <S.IconWrapper>
                <FiDisc size="24px" color={isRecordStart ? "red" : "#ffffff"} />
              </S.IconWrapper>
              {isRecordStart ? "녹화 중지" : "녹화 시작"}
            </S.VideoMenuItem>
          )}
        </S.VideoMenu>
      ) : null}
    </S.Container>
  );
};
export default StreamingLive;
