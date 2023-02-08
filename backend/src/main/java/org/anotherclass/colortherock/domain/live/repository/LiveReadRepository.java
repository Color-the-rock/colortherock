package org.anotherclass.colortherock.domain.live.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.live.entity.QLive;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LiveReadRepository {
    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public LiveReadRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QLive live = QLive.live;

    public Slice<Live> searchBySlice(Long liveId, Pageable pageable) {
        List<Live> results = queryFactory.selectFrom(live)
                .where(
                        ltLiveId(liveId),
                        live.isPublic.eq(true),
                        live.isLive.eq(true)
                )
                .orderBy(live.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    private BooleanExpression ltLiveId(Long liveId) {
        if(liveId == -1L) return null;
        return live.id.lt(liveId);
    }

    private Slice<Live> checkLastPage(Pageable pageable, List<Live> results) {
        boolean hasNext = false;

        if(results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
