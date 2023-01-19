package org.anotherclass.colortherock.global.security.oAuth2;

import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

public class KakaoMemberInfo implements MemberInfo {
    private final String email;

    public KakaoMemberInfo(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public RegistrationId getRegistrationId() {
        return RegistrationId.kakao;
    }
}
