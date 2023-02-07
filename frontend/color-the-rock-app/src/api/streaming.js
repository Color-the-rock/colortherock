// streaming(실시간 스트리밍) API 작성

import { defaultInstance as api } from "./utils";

const streamingApi = {
  // 라이브 방 생성
  createLiveSession: (liveObject) => api.post(`/live`, liveObject),

  // 라이브 참여
  participateLiveSession: (liveId) => api.get(`/live/${liveId}`),
};

export default streamingApi;
