package file;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yiban on 2016/11/1.
 */
public class GZipUtils {

    public static void unGzip(File in, File out){
        // 先创建父目录
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        try( FileOutputStream fos= new FileOutputStream(out);InputStream is=new GZIPInputStream(new FileInputStream(in))) {
            // 写入上传的内容
            byte[] buf = new byte[1024];
            int num;
            while ((num = is.read(buf, 0, buf.length)) != -1) {
                fos.write(buf, 0, num);
            }
        }catch (IOException e){
            System.out.println("解压过程中出现错误");
        }
    }

    public static void gzip(File in, File out){
        // 先创建父目录
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        try(GZIPOutputStream fos= new GZIPOutputStream(new FileOutputStream(out)); InputStream is=new FileInputStream(in)) {
            // 写入上传的内容
            byte[] buf = new byte[1024];
            int num;
            while ((num = is.read(buf, 0, buf.length)) != -1) {
                fos.write(buf, 0, num);
            }
        }catch (IOException e){
            System.out.println("解压过程中出现错误");
        }
    }

    public static void main(String[] args) {
        gzip(new File("G:\\statistic.data"), new File("G:\\statistic.data.gz"));
    }
}
