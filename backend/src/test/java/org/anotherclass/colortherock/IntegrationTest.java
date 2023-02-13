package org.anotherclass.colortherock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class IntegrationTest {
    @Autowired
    public ObjectMapper objectMapper;
    public static final String TOKEN_PREFIX = "Bearer ";

    public <T> BaseResponse<T> convertToBaseResponse(MockHttpServletResponse response) throws UnsupportedEncodingException, JsonProcessingException {

        return objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
    }

}
