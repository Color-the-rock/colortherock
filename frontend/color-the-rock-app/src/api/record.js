// record(운동기록) API 직성
import { defaultInstance as api } from "./utils/index";

export const recordApi = {
  // 날짜별 운동 기록 색상 별 조회

  // 로컬 영상 개인 기록 업로드 API
  uploadLocalVideo: () => api.post(`/record/video`),

  // 날짜별 성공 및 실패 영상 목록 API
  getALlRecordVideo: () => api.get(`/record/videos`),

  // 방문한 암장 통계 API
  getVisitedGymData: () => api.get(`/record/visit`, {}),
  // 영상 재생을 위한 영상 상세 정보 조회
  getRecordVideo: (id) => api.get(`/record/video/${id}`),

  // 전체 운동 기록 누적 통계 조회 API
  getTotalStatistics: () => api.get(`/record/total`),

  // 전체 운동 영상 색상별 통계 조회 API
  getColorStatistics: () => api.get(`/record/color`),

  // 캘린더 운동 영상 색상 조회 API
  getColorStatisticsByCalendar: (date) => api.get(`/record/color/${date}`),

  // 영상 삭제 API
  deleteRecordVideo: (videoId) => api.delete(`/record/vodep/${videoId}`),
};
