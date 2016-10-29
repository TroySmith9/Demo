package cn;

import java.util.HashMap;
import java.util.Map;

/**
 * final对象改变属性 实现描述：
 * @author yiban
 * @time 2016年7月1日
 */
public class FinalTest {

	public static void main(String[] args) {

		Entity en = new Entity();
		en.setName("aaa111");
		final String str = "hello!";
		show(en, str, 1, 2, new HashMap<String, String>());
	}

	protected static void show(final Entity en, final String str, final int i, final Integer j, final Map<String, String> map) {
		en.setNo("101");
		en.setName("daff");
		// str="";//报错 ，不可变
		// i=2;//报错 ，不可变
		// j=3;/报错 ，不可变
		map.put("11", "aa");
		System.out.println(en.getNo() + en.getName() + map.toString());
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