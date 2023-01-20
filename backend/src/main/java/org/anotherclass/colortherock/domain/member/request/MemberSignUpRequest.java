package org.anotherclass.colortherock.domain.member.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "멤버 회원가입 API 요청")
public class MemberSignUpRequest {

    @Email
    @Schema(description = "이메일", required = true)
    private String email;
    @NotNull
    @Schema(description = "가입경로", required = true)
    private RegistrationId registrationId;

    @NotBlank
    @Schema(description = "닉네임", required = true)
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .nickname(this.nickname)
                .registrationId(this.registrationId)
                .email(this.email).build();
    }
}
