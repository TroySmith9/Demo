package designParttern.interceptor;

public class InterceptorClass {
	public void before() {
		System.out.println("拦截器InterceptorClass方法调用:before()!");
	}

	public void after() {
		System.out.println("拦截器InterceptorClass方法调用:after()!");
	}
}