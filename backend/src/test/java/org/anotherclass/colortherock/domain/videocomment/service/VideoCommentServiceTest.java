package org.anotherclass.colortherock.domain.videocomment.service;

import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoCommentServiceTest {

    @Autowired
    private VideoCommentService videoCommentService;
    @Autowired
    private VideoCommentRepository videoCommentRepository;

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

    @Nested
    @DisplayName("insertComment 메소드는")
    class InsertComment {
        @Nested
        @DisplayName("예외가 발생할 경우")
        class Exception_Occurs {
            @Test
            @DisplayName("유효하지 않은 멤버는 No Such User 예외 발생")
            void noSuchUserException() {
                Long memberId = 2L; // DB에 없는 유저
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(0L);
                request.setContent("멋있어요!");
                request.setWrittenTime(LocalDateTime.of(2022, 1, 23, 23, 10));
                assertThrows(GlobalBaseException.class, () -> {
                    videoCommentService.insertComment(memberId, request);
                });
            }

            @Test
            @DisplayName("영상 게시글을 찾을 수 없으면 No Such Post 예외 발생")
            void noSuchPostException() {
                Long memberId = 0L;
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(10L); // DB에 없는 id
                request.setContent("멋있어요!");
                request.setWrittenTime(LocalDateTime.of(2022, 1, 23, 23, 10));
                assertThrows(PostNotFoundException.class, () -> {
                    videoCommentService.insertComment(memberId, request);
                });
            }
        }

        @Nested
        @DisplayName("유효한 멤버, 영상게시글 id일 경우")
        class All_Exception_Pass {
            @Test
            @DisplayName("댓글 삽입 성공")
            void insertComment() {
                Long memberId = 0L;
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(0L); // DB에 없는 id
                request.setContent("멋있어요!");
                request.setWrittenTime(LocalDateTime.of(2022, 1, 23, 23, 10));

                Long commentId = videoCommentService.insertComment(memberId, request);
                VideoComment videoComment = videoCommentRepository.findById(commentId).get();

                assertEquals(videoComment.getContent(), request.getContent());

            }
        }

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