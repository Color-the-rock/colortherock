package org.anotherclass.colortherock.global.security.oauth2;

import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

public class GoogleMemberInfo implements MemberInfo {
    private final String email;

    public GoogleMemberInfo(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public RegistrationId getRegistrationId() {
        return RegistrationId.google;
    }
}
