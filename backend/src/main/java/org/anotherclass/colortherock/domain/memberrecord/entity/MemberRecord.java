package org.anotherclass.colortherock.domain.memberrecord.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemberRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "video_count")
    private Integer videoCount;

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

    public MemberRecord(Member member) {
        this.member = member;
        this.videoCount = 0;
        this.successCount = 0;
    }
    @Builder
    public MemberRecord(Long id, Integer videoCount, Integer successCount, Member member) {
        this.id = id;
        this.videoCount = videoCount;
        this.successCount = successCount;
        this.member = member;
    }
}
