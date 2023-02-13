package org.anotherclass.colortherock.global.log;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * 매 요청 마다 생성 됌
 */
@Component
@RequestScope
@Getter
public class ApiQueryCounter {

    private int count;

    /**
     * 쿼리가 수행 되면 1 증가 됌
     */
    public void increaseCount() {
        count++;
    }
}