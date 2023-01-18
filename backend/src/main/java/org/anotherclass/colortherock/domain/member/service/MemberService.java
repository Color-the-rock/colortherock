package org.anotherclass.colortherock.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.exception.AccessDeniedException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MemberService {
    private final JwtTokenUtils jwtTokenUtils;

    public String regenerateAccessToken(String refreshToken) {
        Optional<RefreshToken> findToken = jwtTokenUtils.isValidRefreshToken(refreshToken);
        RefreshToken findRefreshToken = findToken.orElseThrow(() -> new AccessDeniedException(GlobalErrorCode.ACCESS_DENIED));
        return jwtTokenUtils.reCreateTokens(findRefreshToken);

    }
}
