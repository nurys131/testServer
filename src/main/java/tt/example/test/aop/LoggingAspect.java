package tt.example.test.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(tt.example.test.aop.Log)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void logAllMethodCallsBeforeMethod() {
        System.out.println("Before");
    }

    @After("logPointcut()")
    public void logAllMethodCallsAfterMethod() {
        System.out.println("After");
    }
}
