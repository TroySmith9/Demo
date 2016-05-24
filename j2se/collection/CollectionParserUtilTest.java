package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionParserUtilTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
        //List-->数组   
        List<String> list = new ArrayList<String>();   
        list.add("tom");   
        list.add("Jerval");   
        list.add("WeiSi");   
        Object[] objects = list.toArray();//返回Object数组   
        System.out.println("objects:"+Arrays.toString(objects));   
        String[] strings1 = new String[list.size()];   
        list.toArray(strings1);//将转化后的数组放入已经创建好的对象中   
        System.out.println("strings1:"+Arrays.toString(strings1));   
        String[] strings2 = list.toArray(new String[0]);//将转化后的数组赋给新对象   
        System.out.println("strings2:"+Arrays.toString(strings2));   
        //数组-->List   
        String[] ss = {"JJ","KK"};   
        List<String> list1 = Arrays.asList(ss);   
        List<String> list2 = Arrays.asList("AAA","BBB");   
        System.out.println(list1);   
        System.out.println(list2);   
        //List-->Set   
        List<String> list3 = new ArrayList<String>(new HashSet<String>());    
        //Set-->List   
        Set<String> set = new HashSet<String>(new ArrayList<String>());   
        //数组-->Set   
        String[] strs = {"AA","BB"};   
        Set<String> set2 = new HashSet<String>(Arrays.asList(strs));   
        System.out.println(set2);   
        //Set-->数组   
        Set<String> set3 = new HashSet<String>(Arrays.asList("PP","OO"));   
        String[] strSet = new String[set3.size()];   
        set3.toArray(strSet);   
        System.out.println(Arrays.toString(strSet));   
        //Map操作   
        Map<String, String> map = new HashMap<String, String>();   
        map.put("YYY", "UUU");   
        map.put("RRR", "TTT");   
        // 将键转化为Set     
        Set<String> mapKeySet = map.keySet();   
        // 将值转化为Set     
        Set<String> mapValuesSet = new HashSet<String>(map.values());   
        // 将值转化为List     
        List<String> mapValuesList = new ArrayList<String>(map.values());   
	}
}
