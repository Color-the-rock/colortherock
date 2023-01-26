package org.anotherclass.colortherock.domain.videoboard.entity;

import lombok.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video_board")
public class VideoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 30)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "videoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoComment> videoComments = new ArrayList<>();

    @Column(name = "written_time")
    private LocalDateTime writtenTime;

    @Builder
    public VideoBoard(String title, Video video, Member member, LocalDateTime writtenTime) {
        this.title = title;
        this.video = video;
        this.member = member;
        this.writtenTime = writtenTime;
    }

    public void update(String title, LocalDateTime writtenTime) {
        this.title = title;
        this.writtenTime = writtenTime;
    }

}