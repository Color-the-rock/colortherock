// streaming(실시간 스트리밍) API 작성

import { defaultInstance as api } from "./utils";

const streamingApi = {
  // 라이브 방 생성 API
  createLiveSession: (liveObject) =>
    api.post(`/live`, liveObject, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }),

  // 라이브 참여 API
  participateLiveSession: (liveId) => api.get(`/live/${liveId}`),

  // 라이브 목록 조회 API
  getAllLiveList: (liveId = -1) => api.get(`/live/list?liveId=${liveId}`),

  // 라이브 기록 시작 API
  startRecordVideo: (sessionId, token) => {
    console.log(
      "[startRecordVideo] sessionId > ",
      sessionId,
      " token ? ",
      token
    );
    return api.post(`/live/${sessionId}/recording/start`, token);
  },

  // 라이브 기록 종료 API
  quitRecordVideo: (sessionId, recordObject) => {
    console.log(
      "[quitRecordVideo] sessionId > ",
      sessionId,
      " recordObject ? ",
      recordObject
    );
    return api.post(`/live/${sessionId}/recording/end`, recordObject);
  },

  // 라이브 기록 저장 API
  saveRecordVideo: (sessionId) => api.post(`/live/${sessionId}/recording/save`),
};

export default streamingApi;
