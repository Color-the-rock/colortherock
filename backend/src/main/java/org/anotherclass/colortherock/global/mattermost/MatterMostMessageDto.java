package org.anotherclass.colortherock.global.mattermost;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatterMostMessageDto {

    @Getter
    public static class Attachments {
        private Props props;
        private final List<Attachment> attachmentList;

        public Attachments() {
            attachmentList = new ArrayList<>();
        }

        public Attachments(Attachment attachment) {
            this();
            this.attachmentList.add(attachment);
        }

        public void addProps(Exception e) {
            props = new Props(e);
        }

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Attachment {
        private String channel;

        private String pretext;

        private String color;

        @SerializedName("author_name")
        private String authorName;

        @SerializedName("author_icon")
        private String authorIcon;

        private String title;

        private String text;

        private String footer;

        public void addExceptionInfo(Exception e) {
            this.title = e.getClass().getSimpleName();

            this.text = text + "**Error Message**" + '\n' + '\n' + "```" + e.getMessage() + "```" +
                    '\n' + '\n';
        }

        public void addExceptionInfo(Exception e, String uri) {
            this.addExceptionInfo(e);

            this.text = text + "**Reqeust URL**" + '\n' + '\n' + uri + '\n' + '\n';
        }

        public void addExceptionInfo(Exception e, String uri, String params) {
            this.addExceptionInfo(e, uri);

            this.text = text + "**Parameters**" + '\n' + '\n' + params + '\n' + '\n';
        }

        public void addReportInfo(String title) {
            this.title = "신고 누적 발생";

            this.text = text + "**title**" + '\n' + '\n' + title + '\n' + '\n';
        }

        public void addReportInfo(String title, Long id) {
            this.addReportInfo(title);

            this.text = text + "**videoBoardId**" + '\n' + '\n' + id + "번 videoBoard" + '\n' + '\n';
        }

    }

    @Getter
    @NoArgsConstructor
    public static class Props {
        private String card;

        public Props(Exception e) {
            StringBuilder text = new StringBuilder();

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            text.append("**Stack Trace**").append("\n").append('\n').append("```");
            text.append(sw).append("\n...").append('\n').append('\n');

            this.card = text.toString();
        }
    }

}