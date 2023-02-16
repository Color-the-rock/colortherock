package org.anotherclass.colortherock.domain.live.repository;

import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class LiveRepositoryTest {
    @Autowired
    private LiveRepository liveRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("sessionId를 통한 session 삭제")
    void sessoin삭제() {
        // given
        Member member = Member.builder()
                .email("email@colortherock.com")
                .nickname("test_name")
                .registrationId(Member.RegistrationId.KAKAO).build();

        memberRepository.save(member);

        String sessionId = "test_sessionId";
        Live live = Live.builder()
                .isLive(true)
                .gymName("더클라임 강남")
                .thumbnailURL("thumbnail1.jpg")
                .title("test 제목")
                .sessionId(sessionId)
                .isPublic(true)
                .member(member)
                .build();

        liveRepository.save(live);
        // when
        liveRepository.deleteBySessionId(sessionId);
        // then
        Assertions.assertFalse(liveRepository.findBySessionId(sessionId).isPresent());
    }
}