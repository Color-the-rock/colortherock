package org.anotherclass.colortherock.domain.member.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReGenerateAccessTokenResponse {
    private String accessToken;
    public ReGenerateAccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
