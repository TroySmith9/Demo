package json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Json校验
 */
public class JsonValidateTest{
    public static void main(String[] args) {
        try {
            File file=new File("H:/icon.json");
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            StringBuffer sb=new StringBuffer();
            String str="";
            while (StringUtils.isNotBlank(str=bufferedReader.readLine())){
                sb.append(str);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            System.out.println(jsonObject);
        }catch (Exception e){
        }
        
    }
}
