package org.anotherclass.colortherock.domain.video.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.response.VisitListDto;
import org.anotherclass.colortherock.domain.video.dto.DateLevelDto;
import org.anotherclass.colortherock.domain.video.entity.QVideo;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.request.MySuccessVideoRequest;
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

    // 내 영상 조회 (성공/실패)
    public Slice<Video> searchBySlice(Pageable pageable, MyVideoRequest request, Member member) {
        List<Video> results = queryFactory.selectFrom(video)
                .where(
                        // no-offset 페이지 처리
                        ltVideoId(request.getVideoId()),
                        // 다른 조건
                        video.member.eq(member),
                        video.shootingDate.eq(request.getShootingDate()),
                        video.isSuccess.eq(request.getIsSuccess())
                )
                .orderBy(video.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    // 업로드 된 적 없는 성공 영상 조회
    public Slice<Video> searchBySuccessRequest(Pageable pageable, MySuccessVideoRequest request, Member member) {
        List<Video> results = queryFactory.selectFrom(video)
                .where(
                        // no-offset 페이지 처리
                        ltVideoId(request.getStoreId()),
                        // 다른 조건
                        video.member.eq(member),
                        video.shootingDate.eq(request.getShootingDate()),
                        video.isSuccess.isTrue(),
                        video.isPosted.isFalse()
                )
                .orderBy(video.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    // videoId를 통한 no-offset 처리 메소드
    private BooleanExpression ltVideoId(Long videoId) {
        if (videoId == -1L) return null;
        return video.id.lt(videoId);
    }

    // 무한 스크롤 방식을 처리하는 메소드
    private Slice<Video> checkLastPage(Pageable pageable, List<Video> results) {
        boolean hasNext = false;

        // 조회 결과 갯수가 요청 페이지 사이즈보다 크면 다음 페이지가 존재
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }

    // 사용자 암장 방문 횟수
    public List<VisitListDto> searchVisitCount(Member member) {
        return queryFactory.select(
                        Projections.constructor(VisitListDto.class,
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

    // 사용자가 영상을 찍은 날짜의 수를 반환
    public Integer searchTotalVisit(Member member) {
        return Math.toIntExact(queryFactory.select(video.shootingDate.countDistinct())
                .from(video)
                .where(video.member.eq(member))
                .fetchFirst());
    }
}
