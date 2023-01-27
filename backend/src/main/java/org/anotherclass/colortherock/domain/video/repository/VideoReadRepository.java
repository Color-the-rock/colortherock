package org.anotherclass.colortherock.domain.video.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.video.entity.QVideo;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.request.MyVideoRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoReadRepository {
    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public VideoReadRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QVideo video = QVideo.video;

    public Slice<Video> searchBySlice(Pageable pageable, MyVideoRequest request) {
        List<Video> results = queryFactory.selectFrom(video)
                .where(
                        // no-offset 페이지 처리
                        gtVideoId(request.getVideoId()),
                        // 다른 조건
                        video.member.eq(request.getMember()),
                        video.shootingDate.eq(request.getShootingDate()),
                        video.isSuccess.eq(request.getIsSuccess())
                )
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    // videoId를 통한 no-offset 처리 메소드
    private BooleanExpression gtVideoId(Long videoId) {
        if(videoId == null) return null;
        return video.id.gt(videoId);
    }

    // 무한 스크롤 방식을 처리하는 메소드
    private Slice<Video> checkLastPage(Pageable pageable, List<Video> results) {
        boolean hasNext = false;

        // 조회 결과 갯수가 요청 페이지 사이즈보다 크면 다음 페이지가 존재
        if(results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
