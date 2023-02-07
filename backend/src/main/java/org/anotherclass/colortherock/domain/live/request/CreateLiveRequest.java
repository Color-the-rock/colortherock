package org.anotherclass.colortherock.domain.live.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateLiveRequest {
    @NotNull
    private Boolean isPublic;
    @NotBlank
    private String gymName;
    @NotBlank
    private String title;

    @Builder
    public CreateLiveRequest(Boolean isPublic, String gymName, String title) {
        this.isPublic = isPublic;
        this.gymName = gymName;
        this.title = title;
    }

    public Live toEntity(String sessionId, Member member) {
        return Live.builder()
                .isLive(true)
                .sessionId(sessionId)
                .member(member)
                .gymName(this.gymName)
                .isPublic(this.isPublic)
                .title(this.title)
                .build();
    }
}

