package org.anotherclass.colortherock.domain.live.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.live.entity.QLive;
import org.anotherclass.colortherock.domain.live.request.LiveListRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LiveReadRepository {


    private final JPAQueryFactory queryFactory;

    public LiveReadRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QLive live = QLive.live;

    public Slice<Live> searchBySlice(LiveListRequest liveListRequest, Pageable pageable) {
        List<Live> results = queryFactory.selectFrom(live)
                .where(
                        ltLiveId(liveListRequest.getLiveId()),
                        live.isPublic.eq(true),
                        live.isLive.eq(true),
                        // 암장 검색
                        checkGymName(liveListRequest.getGymName())
                )
                .orderBy(live.id.desc())
                .limit(pageable.getPageSize() + 1L)
                .fetch();
        return checkLastPage(pageable, results);
    }

    private BooleanExpression ltLiveId(Long liveId) {
        if(liveId == -1L) return null;
        return live.id.lt(liveId);
    }

    // 암장 검색을 처리하는 메서드
    private BooleanExpression checkGymName(String gymNameCond) {
        if (gymNameCond == null || gymNameCond.isBlank()) {
            return null;
        }
        return live.gymName.contains(gymNameCond);
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
