package org.anotherclass.colortherock.domain.memberrecord.service;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.exception.UserNotFoundException;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.*;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.request.MyVideoRequest;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class RecordServiceTest {
    @Autowired
    private RecordService recordService;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    EntityManager em;
    Member member;
    @Autowired
    private RecordRepository recordRepository;

    @BeforeEach
    public void setMember() {
        member = new Member("johan@rock.com", "조한", Member.RegistrationId.kakao);
        em.persist(member);
        for (int i = 1; i <= 9; i++) {
            UploadVideoRequest saveDto = UploadVideoRequest.builder()
                    .shootingDate(LocalDate.parse("2023-01-17"))
                    .level(i)
                    .gymName("더클라임 강남")
                    .color("노랑")
                    .isSuccess(true).build();
            em.persist(saveDto.toEntity(member));
        }
        for (int i = 1; i <= 9; i++) {
            UploadVideoRequest saveDto = UploadVideoRequest.builder()
                    .shootingDate(LocalDate.parse("2023-01-17"))
                    .level(i)
                    .gymName("더클라임 강남")
                    .color("노랑")
                    .isSuccess(false).build();
            em.persist(saveDto.toEntity(member));
        }
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("사용자 레벨별 통계 반환")
    public void colorRecordsTest() {
        // when
        List<LevelStatResponse> list = recordService.getColorRecords(member);
        // then
        assertEquals(9, list.size());
        assertEquals(2, list.get(0).getTotal());
        assertEquals(2, list.get(1).getLevel());
        assertEquals(1, list.get(1).getSuccess());
    }

    @Test
    @DisplayName("사용자 날짜별 통계 반환")
    public void dateRecordsTest() {
        // when
        List<LevelStatResponse> dateRecords = recordService.getDateRecords(member, LocalDate.parse("2023-01-17"));
        // then
        assertEquals(9, dateRecords.size());
        assertEquals(2, dateRecords.get(0).getTotal());
        assertEquals(1, dateRecords.get(8).getSuccess());
    }

    @Test
    @DisplayName("사용자 운동 통계 반환")
    public void totalRecordsTest() {
        // given
        MemberRecord testRecord = MemberRecord.builder().member(member).videoCount(18).successCount(9).build();
        em.persist(testRecord);
        em.flush();
        em.clear();
        // when
        TotalStatResponse totalRecords = recordService.getTotalRecords(member);
        // then
        assertEquals(testRecord.getVideoCount(), totalRecords.getVideoCount());
    }

    @Test
    @DisplayName("나의 운동 기록 반환")
    public void getMyVideosTest() {
        // given
        LocalDate localDate = LocalDate.parse("2023-01-17");
        MyVideoRequest request = MyVideoRequest.builder().isSuccess(true).shootingDate(localDate).build();
        // when
        List<VideoListResponse> myVideos = recordService.getMyVideos(member, request);
        // then
        assertEquals(9, myVideos.size());
    }

    @Test
    @DisplayName("영상 상세 조회")
    public void videoDetailTest() {
        // given
        LocalDate date = LocalDate.parse("2023-01-19");
        UploadVideoRequest saveDto = UploadVideoRequest.builder()
                .shootingDate(date)
                .level(1)
                .gymName("더클라임 강남")
                .isSuccess(false)
                .color("노랑").build();
        Video video = saveDto.toEntity(member);
        Long videoId = videoRepository.save(video).getId();
        // when
        VideoDetailResponse videoDetail = recordService.getVideoDetail(videoId);
        // then
        assertEquals(video.getId(), videoDetail.getId());
    }

    @Test
    @DisplayName("영상 상세 조회 - 실패")
    public void videoDetailExceptionTest() {
        // given
        LocalDate date = LocalDate.parse("2023-01-19");
        UploadVideoRequest saveDto = UploadVideoRequest.builder()
                .shootingDate(date)
                .level(1)
                .gymName("더클라임 강남")
                .isSuccess(false)
                .color("노랑").build();
        Video video = saveDto.toEntity(member);
        Long videoId = videoRepository.save(video).getId();
        // when
        Assertions.assertThrows(VideoNotFoundException.class, () -> recordService.getVideoDetail(videoId + 1));
    }

    @Test
    @DisplayName("영상 갯수 증가")
    public void addVideoCountTest() {
        // given
        MemberRecord testRecord = MemberRecord.builder().member(member).videoCount(18).successCount(9).build();
        em.persist(testRecord);
        em.flush();
        em.clear();
        // when
        recordService.addVideoCount(member, true);
        // then
        MemberRecord insertedRecord = recordRepository.findByMember(member);
        assertEquals(19, insertedRecord.getVideoCount());
        assertEquals(10, insertedRecord.getSuccessCount());
    }

    @Test
    @DisplayName("영상 갯수 감소")
    public void subVideoCountTest() {
        // given
        MemberRecord testRecord = MemberRecord.builder().member(member).videoCount(18).successCount(9).build();
        em.persist(testRecord);
        em.flush();
        em.clear();
        // when
        recordService.subVideoCount(member, true);
        // then
        MemberRecord insertedRecord = recordRepository.findByMember(member);
        assertEquals(17, insertedRecord.getVideoCount());
        assertEquals(8, insertedRecord.getSuccessCount());
    }

    @Test
    @DisplayName("새로운 record 생성")
    public void saveNewRecordTest() {
        // given
        Long memberId = member.getId();
        // when
        recordService.saveNewRecord(memberId);
        // then
        MemberRecord insertedRecord = recordRepository.findByMember(member);
        assertEquals(0, insertedRecord.getVideoCount());
        assertEquals(0, insertedRecord.getSuccessCount());
    }

    @Test
    @DisplayName("새로운 record 생성 실패")
    public void noUserTest() {
        // given
        Long memberId = member.getId() * -1;
        // when & then
        assertThrows(UserNotFoundException.class, () -> recordService.saveNewRecord(memberId));
    }

    @Test
    @DisplayName("암장 방문 기록 반환")
    public void getVisitListTest() {
        // given
        UploadVideoRequest saveDto = UploadVideoRequest.builder()
                .shootingDate(LocalDate.parse("2023-01-18"))
                .level(3)
                .gymName("볼더프렌즈 홍대")
                .color("노랑")
                .isSuccess(true).build();
        em.persist(saveDto.toEntity(member));
        em.flush();
        em.clear();
        // when
        VisitResponse visitList = recordService.getVisitList(member);
        // then
        assertEquals(2, visitList.getTotalCount());
        assertEquals(1, visitList.getData().get(0).getCount());
    }

    @Test
    @DisplayName("달력 색상 반환")
    public void getCalendarColorTest() {
         // given
        UploadVideoRequest saveDto = UploadVideoRequest.builder()
                .shootingDate(LocalDate.parse("2023-01-17"))
                .level(1)
                .gymName("더클라임 강남")
                .color("노랑")
                .isSuccess(true).build();
        em.persist(saveDto.toEntity(member));
        em.flush();
        em.clear();
        // when
        List<DailyColorResponse> calendarColor = recordService.getCalendarColor(member, "2023-01");
        // then
        assertEquals(1, calendarColor.size());
        assertEquals("2023-01-17", calendarColor.get(0).getDate().toString());
    }
}