// streaming(실시간 스트리밍) API 작성
import { defaultInstance as api } from "./utils/index";
const streamingApi = {
  //   전체 라이브 목록 호출 API
  //   liveId : 라이브 목록의 마지막 라이브의 id를 전달
  getStreamingList: (liveId) => api.get(`/api/live/list?liveId=${liveId}`),

  // 라이브 생성 API
  // liveRequest : { isPublic, title, gymName }
  createLiveSession: (liveRequest) => api.post(`/api/live`, liveRequest),

  // 라이브 입장 API
  enterLiveSession: (sessionId) => api.get(`/api/live/${sessionId}`),

  // 녹화 시작 API
  startRecordVideo: (sessionId) =>
    api.post(`/api/live/${sessionId}/recording/start`),

  // 녹화 종료 API
  endRecordVideo: (sessionId) =>
    api.post(`/api/live/${sessionId}/recording/stop`),

  // 녹화 저장 API
  saveRecordVideo: (sessionId) =>
    api.post(`/api/live/${sessionId}/recording/save`),
};

export default streamingApi;
