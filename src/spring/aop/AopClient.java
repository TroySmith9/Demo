package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yiban on 2017/3/27.
 */
public class AopClient {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-aop-annotation.xml");
        AtithmeticCalculator cal = ac.getBean(AtithmeticCalculator.class);
//        System.out.println(cal.getClass().getName());
        System.out.println(" ************************ ");
        int result = cal.add(3, 4);
        System.out.println(result);
        int result00 = cal.div(10, 2);
        System.out.println(result00);
    }
}
