package org.anotherclass.colortherock.domain.video.repository;


import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.VisitListResponse;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.junit.jupiter.api.Assertions;
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
    @Test
    @DisplayName("암장 방문 통계 반환")
    public void 방문통계_반환_성공() {
        // given
        Member member = Member.builder()
                .email("johan@rock.com")
                .registrationId(Member.RegistrationId.kakao)
                .nickname("nickname").build();
        memberRepository.save(member);
        for (int i = 0; i < 10; i++) {
            Video video = Video.builder()
                    .shootingDate(LocalDate.parse("2023-01-1" + i))
                    .member(member)
                    .gymName("tempGym1").build();
            videoRepository.save(video);
        }
        for (int i = 0; i < 10; i++) {
            Video video = Video.builder()
                    .shootingDate(LocalDate.parse("2023-01-1" + i))
                    .member(member)
                    .gymName("tempGym2").build();
            videoRepository.save(video);
        }
        // when
        List<VisitListResponse> visitList = videoReadRepository.searchVisitCount(member);
        // then
        Assertions.assertEquals(2, visitList.size());
        Assertions.assertEquals("tempGym1", visitList.get(0).getGymName());
        Assertions.assertEquals(10, visitList.get(1).getCount());
    }
}
