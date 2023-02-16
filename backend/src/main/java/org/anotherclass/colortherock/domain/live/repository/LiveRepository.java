package org.anotherclass.colortherock.domain.live.repository;

import org.anotherclass.colortherock.domain.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LiveRepository extends JpaRepository<Live, Long> {
    @Transactional
    void deleteBySessionId(String sessionId);
    Optional<Live> findBySessionId(String sessionId);
}