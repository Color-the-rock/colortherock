package org.anotherclass.colortherock.domain.video.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "shooting_date")
    private LocalDate shootingDate;

    @Column(name = "level")
    private Integer level;

    @Column(name = "gym_name", length = 30)
    private String gymName;

    @Column(name = "s_3_url", length = 100)
    private String s3URL;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Column(name = "thumbnail_url", length = 100)
    private String thumbnailURL;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "video_name", length = 100)
    private String videoName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToOne(mappedBy = "video", orphanRemoval = true, fetch = FetchType.LAZY)
    private VideoBoard videoBoard;

}