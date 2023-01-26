package org.anotherclass.colortherock.domain.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpResponse {
    private Long id;

    private String email;
    private RegistrationId registrationId;
    private String nickname;
}
