package org.anotherclass.colortherock.global.mattermost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Mattermost 전송을 관리 하는 빈
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Profile("prod")
public class NotificationManager {

    private final MatterMostSender mmSender;

    /**
     * Mattermost 예외를 채널로 전송 한다.
     * @param e 발생한 예외
     * @param uri 요청한 uri
     * @param params 변수
     */
    public void sendNotification(Exception e, String uri, String params) {
        log.info("#### SEND error Notification");
        mmSender.sendExceptionMessage(e, uri, params);
    }

    /**
     * 신고가 누적 되면 Mattermost로 전송 한다.
     * @param videoBoard 신고 당한 게시물
     *
     */
    public void sendNotification(VideoBoard videoBoard) {
        log.info("#### SEND report Notification");
        String title = videoBoard.getTitle();
        Long id = videoBoard.getId();
        mmSender.sendReportMessage(title, id);
    }

}
