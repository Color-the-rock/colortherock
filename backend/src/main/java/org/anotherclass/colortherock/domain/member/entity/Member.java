package org.anotherclass.colortherock.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.report.entity.Report;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Live> lives = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoBoard> videoBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoComment> videoComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Member(Long id,String email, String nickname, RegistrationId registrationId) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.registrationId = registrationId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_id")
    private RegistrationId registrationId;

    public Member(String email, String nickname, RegistrationId registrationId) {
        this.email = email;
        this.nickname = nickname;
        this.registrationId = registrationId;
    }

    public enum RegistrationId {
        KAKAO, GOOGLE

    }

}