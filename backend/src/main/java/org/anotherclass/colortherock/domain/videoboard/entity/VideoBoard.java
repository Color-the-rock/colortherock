package org.anotherclass.colortherock.domain.videoboard.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.report.entity.Report;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.global.common.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video_board")
public class VideoBoard extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 30)
    private String title;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "videoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoComment> videoComments = new ArrayList<>();

    @OneToMany(mappedBy = "videoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @Builder
    public VideoBoard(String title, Boolean isHidden, Video video, Member member) {
        this.isHidden = isHidden;
        this.title = title;
        this.video = video;
        this.member = member;
    }

    public void update(String title) {
        this.title = title;
    }

    public void changeToHidden() {
        this.isHidden = true;
    }
    public void changeToPublic() { this.isHidden = false; }

}