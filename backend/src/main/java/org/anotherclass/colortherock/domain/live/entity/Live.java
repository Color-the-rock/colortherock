package org.anotherclass.colortherock.domain.live.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Live {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "gym_name", length = 50)
    private String gymName;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "thumbnail_url", length = 100)
    private String thumbnailURL;

    @Column(name = "session_id", length = 100)
    private String sessionId;

    @Column(name = "is_live")
    private Boolean isLive;

    // Many : 1 관계 에서는 지연 로딩을 사용 하자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Live(Boolean isPublic, String gymName, String title, String thumbnailURL, String sessionId, Boolean isLive, Member member) {
        this.isPublic = isPublic;
        this.gymName = gymName;
        this.title = title;
        this.thumbnailURL = thumbnailURL;
        this.sessionId = sessionId;
        this.isLive = isLive;
        this.member = member;
    }


}
