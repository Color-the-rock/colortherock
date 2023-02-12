package org.anotherclass.colortherock.global.security.oauth2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoFactory {


    public static MemberInfo getMemberInfo(Map<String, Object> attributes, OAuth2AuthenticationToken authentication) {
        String email = (String) attributes.get("email");
        String registrationId = authentication.getAuthorizedClientRegistrationId();

        if (registrationId.equals("google")) {
            return new GoogleMemberInfo(email);
        } else if (registrationId.equals("kakao")) {
            return new KakaoMemberInfo(attributes);
        }
        throw new UnsupportedInfoException(GlobalErrorCode.UNSUPPORTED_INFO);
    }

}
