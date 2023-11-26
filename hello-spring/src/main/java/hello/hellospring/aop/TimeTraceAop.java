package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Aspect
@Component//어노테이션을 사용하는것보다 SpringConfig 에서 직접 bean으로 지정해 사용하는중임을 확실하게 하는게 좋다 (가독의 문제)
public class TimeTraceAop {
    //  @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    //Bean을 등록하는 방법으로 할때 순환참조 오류를 해결하기위해 어노테이션 옵션 부에 조건을 추가한다.
    //https://www.inflearn.com/questions/48156

    @Around("execution(* hello.hellospring..*(..))")//패키지 하위에는 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START:  " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
