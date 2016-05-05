package com.interceptor;

public class BusinessClass implements BusinessInterface {

	public void doSomething() {
		System.out.println("业务组件BusinessClass方法调用:doSomething()");
	}
}