package org.anotherclass.colortherock.domain.videoboard.service;

import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.exception.VideoUserMismatchException;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoBoardServiceTest {

    @Autowired
    private VideoBoardService videoBoardService;
    @Autowired
    private VideoBoardRepository videoBoardRepository;

    @Nested
    @DisplayName("getSuccessVideo 메소드는")
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
                    cond.setStoreId(null);
                    cond.setGymName("");
                    cond.setColor("");
                    Pageable pageable = Pageable.ofSize(2); // 사이즈 임의로 2로 지정

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond, pageable);

                    // then
                    assertTrue(successVideos.get(0).getVideoBoardId() > successVideos.get(1).getVideoBoardId());
                    assertEquals(successVideos.size(), 2);
                }
            }

            @Nested
            @DisplayName("storeId에 숫자 값이 있을 경우")
            class Exist_Store_Id {
                @Test
                @DisplayName("storeId보다 작은 ID 중 큰 순서대로 지정된 사이즈의 게시글 리스트 반환 ")
                void getSuccessVideosWithStoreId() {

                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(3L);
                    cond.setGymName("");
                    cond.setColor("");
                    Pageable pageable = Pageable.ofSize(2); // 사이즈 임의로 2로 지정

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond, pageable);

                    // then
                    assertTrue(cond.getStoreId() > successVideos.get(0).getVideoBoardId());
                    assertEquals(successVideos.size(), 2);
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
                    Pageable pageable = Pageable.ofSize(2); // 사이즈 임의로 2로 지정

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond, pageable);

                    // then
                    assertEquals(successVideos.size(), 0);
                }
            }

        }

        @Nested
        @DisplayName("색상 검색 조건이 있고")
        class With_Color_Search_Condition {
            @Nested
            @DisplayName("storeId가 null일 경우")
            class No_Store_ID {
                @Test
                @DisplayName("색상 조건에 해당하는 리스트를 Id값이 큰 순서대로 사이즈만큼 반환")
                void getSuccessVideosWithColorWihtoutStoreID() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(null);
                    cond.setGymName("");
                    cond.setColor("초록");
                    Pageable pageable = Pageable.ofSize(2); // 사이즈 임의로 2로 지정

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond, pageable);

                    // then
                    assertEquals(successVideos.get(0).getColor(), cond.getColor());
                    assertEquals(successVideos.size(), 2);
                }

            }
        }

        @Nested
        @DisplayName("암장 검색 조건이 있고")
        class With_Gym_Search_Condition {
            @Nested
            @DisplayName("storeId가 null일 경우")
            class No_Store_Id {
                @Test
                @DisplayName("암장 조건에 해당하는 리스트를 Id값이 큰 순서대로 사이즈만큼 반환")
                void getSuccessVideosWithGymNameWithoutStoreID() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(null);
                    cond.setGymName("더클라임 강남");
                    cond.setColor("");
                    Pageable pageable = Pageable.ofSize(2); // 사이즈 임의로 2로 지정

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond, pageable);

                    // then
                    assertEquals(successVideos.get(0).getGymName(), cond.getGymName());
                    assertEquals(successVideos.size(), 2);
                }
            }
        }

        @Nested
        @DisplayName("모든 조건이 있을 경우")
        class With_ALL_Condition {

            @Nested
            @DisplayName("storeId가 null일 경우")
            class No_Store_Id {

                @Test
                @DisplayName("검색조건에 해당하는 데이터를 Id가 큰 값부터 사이즈만큼 반환")
                void getSuccessVideosWithAllCondWithoutStoreID() {
                    // given
                    VideoBoardSearchRequest cond = new VideoBoardSearchRequest();
                    cond.setStoreId(null);
                    cond.setGymName("더클라임 강남");
                    cond.setColor("초록");
                    Pageable pageable = Pageable.ofSize(2); // 사이즈 임의로 2로 지정

                    // when
                    List<VideoBoardSummaryResponse> successVideos = videoBoardService.getSuccessVideos(cond, pageable);

                    // then
                    assertEquals(successVideos.get(0).getGymName(), cond.getGymName());
                    assertEquals(successVideos.get(0).getColor(), cond.getColor());
                    assertEquals(successVideos.size(), 2);
                }
            }

        }

    }


    @Nested
    @DisplayName("uploadMySuccessVideoPost 메소드는")
    class UploadMySuccessVideoPost {
        @Nested
        @DisplayName("유저 정보를 찾을 수 없을 경우")
        class No_Such_User {
            @Test
            @DisplayName("NO_SUCH_USER 예외를 발생시킴")
            void noSuchUserException() {
                // given
                Long memberId = 2L; //DB에 없는 id
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(1L);
                request.setTitle("성공했습니다.");

                assertThrows(GlobalBaseException.class, () -> videoBoardService.uploadMySuccessVideoPost(memberId, request));
            }
        }

        @Nested
        @DisplayName("비디오를 찾을 수 없을 경우")
        class No_Such_Video {
            @Test
            @DisplayName("NO_SUCH_VIDEO 예외를 발생시킴")
            void noSuchVideoException() {
                Long memberId = 0L;
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(10L); // DB에 없는 ID
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
                Long memberId = 1L;
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(1L); // 해당 비디오의 memberId는 0L
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
                Long memberId = 0L;
                SuccessVideoUploadRequest request = new SuccessVideoUploadRequest();
                request.setVideoId(1L);
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
    @DisplayName("getVideoDetail 메소드는")
    class GetVideoDetail {
        @Nested
        @DisplayName("없는 videoBoardId일 경우")
        class No_Such_Post {
            @Test
            @DisplayName("PostNotFound예외를 발생시킨다")
            void postNotFoundException() {
                Long videoBoardId = 10L;
                assertThrows(PostNotFoundException.class, () -> videoBoardService.getVideoDetail(videoBoardId));
            }
        }

        @Nested
        @DisplayName("videoBoardId가 있을 경우")
        class Post_Exist {
            @Test
            @DisplayName("비디오의 상세 정보를 반환")
            void getVideoDetail() {
                Long videoBoardId = 0L;
                VideoBoardDetailResponse videoBoardDetail = videoBoardService.getVideoDetail(videoBoardId);
                assertEquals(videoBoardId, videoBoardDetail.getVideoBoardId());
            }
        }
    }

    @Nested
    @DisplayName("updateSuccessPost 메소드는")
    class updateSuccessPost {

        @Nested
        @DisplayName("게시글 Id를 찾을 수 없는 경우")
        class No_Such_Post {
            @Test
            @DisplayName("Post Not Found 예외를 발생시킴")
            void PostNotFoundException() {
                Long memberId = 1L;
                SuccessPostUpdateRequest request = new SuccessPostUpdateRequest();
                request.setVideoBoardId(10L); // 없는 게시글 번호
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
                Long memberId = 1L;
                SuccessPostUpdateRequest request = new SuccessPostUpdateRequest();
                request.setVideoBoardId(5L); // 해당 게시글의 작성자는 0L
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
                Long memberId = 0L;
                SuccessPostUpdateRequest request = new SuccessPostUpdateRequest();
                request.setVideoBoardId(5L);
                request.setTitle("수정했습니다.");

                videoBoardService.updateSuccessPost(memberId, request);
                VideoBoard videoBoard = videoBoardRepository.findById(request.getVideoBoardId()).get();

                assertEquals(request.getTitle(), videoBoard.getTitle());
            }
        }

    }

    @Nested
    @DisplayName("deleteSuccessPost 메소드는")
    class DeleteSuccessPost {

        @Nested
        @DisplayName("게시글이 있고 작성자가 유저와 일치할 경우")
        class Exception_All_Pass {
            @Test
            @DisplayName("해당 게시글을 삭제")
            void deleteSuccessPost() {
                Long memberId = 0L;
                Long videoBoardId = 1L;
                videoBoardService.deleteSuccessPost(memberId, videoBoardId);
                // 삭제 후에 해당 데이터를 한번 더 삭제할 경우 No Such Post 예외 발생
                assertThrows(PostNotFoundException.class, () -> videoBoardService.deleteSuccessPost(memberId, videoBoardId));
            }
        }
    }

    @Nested
    @DisplayName("getMySuccessVideoPosts 메소드는")
    class GetMySuccessVideoPosts {
        @Nested
        @DisplayName("storeId가 null일 경우")
        class Store_Id_Null {
            @Test
            @DisplayName("멤버의 성공 영상 게시글의 id값이 큰 순서대로 사이즈만큼 반환")
            void getMySuccessVideoPosts() {
                Long memberId = 0L;
                Long storeId = null;
                Pageable pageable = Pageable.ofSize(2);

                List<VideoBoardSummaryResponse> result = videoBoardService.getMySuccessVideoPosts(memberId, storeId, pageable);

                assertTrue(result.get(0).getVideoBoardId() > result.get(1).getVideoBoardId());
                assertEquals(result.size(), 2);
            }
        }
    }
}