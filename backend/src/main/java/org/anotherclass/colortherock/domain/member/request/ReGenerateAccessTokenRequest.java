package org.anotherclass.colortherock.domain.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReGenerateAccessTokenRequest {

    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;

}
