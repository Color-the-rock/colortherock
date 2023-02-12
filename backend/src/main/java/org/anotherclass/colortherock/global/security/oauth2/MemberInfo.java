package org.anotherclass.colortherock.global.security.oauth2;

import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

public interface MemberInfo {

    String getEmail();

    RegistrationId getRegistrationId();
}
