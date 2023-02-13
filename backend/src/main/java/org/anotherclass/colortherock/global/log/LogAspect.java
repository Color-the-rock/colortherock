package org.anotherclass.colortherock.global.log;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 실행 시간을 측정하는 Aspect
 *
 */
@Aspect
@Slf4j
@Component
@Order(1)
public class LogAspect {

    /**
     * 조인 포인트 주변에 시간 측정 코드를 넣어서 메소드가 수행되는 시간을 측정한다.
     * @param proceedingJoinPoint 적용된 조인포인트
     * @return 원래 결과
     */
    @Around("execution(* org.anotherclass.colortherock.domain..*(..)))")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        final StopWatch stopWatch = new StopWatch();

        //calculate method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution time of "
                + methodSignature.getDeclaringType().getSimpleName() // Class Name
                + "." + methodSignature.getName() + " " // Method Name
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

}
