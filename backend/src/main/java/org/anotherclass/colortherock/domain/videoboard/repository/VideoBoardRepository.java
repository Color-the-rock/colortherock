package org.anotherclass.colortherock.domain.videoboard.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.videoboard.entity.QVideoBoard;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoBoardRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;


    public VideoBoardRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    QVideoBoard videoBoard = QVideoBoard.videoBoard;

    public Slice<VideoBoard> searchBySlice(Long lastStoreId, Pageable pageable) {

        List<VideoBoard> results = query.selectFrom(videoBoard)
                .where(
                        // no-offset 페이징 처리
                        ltStoreId(lastStoreId)
                )
                .orderBy(videoBoard.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        // 무한 스크롤 처리
        return checkLastPage(pageable, results);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression ltStoreId(Long storeId) {
        if (storeId == null) {
            return null;
        }

        return videoBoard.id.lt(storeId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<VideoBoard> checkLastPage(Pageable pageable, List<VideoBoard> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }


}
