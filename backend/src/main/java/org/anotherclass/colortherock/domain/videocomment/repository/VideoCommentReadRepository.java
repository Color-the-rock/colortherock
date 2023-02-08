package org.anotherclass.colortherock.domain.videocomment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.member.entity.QMember;
import org.anotherclass.colortherock.domain.videocomment.entity.QVideoComment;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoCommentReadRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public VideoCommentReadRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    QMember member = QMember.member;
    QVideoComment videoComment = QVideoComment.videoComment;

    public Slice<VideoComment> searchByCond(CommentListRequest condition, Pageable pageable) {
        Long lastStoreId = condition.getStoreId();
        Long videoBoardId = condition.getVideoBoardId();
        List<VideoComment> results = query.selectFrom(videoComment)
                .join(videoComment.member, member)
                .fetchJoin()
                .where(
                        checkStoreId(lastStoreId),
                        videoComment.videoBoard.id.eq(videoBoardId)
                )
                .orderBy(videoComment.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    public Slice<VideoComment> getMyComments(Long memberId, Long storeId, Pageable pageable) {
        List<VideoComment> results = query.selectFrom(videoComment)
                .join(videoComment.member, member)
                .fetchJoin()
                .where(
                        checkStoreId(storeId),
                        videoComment.member.id.eq(memberId)
                )
                .orderBy(videoComment.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);

    }

    // no-offset 방식 처리하는 메서드 (storeId가 없을 경우, 있을 경우)
    private BooleanExpression checkStoreId(Long storeId) {
        if (storeId == -1L) {
            return null;
        }
        return videoComment.id.lt(storeId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<VideoComment> checkLastPage(Pageable pageable, List<VideoComment> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
