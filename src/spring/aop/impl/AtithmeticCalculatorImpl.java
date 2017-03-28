package spring.aop.impl;

import org.springframework.stereotype.Repository;
import spring.aop.AtithmeticCalculator;
/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author 姓名 工号
 * @version [版本号, 2015-11-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository
public class AtithmeticCalculatorImpl implements AtithmeticCalculator {

    /**
     * 重载方法
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public int add(int i, int j) {
        int result = i + j;
        return result;
    }

    /**
     * 重载方法
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public int sub(int i, int j) {
        int result = i - j;
        return result;
    }

    /**
     * 重载方法
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public int mul(int i, int j) {
        int result = i * j;
        return result;
    }

    /**
     * 重载方法
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public int div(int i, int j) {
        int result = 0;
        try {
            //前置通知  
            result = i / j;
            //返回通知  
        } catch (Exception e) {
            e.printStackTrace();
            //异常通知，可以访问到出现的异常  
        }

        //后置通知，因为方法可能会出现异常，所以访问不到方法的返回值  

        return result;
    }

}  