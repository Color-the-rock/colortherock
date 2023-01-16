package org.anotherclass.colortherock.domain.memberrecord.entity;

import org.anotherclass.colortherock.domain.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class MemberRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "video_count")
    private Integer videoCount;

    @Column(name = "video_length_sum")
    private LocalTime videoLengthSum;

    @Column(name = "success_count")
    private Integer successCount;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private Member member;

}
