import { defaultInstance } from "./utils/index";

// board(성공 영상 모음) API 작성
const boardApi = {
  // 완등 영상 글 상세보기(GET요청) - 현주
  getBoardDetail: (videoBoardId) =>
    defaultInstance.get(`/video/board/detail?videoBoardId=${videoBoardId}`),

  // 완등 영상 글 수정하기(PUT 요청)
  putBoardDetail: (requestBody) =>
    defaultInstance.put("/video/board/detail", requestBody),

  // 완등 영상 글 삭제하기(DELETE 요청) - 현주
  deleteBoardDetail: (videoBoardId) =>
    defaultInstance.delete(`/video/board/detail?videoBoardId=${videoBoardId}`),

  // 전체 완등 영상 전체 리스트 조회 - 현주
  getAllVideo: ({ storeId = -1, color, gymName }) =>
    defaultInstance.get(
      `/video/board?storeId=${storeId}&color=${color}&gymName=${gymName}`
    ),

  // 완등 영상 게시글 올리기(내 운동기록 동영상에서 영상 가져오기)
  postRegisterRecordVideo: ({ videoId, title }) => {
    console.log("데이터 확인용: ", videoId, ", ", title);
    return defaultInstance.post("/video/board", {
      videoId,
      title,
    });
  },
  // 완등 영상 게시글 올리기(로컬 파일에서 영상 가져오기)
  postRegisterLocalVideo: (formData) =>
    defaultInstance.post("/video/board/local", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }),

  // 내 완등 영상 게시글 목록 조회 요청
  getMypostVideo: ({ storeId }) =>
    defaultInstance.get("/video/board/mypost", {
      params: {
        storeId,
      },
    }),

  // 영상 댓글 조회 요청 - 현주
  getVideoBoardCommentList: (storeId = -1, videoBoardId) =>
    defaultInstance.get(
      `/videoboard/comment?storeId=${storeId}&videoBoardId=${videoBoardId}`
    ),
  // 영상 댓글 수정 요청
  putVideoBoardComment: (requestBody) =>
    defaultInstance.put("/videoboard/comment", requestBody),

  // 영상 댓글 작성 요청
  postVideoBoardComment: (requestBody) =>
    defaultInstance.post("/videoboard/comment", requestBody),

  // 영상 댓글 삭제 요청
  deleteVideoBoardComment: (commentId) =>
    defaultInstance.delete(`videoboard/comment?commentId=${commentId}`),

  // 내 영상 댓글 조회 요청
  getVideoBoardmyComment: ({ storeId }) =>
    defaultInstance.get("/videoboard/mycomment", {
      params: {
        storeId,
      },
    }),

  // 게시글 신고 API
  reportVideoBoard: (requestBody) =>
    defaultInstance.post(`/video/board/detail/report`, requestBody),
};

export default boardApi;
