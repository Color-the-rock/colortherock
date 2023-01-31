package org.anotherclass.colortherock.domain.live.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
@Builder
public class CreateLiveRequest {

    @NotNull
    private final Boolean isPublic;
    @NotBlank
    private final String gymName;
    @NotBlank
    private final String title;

    public Live toEntity(String sessionId, String thumbnailURL, Member member) {
        return Live.builder()
                .isLive(true)
                .sessionId(sessionId)
                .thumbnailURL(thumbnailURL)
                .member(member)
                .gymName(this.gymName)
                .isPublic(this.isPublic)
                .title(this.title)
                .build();
    }
}

