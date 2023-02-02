import {defaultInstance} from "./utils/index"

// board(성공 영상 모음) API 작성
export const BoardApi = {

  // 완등 영상 글 상세보기(GET요청)
  getBoardDetail: (id) => defaultInstance.get(`/video/board/detail/${id}`),

  // 완등 영상 글 수정하기(PUT 요청)
  PutBoardDetail: (videoBoardId, title) => defaultInstance.put("/video/board/detail",
  {
    videoBoardId, 
    title,
  }),
  
  // 완등 영상 글 삭제하기(DELETE 요청)
  DeleteBoardDetail: (id) => defaultInstance.delete(`/video/board/detail'/${id}`),

  // 전체 완등 영상 전체 리스트 조회
  getAllVideo: (id) => defaultInstance.get(`/video/baord/${id}`),
  
  // 완등 영상 게시글 올리기  
  postRegisterRecordVideo: (formData) => defaultInstance.post("/video/board", formData, 
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    }),
  
  postRegisterLocalVideo : (formData) => defaultInstance.post("/video/board/local", formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    }
  }),

  // 내 완등 영상 게시글 목록 조회 요청
  getMypostVideo: (storeId) => defaultInstance.get(`/video/board/mypost/${storeId}`),


  // 영상 댓글 조회 요청


}

export default BoardApi;