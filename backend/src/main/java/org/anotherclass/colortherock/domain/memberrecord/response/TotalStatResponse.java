package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TotalStatResponse {
    Integer videoCount;
    Integer videoLengthSum;
    Integer successCount;
}
