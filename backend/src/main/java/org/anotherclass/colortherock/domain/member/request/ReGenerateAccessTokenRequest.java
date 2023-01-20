package org.anotherclass.colortherock.domain.member.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "Refresh토큰으로 Access 토큰을 재발급 받음")
public class ReGenerateAccessTokenRequest {

    @NotBlank
    @Parameter(description = "액세스 토큰", required = true)
    private String accessToken;
    @NotBlank
    @Parameter(description = "리프레시 토큰", required = true)
    private String refreshToken;

}
