package org.anotherclass.colortherock.domain.memberrecord.entity;

import org.anotherclass.colortherock.domain.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;

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
    private Integer videoLengthSum;

    @Column(name = "success_count")
    private Integer successCount;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addVideoCount() {
        this.videoCount += 1;
    }

    public void addSuccessCount() {
        this.successCount += 1;
    }

    public void subVideoCount() {
        this.videoCount -= 1;
    }

    public void subSuccessCount() {
        this.successCount -= 1;
    }
}
