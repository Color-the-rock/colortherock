package org.anotherclass.colortherock.domain.videocomment.service;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.exception.CommentNotFoundException;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.CommentUpdateRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.response.MyCommentListResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class VideoCommentServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    private VideoBoardRepository videoBoardRepository;
    @Autowired
    private VideoCommentRepository videoCommentRepository;
    @Autowired
    private VideoCommentService videoCommentService;
    ArrayList<Long> memberIds;
    ArrayList<Long> videoIds;
    ArrayList<Long> videoBoardIds;
    ArrayList<Long> videoCommentIds;

    @BeforeEach
    public void setVideoCommentData() {
        memberIds = new ArrayList<>();
        videoIds = new ArrayList<>();
        videoBoardIds = new ArrayList<>();
        videoCommentIds = new ArrayList<>();
        // Member 생성
        Member memberA = new Member("yeji@rock.com", "yezi", Member.RegistrationId.kakao);
        Member memberB = new Member("johan@rock.com", "johan", Member.RegistrationId.google);
        Member saveA = memberRepository.save(memberA);
        Member saveB = memberRepository.save(memberB);
        memberIds.add(saveA.getId());
        memberIds.add(saveB.getId());

        // Video, VideoBoard, VideoComment 생성
        for (int i = 1; i <= 4; i++) {
            Video video = new Video(LocalDate.parse("2023-01-30"), 4, "더클라임 강남점", "s3url", true, "thumbnail","name", "초록", saveA, "videoName");
            Video saveVideo = videoRepository.save(video);
            VideoBoard videoBoard = new VideoBoard("성공했습니다", false, saveVideo, memberA);
            VideoBoard saveVideoBoard = videoBoardRepository.save(videoBoard);
            videoBoardIds.add(saveVideoBoard.getId());
            for (int j = 1; j <= 4; j++) {
                VideoComment comment = new VideoComment("멋있어요!", saveB, saveVideoBoard);
                VideoComment saveComment = videoCommentRepository.save(comment);
                videoCommentIds.add(saveComment.getId());
            }
        }
    }


    @Nested
    @DisplayName("댓글 가져오기 메소드는")
    class GetCommentList {
        @Nested
        @DisplayName("storeId가 -1일 경우")
        class No_Score_Id {
            @Test
            @DisplayName("해당 videoBoardId에 있는 댓글 리스트를 최신순으로 페이지 사이즈만큼 반환")
            void getCommentList() {
                CommentListRequest request = new CommentListRequest();
                request.setStoreId(-1L);
                request.setVideoBoardId(videoBoardIds.get(0));

                List<CommentListResponse> commentList = videoCommentService.getCommentList(request);

                assertTrue(commentList.get(0).getCommentId() > commentList.get(1).getCommentId());
                assertEquals(4, commentList.size());
            }
        }
    }

    @Nested
    @DisplayName("댓글 삽입 메소드는")
    class InsertComment {
        @Nested
        @DisplayName("매개변수가 유효하지 않을 경우")
        class Exception_Occurs {
            @Test
            @DisplayName("유효하지 않은 멤버는 No Such User 예외 발생")
            void noSuchUserException() {
                Long memberId = 2000000L; // DB에 없는 유저
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(videoBoardIds.get(0));
                request.setContent("멋있어요!");
                assertThrows(GlobalBaseException.class, () -> {
                    videoCommentService.insertComment(memberId, request);
                });
            }

            @Test
            @DisplayName("영상 게시글을 찾을 수 없으면 No Such Post 예외 발생")
            void noSuchPostException() {
                Long memberId = memberIds.get(0);
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(2000000L); // DB에 없는 id
                request.setContent("멋있어요!");
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
                Long memberId = memberIds.get(1);
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(videoBoardIds.get(0));
                request.setContent("멋있어요!");

                Long commentId = videoCommentService.insertComment(memberId, request);
                VideoComment videoComment = videoCommentRepository.findById(commentId).get();

                assertEquals(videoComment.getContent(), request.getContent());

            }
        }

    }

    @Nested
    @DisplayName("댓글 수정 메소드는")
    class UpdateComment {
        @Nested
        @DisplayName("매개변수가 유효하지 않을 경우")
        class Exception_Occurs {
            @Test
            @DisplayName("commentId가 잘못되었으면 Commment Not Found 예외 발생")
            void commentNotFoundException() {
                Long memberId = memberIds.get(0);
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(100000000L); // DB에 없는 commentId
                request.setContent("수정했어요");

                assertThrows(CommentNotFoundException.class, () -> {
                    videoCommentService.updateComment(memberId, request);
                });
            }

            @Test
            @DisplayName("comment작성자와 유저가 일치하지 않으면 Writer Mismatch 예외 발생")
            void writerMismatchException() {
                Long memberId = memberIds.get(0);
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(videoCommentIds.get(0));
                request.setContent("수정했어요");

                assertThrows(GlobalBaseException.class, () -> {
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
                Long memberId = memberIds.get(1);
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(videoCommentIds.get(0));
                request.setContent("수정했어요");

                videoCommentService.updateComment(memberId, request);
                VideoComment comment = videoCommentRepository.findById(request.getCommentId()).get();

                assertEquals(request.getContent(), comment.getContent());
            }
        }
    }

    @Nested
    @DisplayName("댓글 삭제 메소드는")
    class DeleteComment {
        @Nested
        @DisplayName("유효한 commentId, 유효한 권한을 가졌을 경우")
        class All_Exception_Pass {

            @Test
            @DisplayName("해당 댓글 삭제")
            void deleteComment() {
                Long memberId = memberIds.get(1);
                Long commentId = videoCommentIds.get(0);

                videoCommentService.deleteComment(memberId, commentId);
                assertThrows(CommentNotFoundException.class, () -> {
                    videoCommentService.deleteComment(memberId, commentId);
                });
            }
        }
    }

    @Nested
    @DisplayName("내 댓글 리스트 가져오기 메소드는")
    class GetMyCommentList {
        @Nested
        @DisplayName("storeId가 -1일 경우")
        class Store_Id_Null {
            @Test
            @DisplayName("해당 멤버의 댓글 리스트를 id 값이 큰 순서대로 페이지 사이즈만큼 반환")
            void getMyCommentList() {
                Long memberId = memberIds.get(1);
                Long storeId = -1L;

                List<MyCommentListResponse> result = videoCommentService.getMyCommentList(memberId, storeId);

                assertTrue(result.get(0).getCommentId() > result.get(1).getCommentId());
                assertEquals(15, result.size());
            }
        }
    }
}