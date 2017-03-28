package cn;

/**
 * 多态示例代码
 * 
 * @author Administrator
 * 
 */

public class PolyTest {
	public static void main(String[] args) {
		// 向上类型转换
		Cat cat = new Cat();
		Animal animal = cat;
		animal.sing();

		// 向下类型转换
		Animal a = new Cat();
		Cat c = (Cat) a;
		c.sing();
		c.eat();

		// 编译错误
		// 用父类引用调用父类不存在的方法
		// Animal a1 = new Cat();
		// a1.eat();

		// 编译错误
		// 向下类型转换时只能转向指向的对象类型
		// Animal a2 = new Cat();
		// Cat c2 = (Dog)a2;

	}
}

class Animal {
	public void sing() {
		System.out.println("Animal is singing!");
	}
}

class Dog extends Animal {
	public void sing() {
		System.out.println("Dog is singing!");
	}
}

class Cat extends Animal {
	public void sing() {
		System.out.println("Cat is singing!");
	}

	public void eat() {
		System.out.println("Cat is eating!");
	}
}
