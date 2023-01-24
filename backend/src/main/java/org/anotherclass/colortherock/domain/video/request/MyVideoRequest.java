package org.anotherclass.colortherock.domain.video.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.anotherclass.colortherock.domain.member.entity.Member;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class MyVideoRequest {
    private Long videoId;
    private LocalDate shootingDate;
    private Member member;
    private boolean isSuccess;
}
