package org.anotherclass.colortherock.domain.live.repository;

import org.anotherclass.colortherock.domain.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveRepository extends JpaRepository<Live, Long> {

}
