package org.anotherclass.colortherock.domain.member.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.request.LoginInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AdminControllerTest extends IntegrationTest {

    @Autowired
    MockMvc mockMvc;
    String url = "http://localhost:8080/api/login/admin";

    @Value("${spring.security.user.name}")
    private String adminId;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Test
    @DisplayName("관리자 로그인 성공")
    public void adminLogin() throws Exception {
        LoginInfo loginInfo = new LoginInfo(adminId, adminPassword);
        mockMvc.perform(
                        MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(loginInfo))
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));
    }

    @Test
    @DisplayName("관리자 로그인 실패")
    public void adminLoginFail() throws Exception {
        LoginInfo loginInfo = new LoginInfo(adminId, "1234");

        mockMvc.perform(
                        MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(loginInfo))
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(401)));
    }
}