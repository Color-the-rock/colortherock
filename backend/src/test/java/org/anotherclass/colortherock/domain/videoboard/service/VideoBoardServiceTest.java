package org.anotherclass.colortherock.domain.videoboard.service;

import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class VideoBoardServiceTest {

    @Autowired
    private VideoBoardService videoBoardService;

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
                    Assertions.assertTrue(successVideos.get(0).getVideoBoardId() > successVideos.get(1).getVideoBoardId());
                    Assertions.assertEquals(successVideos.size(), 2);
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
                    Assertions.assertTrue(cond.getStoreId() > successVideos.get(0).getVideoBoardId());
                    Assertions.assertEquals(successVideos.size(), 2);
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
                    Assertions.assertEquals(successVideos.size(), 0);
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
                    Assertions.assertEquals(successVideos.get(0).getColor(), cond.getColor());
                    Assertions.assertEquals(successVideos.size(), 2);
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
                    Assertions.assertEquals(successVideos.get(0).getGymName(), cond.getGymName());
                    Assertions.assertEquals(successVideos.size(), 2);
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
                    Assertions.assertEquals(successVideos.get(0).getGymName(), cond.getGymName());
                    Assertions.assertEquals(successVideos.get(0).getColor(), cond.getColor());
                    Assertions.assertEquals(successVideos.size(), 2);
                }
            }

        }

    }


    @Test
    void uploadMySuccessVideo() {
    }

    @Test
    void getVideoDetail() {
    }

    @Test
    void updateSuccessPost() {
    }

    @Test
    void deleteSuccessPost() {
    }

    @Test
    void getMySuccessVideoPosts() {
    }
}