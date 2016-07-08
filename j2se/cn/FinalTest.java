package cn;

/**
 * final对象改变属性 实现描述：
 * @author yiban
 * @time 2016年7月1日
 */
public class FinalTest {

	public static void main(String[] args) {
		
		Entity en=new Entity();
		en.setName("aaa111");
		String str="hello!";
		show(en,str);
	}

	protected static void show(final Entity en,final String str) {
		en.setNo("101");
		System.out.println(en.getNo());
	}
}

class Entity {

	String name;

	String no;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}