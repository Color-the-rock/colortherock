package org.anotherclass.colortherock.global.redis;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;


    public RefreshToken save(final RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getAccessToken());
        redisTemplate.expire(refreshToken.getRefreshToken(), 60L, TimeUnit.SECONDS);
        return refreshToken;
    }

    public Optional<RefreshToken> findById(final String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String accessToken = valueOperations.get(refreshToken);

        if (Objects.isNull(accessToken)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(refreshToken, accessToken));
    }

    public void update(String refreshToken, String accessToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Long expire = redisTemplate.getExpire(refreshToken);
        if (expire != null) {
            valueOperations.set(refreshToken, accessToken, expire, TimeUnit.SECONDS);
        }
    }
}
