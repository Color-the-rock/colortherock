package org.anotherclass.colortherock.domain.memberrecord.entity;

import org.anotherclass.colortherock.domain.member.entity.Member;
import lombok.Getter;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatDTO;

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

    public TotalStatDTO toTotalDTO() {
        return new TotalStatDTO(this.videoCount, this.videoLengthSum, this.successCount);
    }
}
