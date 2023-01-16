package org.anotherclass.colortherock.domain.videoboard.entity;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video_board")
public class VideoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 30)
    private String title;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "videoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoComment> videoComments = new ArrayList<>();

    public List<VideoComment> getVideoComments() {
        return videoComments;
    }

    public void setVideoComments(List<VideoComment> videoComments) {
        this.videoComments = videoComments;
    }
}