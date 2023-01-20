package org.anotherclass.colortherock.domain.member.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "멤버 회원가입 API 요청")
@Getter
public class MemberSignUpRequest {

    @Email(message = "이메일을 필수 값입니다.")
    @Schema(description = "이메일", required = true)
    private String email;
    @NotNull(message = "가입경로는 필수 값입니다.")
    @Schema(description = "가입경로", required = true)
    private RegistrationId registrationId;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Schema(description = "닉네임", required = true)
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .nickname(this.nickname)
                .registrationId(this.registrationId)
                .email(this.email).build();
    }
}
