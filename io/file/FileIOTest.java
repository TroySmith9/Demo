package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(new File("e:\\test.txt"));
			fos = new FileOutputStream(new File("e:\\out.txt"));
			// int temp = 0;
			// StringBuffer sb = new StringBuffer();
			// while ((temp = fis.read()) != -1) {
			// fos.write(temp);
			// sb.append(String.valueOf(temp));
			// // System.out.println(temp);
			// }
			fos.write(" Love is there !".getBytes());

			InputStreamReader reader = new InputStreamReader(fis,"GBK");
			@SuppressWarnings("resource")
			BufferedReader  br=new BufferedReader(reader);
			String line = null;
			while ((line=br.readLine())!=null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fis.close();
			fos.close();
		}

	}

}
