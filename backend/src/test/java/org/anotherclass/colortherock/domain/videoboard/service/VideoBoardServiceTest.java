package org.anotherclass.colortherock.domain.videoboard.service;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.exception.VideoUserMismatchException;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.exception.WriterMismatchException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessPostUpdateRequest;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessVideoUploadRequest;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardDetailResponse;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class VideoBoardServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoBoardService videoBoardService;
    @Autowired
    private VideoBoardRepository videoBoardRepository;
    private ArrayList<Long> memberIds;
    private ArrayList<Long> videoIds;
    private ArrayList<Long> videoBoardIds;
    private final Integer PAGE_SIZE = 16;
    private final Integer MYPAGE_SIZE = 8;

    @BeforeEach
    public void setMemberAndVideo() {
        memberIds = new ArrayList<>();
        videoIds = new ArrayList<>();
        videoBoardIds = new ArrayList<>();
        // Member 생성
        Member memberA = new Member("yeji@rock.com", "yezi", Member.RegistrationId.kakao);
        Member memberB = new Member("johan@rock.com", "johan", Member.RegistrationId.google);
        Member saveA = memberRepository.save(memberA);
        Member saveB = memberRepository.save(memberB);
        memberIds.add(saveA.getId());
        memberIds.add(saveB.getId());

        // Video, VideoBoard 생성
        for(int i = 1; i <= 32; i++){
            Video video = new Video(LocalDate.parse("2023-01-29"), 4, "더클라임 강남점", "s3url", true, "thumbnail", "name","초록", saveA, "videoName", false);
            Video saveVideo = videoRepository.save(video);
            videoIds.add(saveVideo.getId());
            if(i % 2 == 0) {
                VideoBoard videoBoard = VideoBoard.builder()
                        .title("성공했습니다.")
                        .member(memberA)
                        .video(video)
                        .isHidden(false).build();
                VideoBoard saveVideoBoard = videoBoardRepository.save(videoBoard);
                videoBoard.getVideo().videoPosted();
                videoBoardIds.add(saveVideoBoard.getId());
            }
        }
        for(int i = 33; i <= 64; i++){
            Video video = new Video(LocalDate.parse("2023-01-29"),5, "더클라임 홍대점", "s3url", true, "thumbnail", "name","파랑", saveB, "videoName", false);
            Video saveVideo = videoRepository.save(video);
            videoIds.add(saveVideo.getId());
            if(i % 2 == 0){
                VideoBoard videoBoard = VideoBoard.builder()
                        .video(video)
                        .member(memberB)
                        .title("완등했습니다.")
                        .isHidden(false).build();
                VideoBoard saveVideoBoard = videoBoardRepository.save(videoBoard);
                videoBoard.getVideo().videoPosted();
                videoBoardIds.add(saveVideoBoard.getId());
            }
        }
    }

    @Nested
    @DisplayName("성공 영상 리스트 불러오기 메소드는")
    class getSuccessVideos {

        @Nested
        @DisplayName("검색 조건(색상/암장)이 없고")
        class No_Search_Input_Data {

            @Nested
            @DisplayName("storeId가 null일 경우")
            class No_Store_ID {
                @Test
                @DisplayName("ID가 큰 순서대로 지정된 사이즈의 게시글 리스트 반환")
                void getSuccessVideosWithNoInput() {

                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(-1L);
                    cond.setGymName("");
                    cond.setColor("");

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond);

                    // then
                    assertTrue(successVideos.get(0).getVideoBoardId() > successVideos.get(1).getVideoBoardId());
                    assertEquals(PAGE_SIZE, successVideos.size());
                }
            }

            @Nested
            @DisplayName("storeId에 숫자 값이 있을 경우")
            class Exist_Store_Id {
                @Test
                @DisplayName("storeId보다 작은 ID 중 큰 순서대로 지정된 사이즈의 게시글 리스트 반환 ")
                void getSuccessVideosWithStoreId() {

                    // given
                    Long setId = 2L;
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(setId);
                    cond.setGymName("");
                    cond.setColor("");

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond);

                    // then
                    assertTrue(cond.getStoreId() > successVideos.get(0).getVideoBoardId());
                    assertEquals(setId-1, successVideos.size());
                }
            }

            @Nested
            @DisplayName("storeId가 DB에 저장된 가장 작은 숫자일 경우(최초 저장된 데이터)")
            class Last_Store_Id {
                @Test
                @DisplayName("빈 리스트를 반환")
                void getSuccessVideosWithLastStoreId() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(0L);
                    cond.setGymName("");
                    cond.setColor("");

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond);

                    // then
                    assertEquals(0, successVideos.size());
                }
            }

        }

        @Nested
        @DisplayName("색상 검색 조건이 있고")
        class With_Color_Search_Condition {
            @Nested
            @DisplayName("storeId가 -1일 경우")
            class No_Store_ID {
                @Test
                @DisplayName("색상 조건에 해당하는 리스트를 Id값이 큰 순서대로 사이즈만큼 반환")
                void getSuccessVideosWithColorWihtoutStoreID() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(-1L);
                    cond.setGymName("");
                    cond.setColor("초록");

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond);

                    // then
                    assertEquals(successVideos.get(0).getColor(), cond.getColor());
                    assertEquals(successVideos.get(1).getColor(), cond.getColor());
                    assertEquals(PAGE_SIZE,successVideos.size());
                }

            }
        }

        @Nested
        @DisplayName("암장 검색 조건이 있고")
        class With_Gym_Search_Condition {
            @Nested
            @DisplayName("storeId가 -1일 경우")
            class No_Store_Id {
                @Test
                @DisplayName("암장 조건에 해당하는 리스트를 Id값이 큰 순서대로 사이즈만큼 반환")
                void getSuccessVideosWithGymNameWithoutStoreID() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(-1L);
                    cond.setGymName("더클라임");
                    cond.setColor("");

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond);

                    // then
                    assertTrue(successVideos.get(0).getGymName().contains(cond.getGymName()));
                    assertEquals(PAGE_SIZE,successVideos.size());
                }
            }
        }

        @Nested
        @DisplayName("모든 조건이 있을 경우")
        class With_ALL_Condition {

            @Nested
            @DisplayName("storeId가 -1일 경우")
            class No_Store_Id {

                @Test
                @DisplayName("검색조건에 해당하는 데이터를 Id가 큰 값부터 사이즈만큼 반환")
                void getSuccessVideosWithAllCondWithoutStoreID() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(-1L);
                    cond.setGymName("더클라임 홍대점");
                    cond.setColor("파랑");

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond);

                    // then
                    assertEquals(successVideos.get(0).getGymName(), cond.getGymName());
                    assertEquals(successVideos.get(0).getColor(), cond.getColor());
                    assertEquals(PAGE_SIZE, successVideos.size());
                }
            }

        }

    }


    @Nested
    @DisplayName("내 성공 영상 게시글 올리기 메소드는")
    class UploadMySuccessVideoPost {
        @Nested
        @DisplayName("유저 정보를 찾을 수 없을 경우")
        class No_Such_User {
            @Test
            @DisplayName("USER_NOT_FOUND 예외를 발생시킴")
            void noSuchUserException() {
                // given
                Long memberId = 2000000L; //DB에 없는 id
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(videoIds.get(0));
                request.setTitle("성공했습니다.");

                assertThrows(GlobalBaseException.class, () -> videoBoardService.uploadMySuccessVideoPost(memberId, request));
            }
        }

        @Nested
        @DisplayName("비디오를 찾을 수 없을 경우")
        class No_Such_Video {
            @Test
            @DisplayName("VIDEO_NOT_FOUND 예외를 발생시킴")
            void noSuchVideoException() {
                Long memberId = memberIds.get(0);
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(2000000L); // DB에 없는 ID
                request.setTitle("성공했습니다.");

                assertThrows(VideoNotFoundException.class, () -> videoBoardService.uploadMySuccessVideoPost(memberId, request));
            }
        }

        @Nested
        @DisplayName("유저 정보와 비디오의 소유자가 일치하지 않을 경우")
        class Video_User_Mismatch {
            @Test
            @DisplayName("Video_User_Mismatch 예외를 발생시킴")
            void videoUserMismatchException() {
                Long memberId = memberIds.get(0);
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(videoIds.get(33));
                request.setTitle("성공했습니다.");

                assertThrows(VideoUserMismatchException.class, () -> videoBoardService.uploadMySuccessVideoPost(memberId, request));
            }
        }

        @Nested
        @DisplayName("memberId/videoId/video, member간 유효성 확인을 통과할 경우")
        class All_Exception_pass {
            @Test
            @DisplayName("영상 게시글을 DB에 저장한 후 id값을 반환")
            void uploadSuccessVideoPost() {
                // given
                Long memberId = memberIds.get(0);
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(videoIds.get(0));
                request.setTitle("성공했습니다.");
                // when
                Long videoBoardId = videoBoardService.uploadMySuccessVideoPost(memberId, request);
                Optional<VideoBoard> videoBoard = videoBoardRepository.findById(videoBoardId);
                // then
                assertEquals(videoBoardId, videoBoard.get().getId());
                assertEquals(videoBoard.get().getTitle(), request.getTitle());
            }
        }
    }

    @Nested
    @DisplayName("완등 영상 게시글 상세 조회 메소드는")
    class GetVideoDetail {
        @Nested
        @DisplayName("없는 videoBoardId일 경우")
        class No_Video_Board_Id {
            @Test
            @DisplayName("PostNotFound예외를 발생시킨다")
            void postNotFoundException() {
                Long videoBoardId = 2000000L;
                assertThrows(PostNotFoundException.class, () -> videoBoardService.getVideoDetail(videoBoardId));
            }
        }

        @Nested
        @DisplayName("videoBoardId가 있을 경우")
        class Post_Exist {
            @Test
            @DisplayName("비디오의 상세 정보를 반환")
            void getVideoDetail() {
                Long videoBoardId = videoBoardIds.get(0);
                VideoBoardDetailResponse videoBoardDetail = videoBoardService.getVideoDetail(videoBoardId);
                assertEquals(videoBoardId, videoBoardDetail.getVideoBoardId());
            }
        }
    }

    @Nested
    @DisplayName("완등 영상 게시글 수정 메소드는")
    class updateSuccessPost {

        @Nested
        @DisplayName("게시글 Id를 찾을 수 없는 경우")
        class No_Such_Post {
            @Test
            @DisplayName("Post Not Found 예외를 발생시킴")
            void PostNotFoundException() {
                Long memberId = memberIds.get(0);
                SuccessPostUpdateRequest request = new SuccessPostUpdateRequest();
                request.setVideoBoardId(2000000L); // 없는 게시글 번호
                request.setTitle("수정했습니다.");

                assertThrows(PostNotFoundException.class, () -> videoBoardService.updateSuccessPost(memberId, request));
            }
        }

        @Nested
        @DisplayName("멤버가 해당 게시글 작성자와 일치하지 않는 경우")
        class User_Mismatch {
            @Test
            @DisplayName("Writer Mismatch 예외를 발생시킴")
            void WriterMismatchException() {
                Long memberId = memberIds.get(0);
                SuccessPostUpdateRequest request = new SuccessPostUpdateRequest();
                request.setVideoBoardId(videoBoardIds.get(17)); // 해당 게시글의 작성자는 1번째
                request.setTitle("수정했습니다.");

                assertThrows(WriterMismatchException.class, () -> videoBoardService.updateSuccessPost(memberId, request));
            }
        }

        @Nested
        @DisplayName("게시글이 있고 수정 권한이 있을 경우")
        class Exception_All_Pass {
            @Test
            @DisplayName("수정이 정상적으로 완료")
            void updateSuccessPost() {
                Long memberId = memberIds.get(0);
                SuccessPostUpdateRequest request = new SuccessPostUpdateRequest();
                request.setVideoBoardId(videoBoardIds.get(0));
                request.setTitle("수정했습니다.");

                videoBoardService.updateSuccessPost(memberId, request);
                VideoBoard videoBoard = videoBoardRepository.findById(request.getVideoBoardId()).get();

                assertEquals(request.getTitle(), videoBoard.getTitle());
            }
        }

    }

    @Nested
    @DisplayName("완등 영상 게시글 삭제 메소드는")
    class DeleteSuccessPost {

        @Nested
        @DisplayName("게시글이 있고 작성자가 유저와 일치할 경우")
        class Exception_All_Pass {
            @Test
            @DisplayName("해당 게시글은 삭제되며, 비디오에서 해당 영상의 isPosted변수 false로 변경")
            void deleteSuccessPost() {
                Long memberId = memberIds.get(0);
                Long videoBoardId = videoBoardIds.get(0);
                VideoBoard videoBoard = videoBoardRepository.findById(videoBoardId)
                        .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
                Video video = videoBoard.getVideo();
                assertTrue(video.getIsPosted());
                videoBoardService.deleteSuccessPost(memberId, videoBoardId);
                // 삭제 후에 해당 데이터를 한번 더 삭제할 경우 No Such Post 예외 발생
                assertThrows(PostNotFoundException.class, () -> videoBoardService.deleteSuccessPost(memberId, videoBoardId));
                assertFalse(video.getIsPosted());
            }
        }
    }

    @Nested
    @DisplayName("내 완등 영상 게시글 가져오기 메소드는")
    class GetMySuccessVideoPosts {
        @Nested
        @DisplayName("storeId가 -1일 경우")
        class Store_Id_Null {
            @Test
            @DisplayName("멤버의 성공 영상 게시글의 id값이 큰 순서대로 사이즈만큼 반환")
            void getMySuccessVideoPosts() {
                Long memberId = memberIds.get(0);
                Long storeId = -1L;
                List<VideoBoardSummaryResponse> result = videoBoardService.getMySuccessVideoPosts(memberId, storeId);

                assertTrue(result.get(0).getVideoBoardId() > result.get(1).getVideoBoardId());
                assertEquals(MYPAGE_SIZE, result.size());
            }
        }
    }
}