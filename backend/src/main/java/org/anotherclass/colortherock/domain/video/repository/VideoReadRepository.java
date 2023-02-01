package org.anotherclass.colortherock.domain.video.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.response.VisitListResponse;
import org.anotherclass.colortherock.domain.video.dto.DateLevelDto;
import org.anotherclass.colortherock.domain.video.entity.QVideo;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.request.MyVideoRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
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

    // 사용자 암장 방문 횟수
    public List<VisitListResponse> searchVisitCount(Member member) {
        return queryFactory.select(
                        Projections.constructor(VisitListResponse.class,
                                video.gymName.as("gymName"),
                                video.shootingDate.countDistinct().as("count"))
                )
                .from(video)
                .where(video.member.eq(member))
                .groupBy(video.gymName)
                .fetch();
    }

    // 사용자의 해당 날짜 사이에 존재하는 Video를 조회
    public List<DateLevelDto> searchDailyColor(Member member, LocalDate firstDate, LocalDate lastDate) {
        return queryFactory.select(
                    Projections.constructor(DateLevelDto.class,
                            video.shootingDate.as("date"),
                            video.level)
                )
                .from(video)
                .where(
                        video.member.eq(member),
                        video.shootingDate.between(firstDate, lastDate),
                        video.isSuccess.eq(true)
                )
                .orderBy(video.shootingDate.asc(), video.level.desc())
                .distinct()
                .fetch();
    }
}
