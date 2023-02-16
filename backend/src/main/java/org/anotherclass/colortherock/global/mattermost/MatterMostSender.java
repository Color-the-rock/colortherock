package org.anotherclass.colortherock.global.mattermost;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.global.mattermost.MatterMostMessageDto.Attachment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
@Slf4j
@Profile("prod")
public class MatterMostSender {

    @Value("${notification.mattermost.enabled}")
    private boolean mmEnabled;
    @Value("${notification.mattermost.webhook-url}")
    private String webhookUrl;
    @Value("${report.mattermost.webhook-url}")
    private String reportUrl;

    private final RestTemplate restTemplate;
    private final MattermostProperties mmProperties;

    /**
     * Mattermost 예외를 채널로 전송 한다.
     * @param exception 발생한 예외
     * @param uri 요청한 uri
     * @param params 변수
     */

    public void sendExceptionMessage(Exception exception, String uri, String params) {
        if (!mmEnabled)
            return;

        try {
            Attachment attachment = Attachment.builder()
                    .authorIcon(mmProperties.getAuthorIcon())
                    .authorName(mmProperties.getAuthorName())
                    .color(mmProperties.getColor())
                    .pretext(mmProperties.getPretext())
                    .title(mmProperties.getTitle())
                    .text(mmProperties.getText())
                    .footer(mmProperties.getFooter())
                    .build();

            attachment.addExceptionInfo(exception, uri, params);
            MatterMostMessageDto dto = new MatterMostMessageDto(attachment);
            dto.addProps(exception);
            String payload = new Gson().toJson(dto);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> entity = new HttpEntity<>(payload, headers);
            restTemplate.postForEntity(webhookUrl, entity, String.class);

        } catch (Exception e) {
            log.error("#### ERROR!! Notification Manager : {}", e.getMessage());
        }

    }

    /**
     * 신고가 누적 되면 Mattermost로 전송 한다.
     * @param title 신고 당한 게시글 제목
     * @param id 게시글 id
     *
     */

    public void sendReportMessage(String title, Long id) {
        if (!mmEnabled)
            return;

        try {
            Attachment attachment = Attachment.builder()
                    .authorIcon(mmProperties.getAuthorIcon())
                    .authorName(mmProperties.getAuthorName())
                    .color(mmProperties.getColor())
                    .pretext(mmProperties.getPretext())
                    .title(mmProperties.getTitle())
                    .text(mmProperties.getText())
                    .footer(mmProperties.getFooter())
                    .build();

            attachment.addReportInfo(title, id);
            MatterMostMessageDto dto = new MatterMostMessageDto(attachment);
            String payload = new Gson().toJson(dto);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> entity = new HttpEntity<>(payload, headers);
            restTemplate.postForEntity(reportUrl, entity, String.class);

        } catch (Exception e) {
            log.error("#### ERROR!! Notification Manager : {}", e.getMessage());
        }

    }

}