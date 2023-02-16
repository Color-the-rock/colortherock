package org.anotherclass.colortherock.domain.memberrecord.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
@DisplayName("member record repository test")
class RecordRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private RecordRepository recordRepository;

    @Test
    @DisplayName("member로 member record 검색")
    @Transactional
    void 검색_성공() {
        // given
        Member testMember = Member.builder().email("test@colortherock.com").nickname("test_name").registrationId(Member.RegistrationId.KAKAO).build();
        MemberRecord testRecord = MemberRecord.builder().member(testMember).successCount(20).videoCount(40).build();
        entityManager.persist(testMember);
        entityManager.persist(testRecord);
        entityManager.flush();
        entityManager.clear();
        // when
        MemberRecord insertedRecord = recordRepository.findByMember(testMember);
        // then
        assertEquals(testRecord.getVideoCount(), insertedRecord.getVideoCount());
    }
}