package spring.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author 姓名 工号
 * @version [版本号, 2015-11-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
//把一个类声明为一个切面：需要把该类放入到IOC容器中，再声明为一个切面  
@Order(2)
@Aspect
@Component
public class LoggingAspect {
    //定义一个方法，用于声明切入点表达式，一般的  该方法中不需要再添加其他代码  
    @Pointcut("execution(public int spring.aop.impl.AtithmeticCalculatorImpl.*(..) )")
    public void declareJointPointExpression() {
    }


    //声明该方法是一个前置通知，在目标方法开始之前执行.第一个*表示任意修饰符  任意返回值，后边的*表示任意方法名  
    //@Before("execution(* spring.aop.impl.AtithmeticCalculatorImpl.*(int, int) )")  
    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("this is method " + methodName + " begin with " + args);
    }

    //后置通知：在目标方法执行后(无论是否发生异常)，执行的通知  
    //在后置通知中不能访问在目标方法中返回的执行结果  
    @After("execution(public int spring.aop.impl.AtithmeticCalculatorImpl.*(int, int) )")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this is method " + methodName + " end with ");
    }

    //在方法正常结束后要执行的代码  
    //返回通知是可以访问到方法的返回值的  
    @AfterReturning(value = "declareJointPointExpression()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName;
        methodName = joinPoint.getSignature().getName();
        System.out.println("this is method " + methodName + " after Returning " + result);
    }

    //在方法出现异常时，会执行的代码，而且可以访问到异常对象，且可以指定在出现  特定异常时在执行通知代码 Exception ex,NullPointerException ex  
    @AfterThrowing(value = "declareJointPointExpression())", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this is method " + methodName + " after Returning exception " + ex);
    }
}  