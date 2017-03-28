package cn;

public class HummingbirdFactory {

	private static int index = 0;

	public Hummingbird createBird() {
		index++;
		return new Hummingbird(index);
	}
}
