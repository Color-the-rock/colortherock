package org.anotherclass.colortherock.global.security.jwt;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Getter
    @Id
    private final String refreshTokenKey;
    private final String accessTokenValue;

    public RefreshToken(final String refreshTokenKey, final String accessTokenValue) {
        this.refreshTokenKey = refreshTokenKey;
        this.accessTokenValue = accessTokenValue;
    }

    public String getAccessTokenValue() {
        return accessTokenValue;
    }
}