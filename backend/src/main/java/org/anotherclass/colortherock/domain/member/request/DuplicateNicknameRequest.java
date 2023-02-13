package org.anotherclass.colortherock.domain.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DuplicateNicknameRequest {
    @Pattern(regexp = "^\\S*$", message = "닉네임에 공백이 있으면 안됩니다.")
    @Pattern(regexp = "^[A-Za-z0-9가-힣]{2,16}$", message = "닉네임은 2글자 이상 16자 이하입니다.")
    @Schema(description = "닉네임")
    String nickname;
}
