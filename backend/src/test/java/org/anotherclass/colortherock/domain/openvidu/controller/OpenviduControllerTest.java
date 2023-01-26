package org.anotherclass.colortherock.domain.openvidu.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class OpenviduControllerTest extends IntegrationTest {


    String url = "http://localhost:8080";

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("세션 만들기 테스트")
    public void 세션_만들기() throws Exception {
        url += "/api/sessions";
        MvcResult mvcResult = mockMvc.perform(post(url
                ))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String sessionId = mvcResult.getResponse().getContentAsString();
        System.out.println("sessionId = " + sessionId);
        Assertions.assertNotNull(sessionId);
    }

    @Test
    @DisplayName("세션 만들고 개수가 1이상 이어야 함")
    public void 세션_결과_테스트() throws Exception {
        url += "/api/sessions";
        mockMvc.perform(post(url
                ))
                .andExpect(status().isOk());

        mockMvc.perform(get(url
                ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.notNullValue()));


    }
}