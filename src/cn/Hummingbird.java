package cn;

public class Hummingbird {
	
	public Hummingbird(int birdno) {
		this.birdno = birdno;
	}

	private int birdno;
	
	public void caimi(){
		System.out.println("Hummingbird "+birdno+"'s breakfast time");
	}
	
	public int getBirdno() {
		return birdno;
	}
	
	public void setBirdno(int birdno) {
		this.birdno = birdno;
	}
}
