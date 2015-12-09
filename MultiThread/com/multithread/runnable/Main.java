package com.multithread.runnable;

public class Main {

	public static void main(String[] args) {
//
//		Thread2 my2 = new Thread2();
//		new Thread(my2, "C").start();// 同一个mt，但是在Thread中就不可以，如果用同一个实例化对象mt，就会出现异常
//		new Thread(my2, "D").start();
//		new Thread(my2, "E").start();
		
		Thread1 my1 = new Thread1();
		new Thread(my1, "A").start();
		
	}
}

class Thread2 implements Runnable {
	private int count = 15;

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + "运行  count= "
					+ count--);
			try {
				Thread.sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

class Thread1 extends Thread {
	private int count = 15;

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + "运行  count= "
					+ count--);
			try {
				Thread.sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}