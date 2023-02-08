package org.anotherclass.colortherock.domain.live.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Schema(description = "라이브 방 생성 요청")
public class CreateLiveRequest {
    @NotNull
    @Schema(description = "방 공개 여부")
    private Boolean isPublic;
    @NotBlank
    @Schema(description = "암장 이름")
    private String gymName;
    @NotBlank
    @Schema(description = "라이브 방 제목")
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

