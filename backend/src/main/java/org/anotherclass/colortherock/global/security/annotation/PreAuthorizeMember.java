package org.anotherclass.colortherock.global.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * 메서드에 붙어있으면 사용자 권한을 인증해야 한다.
 */
@PreAuthorize("hasRole('ROLE_MEMBER')")
public @interface PreAuthorizeMember {
}
