package org.anotherclass.colortherock.global.security.oAuth2;

import java.util.Map;

public class MemberInfoFactory {


    public static MemberInfo getMemberInfo(Map<String, Object> attributes) {
        String registrationId = (String) attributes.get("registrationId");
        String email = (String) attributes.get("email");

        if (registrationId.equals("google")) {
            return new GoogleMemberInfo(email);
        } else if (registrationId.equals("kakao")) {
            return new KakaoMemberInfo(email);
        }
        throw new RuntimeException();
    }

}
