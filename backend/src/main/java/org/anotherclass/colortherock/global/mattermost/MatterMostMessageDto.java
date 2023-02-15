package org.anotherclass.colortherock.global.mattermost;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MatterMostMessageDto {

        private Props props;
        private List<Attachment> attachments;
        public MatterMostMessageDto() {
            attachments = new ArrayList<>();
        }

        public MatterMostMessageDto(Attachment attachment) {
            this();
            this.attachments.add(attachment);
        }

        public void addProps(Exception e) {
            props = new Props(e);
        }

    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Attachment {

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
            this.title = "## :warning: 신고 5회 누적 게시물 발생 :warning:" + '\n' + '\n';

            this.text = text + "**게시물 제목**" + '\n' + '\n' + title + '\n' + '\n';
        }

        public void addReportInfo(String title, Long id) {
            this.addReportInfo(title);

            this.text = text + "**videoBoardId**" + '\n' + '\n' + id + "번 videoBoard" + '\n' + '\n'
                    + "[**[🛠 관리자 페이지로 이동하기]**](https://colortherock.com/admin)";
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