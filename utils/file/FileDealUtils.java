package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDealUtils {

	private static List<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {
		String path = "E:\\Coding/php从入门到精通-传智播客-韩顺平";
		
		getAllFiles(path);
		replaceFileNames("传智播客_韩顺平_php从入门到精通 视频教程 ","");
	}

	public static void replaceFileNames(String old, String replacement) {
        for (File file : fileList) {
        	String oldpath=file.getPath();
        	String newpath=oldpath.replaceAll(old, replacement);
        	file.renameTo(new File(newpath));
//        	System.out.println(newpath);
		}
	}

	public static void getAllFiles(String path) {

		File file = new File(path);
		if (file.isDirectory()) {
			for (File childFile : file.listFiles()) {
                 getAllFiles(childFile.getAbsolutePath());
			}
		}else{
			fileList.add(file);
		}

	}

}
