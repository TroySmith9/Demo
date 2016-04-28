package collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {

		Map<Dim,String> map=new HashMap<Dim, String>();
		Dim dim=new Dim("aa","11");
		map.put(dim, "aa");
		System.out.println(map.containsKey(new Dim("aa","11")));
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
		MapDim dim= (MapDim) obj;
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
