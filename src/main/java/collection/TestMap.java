package collection;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

	public static void main(String[] args) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(null,"null");
		map.put(0,0);
		System.out.println(map.get(null));
		System.out.println(map.get(0));
//		testMap2String(map);
//		map.put("integer",null);
//		if ((Integer) map.get("integer") == 1) {
//			System.out.println(true);
//		}else {
//			System.out.println(false);
//		}
	}

	private static void testMap2String(Map<String, String> map) {
		map.put("dim", "aa");
		map.put("11", "aa");
		System.out.println(map.toString());
	}

	public static void testMapDim() {
		Map<Dim, String> map = new HashMap<Dim, String>();
		Dim dim = new Dim("aa", "11");
		map.put(dim, "aa");
		System.out.println(map.containsKey(new Dim("aa", "11")));
	}
}

class MapDim {
	private String name;

	private String code;

	public MapDim(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		MapDim dim = (MapDim) obj;
		if (name.equals(dim.getName()) && code.equals(getCode()))
			return true;
		return super.equals(obj);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
