package org.anotherclass.colortherock.global.mattermost;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Getter
@Setter
@Profile("prod")
public class MattermostProperties {

    private String channel;
    private String pretext;
    private String color = "#ff5d52";
    private String authorName = "에러 봇";
    private String authorIcon;
    private String title;
    private String text = "";
    private String footer = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

}
