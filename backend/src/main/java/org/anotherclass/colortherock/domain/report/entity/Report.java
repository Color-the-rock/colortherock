package org.anotherclass.colortherock.domain.report.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_board_id")
    private VideoBoard videoBoard;

    @Builder
    public Report(String categoryName, Member member, VideoBoard videoBoard) {
        this.category = Category.valueOf(categoryName);
        this.member = member;
        this.videoBoard = videoBoard;
    }

    public enum Category {
        TYPE_A("성공영상 아님"), TYPE_B("부적절한 영상"), TYPE_C("부적절한 제목");
        private final String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}