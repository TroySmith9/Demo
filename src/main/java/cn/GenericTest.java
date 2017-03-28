package cn;

public class GenericTest {

	<T> void test(T a, int b) {

	}

	<T> T dotet(T a, int b) {

		return null;
	}
}

class GenericChild<T> {
	void test(T a, int b) {

	}

	T dotet(T a, int b) {

		return null;
	}

}