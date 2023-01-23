package org.anotherclass.colortherock.domain.videocomment.service;

import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.exception.CommentNotFoundException;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.CommentUpdateRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.response.MyCommentListResponse;
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
        @DisplayName("매개변수가 유효하지 않을 경우")
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

    @Nested
    @DisplayName("updateComment 메소드는")
    class UpdateComment {
        @Nested
        @DisplayName("매개변수가 유효하지 않을 경우")
        class Exception_Occurs {
            @Test
            @DisplayName("commentId가 잘못되었으면 Commment Not Found 예외 발생")
            void commentNotFoundException() {
                Long memberId = 0L;
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(10L); // DB에 없는 commentId
                request.setContent("수정했어요");
                request.setWrittenTime(LocalDateTime.of(2023,1,23,23,25));

                assertThrows(CommentNotFoundException.class, () ->{
                    videoCommentService.updateComment(memberId, request);
                });
            }

            @Test
            @DisplayName("comment작성자와 유저가 일치하지 않으면 Writer Mismatch 예외 발생")
            void writerMismatchException() {
                Long memberId = 1L; // 작성자는 0L
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(7L);
                request.setContent("수정했어요");
                request.setWrittenTime(LocalDateTime.of(2023,1,23,23,25));

                assertThrows(GlobalBaseException.class, () ->{
                    videoCommentService.updateComment(memberId, request);
                });
            }
        }

        @Nested
        @DisplayName("유효한 commentId, 유효한 수정권한을 가졌을 경우")
        class All_Exception_Pass {
            @Test
            @DisplayName("댓글 수정 성공")
            void updateComment() {
                Long memberId = 0L;
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(7L);
                request.setContent("수정했어요");
                request.setWrittenTime(LocalDateTime.of(2023,1,23,23,25));

                videoCommentService.updateComment(memberId, request);
                VideoComment comment = videoCommentRepository.findById(request.getCommentId()).get();

                assertEquals(request.getContent(), comment.getContent());
            }
        }
    }

    @Nested
    @DisplayName("deleteComment 메소드는")
    class DeleteComment {
        @Nested
        @DisplayName("유효한 commentId, 유효한 권한을 가졌을 경우")
        class All_Exception_Pass {

            @Test
            @DisplayName("해당 댓글 삭제")
            void deleteComment() {
                Long memberId = 0L;
                Long commentId = 7L;

                videoCommentService.deleteComment(memberId, commentId);
                assertThrows(CommentNotFoundException.class, () ->{
                    videoCommentService.deleteComment(memberId, commentId);
                });
            }
        }
    }
    @Nested
    @DisplayName("getMyCommentList 메소드는")
    class GetMyCommentList {
        @Nested
        @DisplayName("storeId가 null일 경우")
        class Store_Id_Null {
            @Test
            @DisplayName("해당 멤버의 댓글 리스트를 id 값이 큰 순서대로 페이지 사이즈만큼 반환")
            void getMyCommentList() {
                Long memberId = 0L;
                Long storeId = null;
                Pageable pageable = Pageable.ofSize(2);

                List<MyCommentListResponse> result = videoCommentService.getMyCommentList(memberId, storeId, pageable);

                assertTrue(result.get(0).getCommentId() > result.get(1).getCommentId());
                assertEquals(result.size(), 2);
            }
        }
    }
}