package org.anotherclass.colortherock.domain.videocomment.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.global.common.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video_comment")
public class VideoComment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_board_id")
    private VideoBoard videoBoard;

    @Builder
    public VideoComment(String content, Member member, VideoBoard videoBoard) {
        this.content = content;
        this.member = member;
        this.videoBoard = videoBoard;
    }

    public void update(String content) {
        this.content = content;
    }
}