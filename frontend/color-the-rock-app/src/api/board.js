import { defaultInstance as api } from "./utils/index";

// board(성공 영상 모음) API 작성
const boardApi = {
  // 완등 영상 글 상세보기(GET요청) - 현주
  getBoardDetail: (videoBoardId) =>
    api.get(`/video/board/detail?videoBoardId=${videoBoardId}`),

  // 완등 영상 글 수정하기(PUT 요청)
  putBoardDetail: (requestBody) => api.put("/video/board/detail", requestBody),

  // 완등 영상 글 삭제하기(DELETE 요청) - 현주
  deleteBoardDetail: (videoBoardId) =>
    api.delete(`/video/board/detail?videoBoardId=${videoBoardId}`),

  // 전체 완등 영상 전체 리스트 조회 - 현주
  getAllVideo: ({ storeId = -1, color, gymName }) =>
    api.get(
      `/video/board?storeId=${storeId}&color=${color}&gymName=${gymName}`
    ),

  // 완등 영상 게시글 올리기(내 운동기록 동영상에서 영상 가져오기)
  postRegisterRecordVideo: ({ videoId, title }) =>
    api.post("/video/board", {
      videoId,
      title,
    }),

  // 완등 영상 게시글 올리기(로컬 파일에서 영상 가져오기)
  postRegisterLocalVideo: (formData) =>
    api.post("/video/board/local", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }),

  // 영상 댓글 조회 요청 - 현주
  getVideoBoardCommentList: (storeId = -1, videoBoardId) =>
    api.get(
      `/videoboard/comment?storeId=${storeId}&videoBoardId=${videoBoardId}`
    ),
  // 영상 댓글 수정 요청
  putVideoBoardComment: (requestBody) =>
    api.put("/videoboard/comment", requestBody),

  // 영상 댓글 작성 요청
  postVideoBoardComment: (requestBody) =>
    api.post("/videoboard/comment", requestBody),

  // 영상 댓글 삭제 요청
  deleteVideoBoardComment: (commentId) =>
    api.delete(`videoboard/comment?commentId=${commentId}`),

  // 게시글 신고 API
  reportVideoBoard: (requestBody) =>
    api.post(`/video/board/detail/report`, requestBody),
};

export default boardApi;
