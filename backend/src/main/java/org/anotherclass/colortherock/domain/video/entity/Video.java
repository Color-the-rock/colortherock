package org.anotherclass.colortherock.domain.video.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.global.common.BaseTime;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video")
public class Video extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "shooting_date")
    private LocalDate shootingDate;

    @Column(name = "level")
    private Integer level;

    @Column(name = "gym_name", length = 30)
    private String gymName;

    @Column(name = "s_3_url", length = 200)
    private String s3URL;
    @Column(name = "video_name", length = 200)
    private String videoName;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Column(name = "thumbnail_url", length = 200)
    private String thumbnailURL;

    @Column(name = "thumbnail_name", length = 200)
    private String thumbnailName;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "is_posted")
    @ColumnDefault("FALSE")
    private Boolean isPosted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "video", orphanRemoval = true)
    private VideoBoard videoBoard;

    @Builder
    public Video(LocalDate shootingDate, Integer level, String gymName, String s3URL, Boolean isSuccess, String thumbnailURL, String thumbnailName, String color, Member member, String videoName, Boolean isPosted) {
        this.shootingDate = shootingDate;
        this.level = level;
        this.gymName = gymName;
        this.s3URL = s3URL;
        this.isSuccess = isSuccess;
        this.thumbnailURL= thumbnailURL;
        this.thumbnailName = thumbnailName;
        this.color = color;
        this.member = member;
        this.videoName = videoName;
        this.isPosted = isPosted;
    }

    public void videoPosted() {this.isPosted = true;}
    public void postDeleted() {this.isPosted = false;}

    public void update(Integer level, String gymName, String color) {
        this.level = level;
        this.gymName = gymName;
        this.color = color;
    }
}