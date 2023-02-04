package org.anotherclass.colortherock.domain.video.repository;


import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.VisitListResponse;
import org.anotherclass.colortherock.domain.video.dto.DateLevelDto;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class VideoRepositoryTest {
    @Autowired
    private VideoReadRepository videoReadRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VideoRepository videoRepository;

    private Member member;
    @BeforeEach
    public void setMemberAndVideos() {
        // given
        member = Member.builder()
                .email("johan@rock.com")
                .registrationId(Member.RegistrationId.kakao)
                .nickname("nickname").build();
        memberRepository.save(member);
        for (int i = 0; i < 10; i++) {
            Video video = Video.builder()
                    .shootingDate(LocalDate.parse("2023-01-1" + i))
                    .level(1)
                    .member(member)
                    .isSuccess(true)
                    .gymName("tempGym1").build();
            videoRepository.save(video);
        }
        for (int i = 0; i < 10; i++) {
            Video video = Video.builder()
                    .shootingDate(LocalDate.parse("2023-01-1" + i))
                    .level(2)
                    .member(member)
                    .isSuccess(true)
                    .gymName("tempGym2").build();
            videoRepository.save(video);
        }
    }
    @Test
    @DisplayName("암장 방문 통계 조회")
    public void 방문통계_조회_성공() {
        // when
        List<VisitListResponse> visitList = videoReadRepository.searchVisitCount(member);
        // then
        Assertions.assertEquals(2, visitList.size());
        Assertions.assertEquals("tempGym1", visitList.get(0).getGymName());
        Assertions.assertEquals(10, visitList.get(1).getCount());
    }

    @Test
    @DisplayName("날짜별 완등 레벨 조회")
    public void 레벨_조회_성공() {
        // when
        List<DateLevelDto> dtos = videoReadRepository.searchDailyColor(member, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"));
        // then
        Assertions.assertEquals(20, dtos.size());
        Assertions.assertEquals("2023-01-10", dtos.get(0).getDate().toString());
        Assertions.assertEquals(1, dtos.get(1).getLevel());
    }

    @Test
    @DisplayName("사용자별 암장 방문 일수 조회")
    public void 방문횟수_조회_성공() {
        // when
        Integer visitCount = videoReadRepository.searchTotalVisit(member);
        // then
        Assertions.assertNotNull(visitCount);
        Assertions.assertEquals(10, visitCount);
    }
}
