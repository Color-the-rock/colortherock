package org.anotherclass.colortherock.domain.videocomment.entity;

import lombok.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video_comment")
public class VideoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", length = 500)
    private String content;

    @Column(name = "written_time")
    private LocalDateTime writtenTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_board_id")
    private VideoBoard videoBoard;

    @Builder
    public VideoComment(String content, LocalDateTime writtenTime, Member member, VideoBoard videoBoard) {
        this.content = content;
        this.writtenTime = writtenTime;
        this.member = member;
        this.videoBoard = videoBoard;
    }

    public void update(String content, LocalDateTime writtenTime) {
        this.content = content;
        this.writtenTime = writtenTime;
    }
}