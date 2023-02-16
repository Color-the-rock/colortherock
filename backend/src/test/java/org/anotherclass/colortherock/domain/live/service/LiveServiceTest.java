package org.anotherclass.colortherock.domain.live.service;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import org.anotherclass.colortherock.domain.live.repository.LiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class LiveServiceTest {
    @Autowired
    private LiveService liveService;
    private OpenVidu openVidu;
    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;
    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;
    @Autowired
    private LiveRepository liveRepository;

    @Test
    @DisplayName("세션 삭제 요청")
    void 세션삭제_성공() throws OpenViduJavaClientException, OpenViduHttpException {
        // given
        openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
        Session session = openVidu.createSession();
        String sessionId = session.getSessionId();
        Session activeSession = openVidu.getActiveSession(sessionId);
        // when
        liveService.removeSession(sessionId);
        // then
        Assertions.assertFalse(liveRepository.findBySessionId(sessionId).isPresent());
    }
}