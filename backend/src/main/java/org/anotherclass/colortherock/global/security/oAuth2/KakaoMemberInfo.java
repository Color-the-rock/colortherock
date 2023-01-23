package org.anotherclass.colortherock.global.security.oAuth2;

import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

import java.util.Map;

public class KakaoMemberInfo implements MemberInfo {
    private final Map<String, Object> attributes;

    public KakaoMemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public RegistrationId getRegistrationId() {
        return RegistrationId.kakao;
    }
}
