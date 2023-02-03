package org.anotherclass.colortherock.test;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokensResponse {
    private String nickname;
    private String token;

    @Builder
    public TokensResponse(String nickname, String token) {
        this.nickname = nickname;
        this.token = token;
    }
}
