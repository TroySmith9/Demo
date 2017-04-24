package file;

import java.io.*;

/**
 * Created by yiban on 2017/4/18.
 */
public class FileReadSkipTest {

    public static void main(String[] args)  {
        
        try {
            File inFile=new File("d:\\in.txt");
            InputStream reader = new BufferedInputStream(new FileInputStream(inFile));
            if (reader.read(new byte[4]) <= 0) {
                System.out.println("格式错误！");
            }
            byte[] bytes=new byte[8];
            int len;
            while ((len = reader.read(bytes)) > 0){
                System.out.println(bytes+" **  "+len);
            }   
        }catch (Exception e){
            e.printStackTrace();
        }
       
        
        
    }
}
