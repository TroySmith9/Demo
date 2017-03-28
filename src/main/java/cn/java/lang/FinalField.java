package cn.java.lang;

/**
 * Created by yiban on 2016/12/22.
 */
public class FinalField {

	private final Entity entity;

	public static void main(String[] args) {
	}

	public FinalField(Entity entity) {
		this.entity = entity;
	}

}

class Entity {
	String name;

	int no;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
