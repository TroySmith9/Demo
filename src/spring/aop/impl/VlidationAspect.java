package spring.aop.impl;

import java.util.Arrays;  
  
import org.aspectj.lang.JoinPoint;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.springframework.core.annotation.Order;  
import org.springframework.stereotype.Component;  
  
/** 
 * <一句话功能简述> 
 * <功能详细描述> 
 *  
 * @author  姓名 工号 
 * @version  [版本号, 2015-11-2] 
 * @see  [相关类/方法] 
 * @since  [产品/模块版本] 
 */  
//@Order(1)设定切面的优先级  值越小  优先级越高  
@Order(1)  
@Aspect  
@Component  
public class VlidationAspect  
{  
    //不同的包下要加包名   com.cgc.spring.aop.impl.LoggingAspect.declareJointPointExpression()  
    @Before("spring.aop.impl.LoggingAspect.declareJointPointExpression()")  
    public void validateArrgs(JoinPoint joinPoint)  
    {  
        System.out.println("-->validate"+Arrays.asList(joinPoint.getArgs()));  
        //可以在此处实现请求的参数是不是int类型，从joinPoint中可以获取请求的参数  
    }  
}  