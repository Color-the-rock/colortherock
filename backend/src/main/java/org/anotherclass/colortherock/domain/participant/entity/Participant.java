package org.anotherclass.colortherock.domain.participant.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "live_id")
    private Live live;

    @Builder
    public Participant(Long id, Member member, Live live) {
        this.id = id;
        this.member = member;
        this.live = live;
    }
}
