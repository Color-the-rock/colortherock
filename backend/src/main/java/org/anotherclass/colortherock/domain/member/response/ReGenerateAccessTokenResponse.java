package org.anotherclass.colortherock.domain.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReGenerateAccessTokenResponse {

    @Schema(description = "닉네임")
    private String accessToken;
    public ReGenerateAccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
