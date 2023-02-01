import {defaultInstance} from "./utils/index"

// board(성공 영상 모음) API 작성
export const requests = {

  // 완등 영상 글 상세보기(GET요청)
  GetBoardDetail: (id) => defaultInstance.get(`/video/board/detail/${id}`),

  // 완등 영상 글 수정하기(PUT 요청)
  // PutBoardDetail: (id) => defaultInstance.put("");
  
  // 완등 영상 글 삭제하기(DELETE 요청)
  // DeleteBoardDetail:
  

  // 전체 완등 영상 전체 리스트 조회
  GetAllVideo: (id) => defaultInstance.get(`/video/baord${id}`),
  
  // 완등 영상 게시글 올리기  
  PostResgisterVideo: (formData) => defaultInstance.post("/video/board", formData, {
    headers: {}
  }),

  // 내 완등 영상 게시글 목록 조회 API
  GetMypostVideo: (storeId) => defaultInstance.get(`/video/board/mypost/${storeId}`),

}

export default requests;