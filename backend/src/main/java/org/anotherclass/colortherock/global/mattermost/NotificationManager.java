package org.anotherclass.colortherock.global.mattermost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("prod")
public class NotificationManager {

    private final MatterMostSender mmSender;

    public void sendNotification(Exception e, String uri, String params) {
        log.info("#### SEND error Notification");
        mmSender.sendMessage(e, uri, params);
    }

    public void sendNotification(VideoBoard videoBoard) {
        log.info("#### SEND report Notification");
        String title = videoBoard.getTitle();
        Long id = videoBoard.getId();
        mmSender.sendMessage(title, id);
    }

}
