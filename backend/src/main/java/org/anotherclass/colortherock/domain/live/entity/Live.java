package org.anotherclass.colortherock.domain.live.entity;

import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.persistence.*;

@Entity
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

    @Column(name = "level")
    private Integer level;

    @Column(name = "session_id", length = 100)
    private String sessionId;

    @Column(name = "is_live")
    private Boolean isLive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
