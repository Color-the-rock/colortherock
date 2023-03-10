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
    void setVideoCommentData() {
        memberIds = new ArrayList<>();
        videoIds = new ArrayList<>();
        videoBoardIds = new ArrayList<>();
        videoCommentIds = new ArrayList<>();
        // Member ??????
        Member memberA = new Member("yeji@rock.com", "yezi", Member.RegistrationId.kakao);
        Member memberB = new Member("johan@rock.com", "johan", Member.RegistrationId.google);
        Member saveA = memberRepository.save(memberA);
        Member saveB = memberRepository.save(memberB);
        memberIds.add(saveA.getId());
        memberIds.add(saveB.getId());

        // Video, VideoBoard, VideoComment ??????
        for (int i = 1; i <= 4; i++) {
            Video video = new Video(LocalDate.parse("2023-01-30"), 4, "???????????? ?????????", "s3url", true, "thumbnail", "name", "??????", saveA, "videoName", false);
            Video saveVideo = videoRepository.save(video);
            VideoBoard videoBoard = new VideoBoard("??????????????????", false, saveVideo, memberA);
            VideoBoard saveVideoBoard = videoBoardRepository.save(videoBoard);
            saveVideoBoard.getVideo().videoPosted();
            videoBoardIds.add(saveVideoBoard.getId());
            for (int j = 1; j <= 4; j++) {
                VideoComment comment = new VideoComment("????????????!", saveB, saveVideoBoard);
                VideoComment saveComment = videoCommentRepository.save(comment);
                videoCommentIds.add(saveComment.getId());
            }
        }
    }


    @Nested
    @DisplayName("?????? ???????????? ????????????")
    class GetCommentList {
        @Nested
        @DisplayName("storeId??? -1??? ??????")
        class No_Score_Id {
            @Test
            @DisplayName("?????? videoBoardId??? ?????? ?????? ???????????? ??????????????? ????????? ??????????????? ??????")
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
    @DisplayName("?????? ?????? ????????????")
    class InsertComment {
        @Nested
        @DisplayName("??????????????? ???????????? ?????? ??????")
        class Exception_Occurs {
            @Test
            @DisplayName("???????????? ?????? ????????? No Such User ?????? ??????")
            void noSuchUserException() {
                Long memberId = 2000000L; // DB??? ?????? ??????
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(videoBoardIds.get(0));
                request.setContent("????????????!");
                assertThrows(GlobalBaseException.class, () -> videoCommentService.insertComment(memberId, request));

            }

            @Test
            @DisplayName("?????? ???????????? ?????? ??? ????????? No Such Post ?????? ??????")
            void noSuchPostException() {
                Long memberId = memberIds.get(0);
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(2000000L); // DB??? ?????? id
                request.setContent("????????????!");
                assertThrows(PostNotFoundException.class, () -> videoCommentService.insertComment(memberId, request));
            }
        }

        @Nested
        @DisplayName("????????? ??????, ??????????????? id??? ??????")
        class All_Exception_Pass {
            @Test
            @DisplayName("?????? ?????? ??????")
            void insertComment() {
                Long memberId = memberIds.get(1);
                NewCommentRequest request = new NewCommentRequest();
                request.setVideoBoardId(videoBoardIds.get(0));
                request.setContent("????????????!");

                Long commentId = videoCommentService.insertComment(memberId, request);
                VideoComment videoComment = videoCommentRepository.findById(commentId).get();

                assertEquals(videoComment.getContent(), request.getContent());

            }
        }

    }

    @Nested
    @DisplayName("?????? ?????? ????????????")
    class UpdateComment {
        @Nested
        @DisplayName("??????????????? ???????????? ?????? ??????")
        class Exception_Occurs {
            @Test
            @DisplayName("commentId??? ?????????????????? Commment Not Found ?????? ??????")
            void commentNotFoundException() {
                Long memberId = memberIds.get(0);
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(100000000L); // DB??? ?????? commentId
                request.setContent("???????????????");

                assertThrows(CommentNotFoundException.class, () -> videoCommentService.updateComment(memberId, request));
            }

            @Test
            @DisplayName("comment???????????? ????????? ???????????? ????????? Writer Mismatch ?????? ??????")
            void writerMismatchException() {
                Long memberId = memberIds.get(0);
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(videoCommentIds.get(0));
                request.setContent("???????????????");

                assertThrows(GlobalBaseException.class, () -> videoCommentService.updateComment(memberId, request));
            }
        }

        @Nested
        @DisplayName("????????? commentId, ????????? ??????????????? ????????? ??????")
        class All_Exception_Pass {
            @Test
            @DisplayName("?????? ?????? ??????")
            void updateComment() {
                Long memberId = memberIds.get(1);
                CommentUpdateRequest request = new CommentUpdateRequest();
                request.setCommentId(videoCommentIds.get(0));
                request.setContent("???????????????");

                videoCommentService.updateComment(memberId, request);
                VideoComment comment = videoCommentRepository.findById(request.getCommentId()).orElseThrow();

                assertEquals(request.getContent(), comment.getContent());
            }
        }
    }

    @Nested
    @DisplayName("?????? ?????? ????????????")
    class DeleteComment {
        @Nested
        @DisplayName("????????? commentId, ????????? ????????? ????????? ??????")
        class All_Exception_Pass {

            @Test
            @DisplayName("?????? ?????? ??????")
            void deleteComment() {
                Long memberId = memberIds.get(1);
                Long commentId = videoCommentIds.get(0);

                videoCommentService.deleteComment(memberId, commentId);
                assertThrows(CommentNotFoundException.class, () -> videoCommentService.deleteComment(memberId, commentId));

            }
        }
    }

    @Nested
    @DisplayName("??? ?????? ????????? ???????????? ????????????")
    class GetMyCommentList {
        @Nested
        @DisplayName("storeId??? -1??? ??????")
        class Store_Id_Null {
            @Test
            @DisplayName("?????? ????????? ?????? ???????????? id ?????? ??? ???????????? ????????? ??????????????? ??????")
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