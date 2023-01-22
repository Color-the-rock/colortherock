package org.anotherclass.colortherock.domain.openvidu.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

}