package org.anotherclass.colortherock.domain.video.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class S3ServiceTest {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    @Autowired
    private S3Service s3Service;

    @Test @DisplayName("S3 영상 업로드")
    void upload() throws IOException {
        // given
        final byte[] content = "hello world".getBytes();
        MockMultipartFile mockFile = new MockMultipartFile("content", "test.jpg", "mutipart/mixed", content);
        // when
        String s3URL = s3Service.upload(mockFile, mockFile.getOriginalFilename());
        // then
        Assertions.assertNotNull(s3URL);
    }

    @Test @DisplayName("S3 영상 삭제")
    void deleteS3Video() {
        assertDoesNotThrow(() ->
            s3Service.deleteFile("test.jpg")
        );
    }
}