package org.anotherclass.colortherock.global.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 메서드에 붙어있으면 관리자 권한을 인증해야 한다.
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public @interface PreAuthorizeAdmin {
}
