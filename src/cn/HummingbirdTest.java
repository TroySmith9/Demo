package cn;

import java.util.ArrayList;
import java.util.List;

public class HummingbirdTest {

	public static void main(String[] args) {
		
		HummingbirdFactory factory=new HummingbirdFactory();
		List<Hummingbird> birdlist=new ArrayList<Hummingbird>();
		for (int i = 0; i < 3; i++) {
			Hummingbird bird=factory.createBird();
			birdlist.add(bird);
		}
		for (Hummingbird hummingbird : birdlist) {
			hummingbird.caimi();
		}
	}
}
