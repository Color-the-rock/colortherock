package org.anotherclass.colortherock.domain.video.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "내 성공 영상 리스트 요청")
public class MySuccessVideoRequest {

    @NotNull
    @Schema(description = "페이지네이션용 id")
    private Long storeId;

    @NotNull
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "찍은 날짜")
    private LocalDate shootingDate;

}
