package org.anotherclass.colortherock.domain.memberrecord.service;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoDetailResponse;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @BeforeEach
    public void setMember() {
        member = new Member("johan@rock.com", "조한", Member.RegistrationId.kakao);
        em.persist(member);
        for (int i = 1; i <= 9; i++) {
            UploadVideoRequest saveDto = new UploadVideoRequest(LocalDate.parse("2023-01-17"), i, "더클라임 강남", true, "노랑", member);
            em.persist(saveDto.toEntity());
        }
        for (int i = 1; i <= 9; i++) {
            UploadVideoRequest saveDto = new UploadVideoRequest(LocalDate.parse("2023-01-17"), i, "더클라임 강남", false, "노랑", member);
            em.persist(saveDto.toEntity());
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
        assertEquals(1, list.get(0).getTotal());
        assertEquals(1, list.get(1).getTotal());
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
    @DisplayName("영상 상세 조회")
    public void videoDetailTest() {
        // given
        LocalDate date = LocalDate.parse("2023-01-19");
        UploadVideoRequest saveDto = new UploadVideoRequest(date, 1, "더클라임 강남", false, "노랑", member);
        Video video = saveDto.toEntity();
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
        UploadVideoRequest saveDto = new UploadVideoRequest(date, 1, "더클라임 강남", false, "노랑", member);
        Video video = saveDto.toEntity();
        Long videoId = videoRepository.save(video).getId();
        // when
        Assertions.assertThrows(VideoNotFoundException.class, () -> recordService.getVideoDetail(videoId + 1));
    }
}