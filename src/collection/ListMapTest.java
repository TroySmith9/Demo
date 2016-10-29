package collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMapTest {

	public static void main(String[] args) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("a", 111);
		map.put("b", "bbb");
		map.put("c", new Object());
		List<Map<Object, Object>> list = Arrays.asList(map);
		System.out.println(list.size());
		for (Object object : list) {
			System.out.println(object);
		}

		Map<MapDim, Object> map2 = new HashMap<MapDim, Object>();
		map2.put(new MapDim("a1", "a2"), 111);
		map2.put(new MapDim("b1", "b2"), "bbb");
		map2.put(new MapDim("c1", "c2"), new Object());
		System.out.println(map2.values().size());
		List<Collection<Object>> dimList = Arrays.asList(map2.values());
		System.out.println(dimList.size());
		for (Object object : map2.values()) {
			System.out.println(object);
			System.out.println(object.getClass());
		}
		
		Map<MapDim, Object> map3 = new HashMap<MapDim, Object>();
		System.out.println(Arrays.asList(map3.values().size()));

	}

}

class Dim{
	String key;
	String value;
	
	public Dim(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
