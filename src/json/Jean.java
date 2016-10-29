package json;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * json文件合并工具类
 * @author 杨元
 */
public class Jean {
    
    /**
     * 识别jean表达式
     */
    private static Pattern jeanRegex = Pattern.compile("(@Jean\\((\"[^\"]*\",)?\"[^\"]*\"\\))");
    /**
     * 识别jean表达式中的所有参数
     */
    private static Pattern paramRegex = Pattern.compile("\"([^\"]*)\"");
    /**
     * 识别jean表达式中的name参数
     */
    private static Pattern nameRegex = Pattern.compile("\"([^\"]*)\",");
    /**
     * 默认属性名称
     */
    private static String defaultName = "jean";
    
    /**
     * 解析所有的jean表达式
     * @param json json字符串
     * @param jsonPath json字符串所在路径，完整路径
     * @return 解析后的json字符串
     */
    public static String parseAll(String json,String jsonPath){
        //识别jean表达式
        List<String> jeans = regexMatchList(jeanRegex, json);
        jeans = noRepeat(jeans);
        
        //解析
        for(String jean : jeans){
            json = json.replace(jean, parse(jean, jsonPath));
        }
        
        return json;
    }
    
    /**
     * 解析单个jean表达式
     * @param express jean表达式
     * @param jsonPath json字符串所在路径，完整路径
     * @return 解析结果
     */
    public static String parseOne(String express,String jsonPath){
        return parse(express, jsonPath);
    }
    
    /**
     * 解析特定的jean表达式
     * @param json json字符串
     * @param jsonPath json字符串所在路径，完整路径
     * @param names 需要解析的属性名称列表
     * @return 解析后的json字符串
     */
    public static String parseTarget(String json,String jsonPath,List<String> names){
        //识别jean表达式
        List<String> jeans = regexMatchList(jeanRegex, json);
        jeans = noRepeat(jeans);
        //处理属性名映射
        Map<String, Boolean> nameMap = new HashMap<String, Boolean>();
        for(String s : names){
            nameMap.put(s, true);
        }
        
        //解析
        String replacement = "";
        Matcher matcher = null;
        String name = "";
        for(String jean : jeans){
            matcher = nameRegex.matcher(jean);
            
            //判断是否传入属性名称
            if(matcher.find()){
                name = matcher.group(1);
                //判断是否需要解析
                if(nameMap.get(name) != null){
                    replacement = parse(jean, jsonPath);
                }else{
                    //不需要解析直接将属性值写为null
                    replacement = "\""+name+"\": null";
                }
            }else{
                //无属性名直接用默认的jean
                replacement = "\""+defaultName+"\": null";
            }
            
            json = json.replace(jean, replacement);
        }
        
        return json;
    }
    
    /**
     * 解析jean表达式
     * @param express jean表达式
     * @param jsonPath json文件所在路径，完整路径
     * @return jean表达式执行结果
     */
    private static String parse(String express,String jsonPath){
        //识别参数
        List<String> params = regexMatchList(paramRegex, express);
        //默认属性名称
        String name = defaultName;
        //格式化路径
        jsonPath = removeSuffix(jsonPath, "/");
        
        //判断是否传入了属性名称
        if(params.size() > 1){
            name = params.get(0);
        }
        
        //解析路径
        String path = getAbsolutePath(jsonPath, params.get(params.size()-1));
        
        //读取内容并返回
        name = wrapWith(name, "\"");
        return name + ": " + readJsonFile(path);
    }
    
    /**
     * 从字符串中移除指定后缀
     * @param source 源字符串
     * @param suffix 需要移除的后缀
     * @return 处理后的源字符串
     */
    private static String removeSuffix(String source,String suffix){
        if(source.endsWith(suffix)){
            source = source.substring(0, source.length()-suffix.length());
        }
        
        return source;
    }
    
    /**
     * list内容去重
     * @param list 内容为string的list
     * @return 内容去重后的list
     */
    private static List<String> noRepeat(List<String> list){
        Map<String, String> map = new HashMap<String, String>();
        List<String> result = new ArrayList<String>();
        
        for(String s : list){
            map.put(s, null);
        }
        
        for(String s : map.keySet()){
            result.add(s);
        }
        
        return result;
    }
    
    /**
     * 用指定的字符串包裹内容
     * @param content 内容
     * @param wrap 包裹字符串
     * @return 包裹后的内容
     */
    private static String wrapWith(String content,String wrap){
        return wrap+content+wrap;
    }
    
    /**
     * 读取Json文件(纯文本文件，utf-8编码)
     * 这个方法可以替换成自己项目中封装的方法
     * @param path 文件路径
     * @return 文件内容
     */
    private static String readJsonFile(String path){
        String encoding = "utf-8";
        StringBuilder sb = new StringBuilder(256);
        
        File file = new File(path);
        InputStreamReader iReader = null;
        BufferedReader bReader = null;
        
        try{
            iReader = new InputStreamReader(new FileInputStream(file), encoding);
            bReader = new BufferedReader(iReader);
            String line = null;
            
            while((line = bReader.readLine()) != null){
                sb.append(line.trim());
            }
            
            bReader.close();
            iReader.close();
            
        }catch(Exception e){
            if(iReader != null){
                try {
                    iReader.close();
                } catch (IOException e1) {
                    iReader = null;
                }
            }
            if(bReader != null){
                try {
                    bReader.close();
                } catch (IOException e1) {
                    bReader = null;
                }
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 将相对路径转换成绝对路径
     * 只识别 ./ ../
     * @param refrence 基准参照路径
     * @param relative 相对路径表达式
     * @return 绝对路径
     */
    private static String getAbsolutePath(String refrence,String relative){
        if(relative.startsWith("./")){
            refrence = getAbsolutePath(refrence, relative.replaceFirst("\\./", ""));
        }else if(relative.startsWith("../")){
            refrence = getAbsolutePath(refrence.substring(0, refrence.lastIndexOf("/")), 
                            relative.replaceFirst("\\.\\./", ""));
        }else{
            refrence = refrence + "/" + relative;
        }
        
        return refrence;
    }
    
    /**
     * 将正则表达式的匹配结果转换成列表
     * @param regex 正则表达式对象
     * @param input 要检索的字符串
     * @return 结果列表
     */
    private static List<String> regexMatchList(Pattern regex,String input){
        List<String> result = new ArrayList<String>();
        Matcher matcher = regex.matcher(input);
        while(matcher.find()){
            result.add(matcher.group(1));
        }
        
        return result;
    }


}