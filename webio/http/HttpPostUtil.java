package http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class HttpPostUtil {
	URL url;
	HttpURLConnection conn;
	String boundary = "--------httppost";
	Map<String, String> textParams = new HashMap<String, String>();//请求文本参数
	Map<String, File> fileparams = new HashMap<String, File>();//请求文件参数
	Map<String, String> textHeader = new HashMap<String, String>();//请求header
	DataOutputStream ds;

	public HttpPostUtil(String url) throws Exception {
		this.url = new URL(url);
	}

	// 增加一个header
	public void addHeaderParameter(String name, String value) {
		textHeader.put(name, value);
	}
	
	// 增加一个普通字符串数据到form表单数据中
	public void addTextParameter(String name, String value) {
		textParams.put(name, value);
	}

	// 增加一个文件到form表单数据中
	public void addFileParameter(String name, File value) {
		fileparams.put(name, value);
	}

	/**
	 * 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
	 * @param hasFile 是否上传文件
	 * @return
	 * @throws Exception
	 */
	public HttpResponseBean send(Boolean hasFile) throws Exception {
		initConnection(hasFile);
		conn.connect();
		ds = new DataOutputStream(conn.getOutputStream());
		if(null != hasFile && hasFile){
			writeFileParams();
		}
		writeStringParams(hasFile);
		if(null != hasFile && hasFile){
			paramsEnd();
		}
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		
		HttpResponseBean response = new HttpResponseBean();
		response.setResponseContent(out.toByteArray());
		response.setResponseHeaderFields(conn.getHeaderFields());
		response.setResponseCode(conn.getResponseCode());
		response.setResponseMessage(conn.getResponseMessage());
		response.setResponseHeaderCMD(conn.getHeaderField("cmd"));
		response.setPostSize(ds.size());
		out.flush();out.close();
		ds.close();
		return response;
	}
	
	/**
	 * 设置请求头
	 */
	private void addHeader() throws Exception {
		Set<String> keySet = textHeader.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textHeader.get(name);
			conn.addRequestProperty(name, value);
		}
	}
	
	/**
	 * @param upFile true：上传文件
	 * @throws Exception
	 */
	private void initConnection(Boolean hasFile) throws Exception {
		conn = (HttpURLConnection) this.url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.setConnectTimeout(1000*30); // 连接超时为30秒
		addHeader();
		if(null != hasFile && hasFile){
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		}else{
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		}
	}
	
	// 普通字符串数据
	private void writeStringParams(Boolean hasFile) throws Exception {
		Set<String> keySet = textParams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textParams.get(name);
			if(null != hasFile && hasFile){
				ds.writeBytes("--" + boundary + "\r\n");
				ds.writeBytes("Content-Disposition: form-data; name=\"" + encode(name) + "\"\r\n");
				ds.writeBytes("\r\n");
				ds.writeBytes(encode(value) + "\r\n");
			}else{
				ds.writeBytes(encode(name) + "=" + encode(value));
			}
		}
	}
	
	// 文件数据
	private void writeFileParams() throws Exception {
		Set<String> keySet = fileparams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			File value = fileparams.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\""
					+ encode(name) + "\"; filename=\"" + encode(value.getName())
					+ "\"\r\n");
			ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
			ds.writeBytes("\r\n");
//			ds.write(getBytes(value));
			ds.write(getGZipBytes(value));
			ds.writeBytes("\r\n");
		}
	}

	// 获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
	private String getContentType(File f) throws Exception {
		// return "application/octet-stream";
		// 此行不再细分是否为图片，全部作为application/octet-stream 类型
		ImageInputStream imagein = ImageIO.createImageInputStream(f);
		if (imagein == null) {
			return "application/octet-stream";
		}
		Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
		if (!it.hasNext()) {
			imagein.close();
			return "application/octet-stream";
		}
		imagein.close();
		return "image/" + it.next().getFormatName().toLowerCase();

	}

	// 把文件转换成字节数组
	private byte[] getBytes(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = in.read(b)) != -1) {
			out.write(b, 0, n);
		}
		in.close();
		return out.toByteArray();
	}
	
	// 把文件转换成字节数组
	private byte[] getGZipBytes(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzout = new GZIPOutputStream(out);
//		GZIPOutputStream gzout = new GZIPOutputStream(out);
		byte[] b = new byte[1024];
		int n;
		while ((n = in.read(b)) > 0){
			 gzout.write(b, 0, n);
		}
		in.close();
        gzout.flush();
        gzout.close();
		
//		byte[] byteArray= out.toByteArray();
//		byte[] result = new byte[byteArray.length - 8];
//		for(int i=0; i<byteArray.length; i++){
//			result[i] = byteArray[i + 8];
//		}
//		return result;
		return out.toByteArray();
	}

	// 添加结尾数据
	private void paramsEnd() throws Exception {
		ds.writeBytes("--" + boundary + "--" + "\r\n");
		ds.writeBytes("\r\n");
	}

	// 对包含中文的字符串进行转码，此为UTF-8。
	private String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}
	
	
	public static void main(String[] args) throws Exception{
		HttpPostUtil u = new HttpPostUtil("http://test.feidee.net/money-w/syncUserCheck?UserName=test12@kd.com&Password=123456&encode=&isDetail=true&protocolVersion=3.0&userKey=&userType=&Model=iPhone&ProductName=&ProductVersion=&Locale=&SyncBooks=[{%27SyncAccountBookID%27:1674290,%27UDID%27:%27wwww%27,%27SyncMode%27:%27auto%27},{%27SyncAccountBookID%27:1716690,%27UDID%27:%27%27,%27SyncMode%27:%27auto%27}]");
		u.addFileParameter("file1", new File("src"));
		u.addTextParameter("param1", "value");
		String result = new String(u.send(true).getResponseContent());
		System.out.println(result);
	}
	
}

class HttpResponseBean {
	private int responseCode;//响应码
	private String responseMessage;//响应信息
	private byte[] responseContent;//响应内容
	private Map<String, List<String>> responseHeaderFields;//响应header内容
	private String responseHeaderCMD;//header "cmd"
	
	private long postSize = 0L;//post请求发出的字节数
	
	public long getPostSize() {
		return postSize;
	}
	public void setPostSize(long postSize) {
		this.postSize = postSize;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public byte[] getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(byte[] responseContent) {
		this.responseContent = responseContent;
	}
	public Map<String, List<String>> getResponseHeaderFields() {
		return responseHeaderFields;
	}
	public void setResponseHeaderFields(
			Map<String, List<String>> responseHeaderFields) {
		this.responseHeaderFields = responseHeaderFields;
	}
	public String getResponseHeaderCMD() {
		return responseHeaderCMD;
	}
	public void setResponseHeaderCMD(String responseHeaderCMD) {
		this.responseHeaderCMD = responseHeaderCMD;
	}
	
	//获取cookie
	public String getCookie() {
		Map<String, List<String>> map = this.responseHeaderFields;
		if(map == null) return null;
		
		Set<String> keySet = map.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = (String) it.next();
			if ("Set-Cookie".equals(key)) {
				List<String> list = map.get(key); 
				StringBuilder builder = new StringBuilder(); 
				for (int i=0; i<list.size(); i++) {
					if(i != (list.size() -1))
						builder.append(list.get(i) + ", ");
					else 
						builder.append(list.get(i));
				} 
				return builder.toString();
			} 
		}
		return null;
	}
	
}
