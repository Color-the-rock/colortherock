package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Schema(description = "레벨별 통계 응답 객체")
public class LevelStatResponse {
    @Schema(description = "레벨")
    int level;
    @Schema(description = "총합")
    long total;
    @Schema(description = "성공 개수")
    long success;

    public LevelStatResponse(int level) {
        this.level = level;
    }

    public void totalIncrement() {
        this.total += 1;
    }

    public void successIncrement() {
        this.success += 1;
    }
}
