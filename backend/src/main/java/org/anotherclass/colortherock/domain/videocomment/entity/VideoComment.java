package org.anotherclass.colortherock.domain.videocomment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "video_comment")
public class VideoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", length = 500)
    private String content;

    @Column(name = "written_time")
    private LocalDateTime writtenTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "video_board_id")
    private VideoBoard videoBoard;

}