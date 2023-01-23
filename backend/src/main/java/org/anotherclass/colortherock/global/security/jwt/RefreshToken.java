package org.anotherclass.colortherock.global.security.jwt;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private final String refreshToken;
    private final String accessToken;

    public RefreshToken(final String refreshToken, final String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}