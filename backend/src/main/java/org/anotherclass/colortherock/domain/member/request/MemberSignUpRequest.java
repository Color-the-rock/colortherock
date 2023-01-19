package org.anotherclass.colortherock.domain.member.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpRequest {

    @Email
    private String email;
    @NotNull
    private RegistrationId registrationId;

    @NotBlank
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .nickname(this.nickname)
                .registrationId(this.registrationId)
                .email(this.email).build();
    }
}
