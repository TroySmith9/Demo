package file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yiban on 2017/3/29.
 */
public class PropertiesTest {

    public static void main(String[] args) throws IOException {
        Properties properties=new Properties();
        InputStream inputStream = PropertiesTest.class.getClassLoader().getResourceAsStream("log4j.properties");
        properties.load(inputStream);
//        System.out.println(properties.get("log4j.rootLogger"));
        for (Map.Entry entry:properties.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
    }
    
}
