import { defaultInstance as api } from "./utils/index";

export const myPageApi = {
  // 나의 댓글 목록 가져오기
  getMyCommentList: (storeId = 1) =>
    api.get(`/videoboard/mycomment?storeId=${storeId}`),

  // 나의 게시글 목록 가져오기
  getMyBoardList: (storeId = 1) =>
    api.get(`/video/board/mypost?storeId=${storeId}`),
};
