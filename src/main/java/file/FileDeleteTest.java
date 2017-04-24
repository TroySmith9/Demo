package file;

import java.io.File;

/**
 * Created by yiban on 2017/4/19.
 */
public class FileDeleteTest {

    public static void main(String[] args) {
        File file=new File("d://in.txt");
        if (!file.delete()){
            System.out.println("删除失败");
        }
    }
}
