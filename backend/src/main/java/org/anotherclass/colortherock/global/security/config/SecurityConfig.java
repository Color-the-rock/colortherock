package org.anotherclass.colortherock.global.security.config;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.service.MemberDetailsServiceImpl;
import org.anotherclass.colortherock.global.security.jwt.JwtAuthenticationProvider;
import org.anotherclass.colortherock.global.security.jwt.JwtAuthorizeFilter;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.oAuth2.OAuth2AuthenticationFailureHandler;
import org.anotherclass.colortherock.global.security.oAuth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    //    private final CustomOAuthUserService oAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final MemberDetailsServiceImpl memberDetailsService;
    private final JwtTokenUtils jwtTokenUtils;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(jwtAuthenticationProvider);
        http.cors().configurationSource(corsConfigurationSource());
        http.userDetailsService(memberDetailsService);
        http.formLogin().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new JwtAuthorizeFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtTokenUtils), BasicAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/test")
                .authenticated();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login/test").authenticated()
                .anyRequest().permitAll();
        http.oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint().and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        http.httpBasic().disable();

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
