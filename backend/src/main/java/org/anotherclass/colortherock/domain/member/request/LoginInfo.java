package org.anotherclass.colortherock.domain.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(description = "관리자 계정 API 요청")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginInfo {

    @NotNull
    private String id;

    @NotNull
    private String password;

}
