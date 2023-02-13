package org.anotherclass.colortherock.global.security.oauth2;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CookieUtils {

    /**
     * 요청에서 쿠키를 가져온다
     *
     * @param request Servlet 요청
     * @param name    가져올 쿠키 이름
     * @return 쿠키 값
     */
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * 응답에 쿠키를 추가한다.
     *
     * @param response 응답
     * @param name     이름
     * @param value    값
     * @param maxAge   지속기간
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 쿠리를 삭제하고 응답을 반환한다.
     *
     * @param request  쿠키를 가져올 request
     * @param response 삭제할 response
     * @param name     쿠키 이름
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * 쿠키를 Base64로 직렬화  한다.
     * @param object 직렬화 할 객체
     * @return 인코딩 값
     */
    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    /**
     * 역직렬화 메소드
     * @param cookie 쿠키
     * @param cls 역직렬화 할 클래스 타입
     */
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}