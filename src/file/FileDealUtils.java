package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDealUtils {

	private static List<File> fileList = new ArrayList<>();

	public static void main(String[] args) {
		String path = "";
		File file = new File(path);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		
//		getAllFiles(path);
//		replaceFileNames("","");
	}

	public static void replaceFileNames(String old, String newname) {
        for (File file : fileList) {
        	String oldname=file.getName();
        	oldname.replaceAll("", newname);
//        	file.
        	file.renameTo(new File(""));
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
