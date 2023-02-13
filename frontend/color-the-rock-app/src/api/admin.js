import axios from "axios";
const BASE_URL = "https://colortherock.com/api";

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

const adminToken = sessionStorage.getItem("adminToken");

export const adminApi = {
  // 관리자 - 로그인 API
  loginAdmin: (admin) => api.post(`/login/admin`, admin),
  // 관리자 - 숨김처리된 영상 게시글 보기 API
  getHiddenVideoBoardList: () =>
    api.get(`/admin/list`, {
      headers: {
        Authorization: `${adminToken}`,
      },
    }),

  // 관리자 - 신고 내용 확인하기 API
  getReportDetail: (videoBoardId) =>
    api.get(`/admin/list/detail?videoBoardId=${videoBoardId}`),

  // 관리자 - 영상 삭제 API
  deleteVideoBoard: (videoBoardId) =>
    api.delete(`/admin/list/detail?videoBoardId=${videoBoardId}`),

  // 관리자 - 영상 숨김 해제 API
  toggleHiddenVideoBoard: () => api.get(`/admin/list/detail/unhidden`),
};
