package org.anotherclass.colortherock.domain.videocomment.service;

import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoCommentServiceTest {

    @Autowired
    private VideoCommentService videoCommentService;

    @Nested
    @DisplayName("getCommentList 메소드는")
    class GetCommentList {
        @Nested
        @DisplayName("storeId가 null일 경우")
        class No_Score_Id {
            @Test
            @DisplayName("해당 videoBoardId에 있는 댓글 리스트를 최신순으로 페이지 사이즈만큼 반환")
            void getCommentList() {
                CommentListRequest request = new CommentListRequest();
                request.setStoreId(null);
                request.setVideoBoardId(0L);
                Pageable pageable = Pageable.ofSize(2);

                List<CommentListResponse> commentList = videoCommentService.getCommentList(request, pageable);

                assertTrue(commentList.get(0).getCommentId() > commentList.get(1).getCommentId());
                assertEquals(commentList.size(), 2);
            }
        }


    }

    @Test
    void insertComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void getMyCommentList() {
    }
}