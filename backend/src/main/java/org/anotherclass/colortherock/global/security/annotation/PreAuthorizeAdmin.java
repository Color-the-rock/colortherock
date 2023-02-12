package org.anotherclass.colortherock.global.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_ADMIN')")
public @interface PreAuthorizeAdmin {
}
