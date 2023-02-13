package org.anotherclass.colortherock.domain.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReGenerateAccessTokenResponse {

    @Schema(description = "액세스 토큰 ")
    private String accessToken;
    public ReGenerateAccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
