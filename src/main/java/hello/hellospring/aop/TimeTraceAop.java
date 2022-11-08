package hello.hellospring.aop;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // 콘피그에서 빈으로 등록해주는 걸 선호하지만, @Component로 빈 등록도 가능한 방법
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 패키지내 모든 메소드
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }


    }
}
