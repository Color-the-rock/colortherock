package org.anotherclass.colortherock.domain.videoboard.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.member.entity.QMember;
import org.anotherclass.colortherock.domain.video.entity.QVideo;
import org.anotherclass.colortherock.domain.videoboard.entity.QVideoBoard;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoBoardReadRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;


    public VideoBoardReadRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    QVideo video = QVideo.video;
    QVideoBoard videoBoard = QVideoBoard.videoBoard;
    QMember member = QMember.member;

    public Slice<VideoBoard> searchBySearchCond(VideoBoardSearchRequest condition, Pageable pageable) {

        Long lastStoreId = condition.getStoreId();
        String gymNameCond = condition.getGymName();
        String colorCond = condition.getColor();

        List<VideoBoard> results = query.selectFrom(videoBoard)
                .join(videoBoard.video, video)
                .where(
                        // no-offset 페이징 처리
                        checkStoreId(lastStoreId),
                        // 암장 검색
                        checkGymName(gymNameCond),
                        // 색상 검색
                        checkColor(colorCond)
                )
                .orderBy(videoBoard.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        // 무한 스크롤 처리
        return checkLastPage(pageable, results);
    }

    public Slice<VideoBoard> getMySuccessPosts(Long memberId, Long storeId, Pageable pageable) {

        List<VideoBoard> results = query.selectFrom(videoBoard)
                .join(videoBoard.member, member)
                .where(
                        // no-offset 페이징 처리
                        checkStoreId(storeId),
                        // 유저 검색
                        videoBoard.member.id.eq(memberId)
                )
                .orderBy(videoBoard.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, results);
    }

    // no-offset 방식 처리하는 메서드 (storeId가 없을 경우, 있을 경우)
    private BooleanExpression checkStoreId(Long storeId) {
        if (storeId == null) {
            return null;
        }

        return videoBoard.id.lt(storeId);
    }

    // 암장 검색을 처리하는 메서드
    private BooleanExpression checkGymName(String gymNameCond) {
        if (gymNameCond.equals("")) {
            return null;
        }
        return videoBoard.video.gymName.eq(gymNameCond);
    }

    // 레벨 검색을 처리하는 메서드
    private BooleanExpression checkColor(String colorCond) {
        if (colorCond.equals("")) {
            return null;
        }
        return videoBoard.video.color.eq(colorCond);
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
