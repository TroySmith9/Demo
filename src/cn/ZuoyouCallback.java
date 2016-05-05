package cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ExecutionContext;

public class ZuoyouCallback {

	public static void main(String[] args) {
		try {
			for (int i = 500; i < 550; i++) {
				String url ="http://weixin.gxshangyou.com/index.php?g=Wap&m=Zuoyou&a=power&token=iuqgfm1453706336&reid=9&id=197&open_id=&wecha_id=o8inVt-ec-LsZgHZjdoUCEx6h"+ i;
				System.out.println(callBack(url));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String callBack2(String url) throws Exception {
		try {
			HttpURLConnection urlcon = (HttpURLConnection) new URL(url).openConnection();
			urlcon.setConnectTimeout(10000);// 10秒超时
			urlcon.connect();
			BufferedReader bis = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = bis.readLine()) != null) {
				result.append(line);
			}
			
			return result.toString();
		} catch (SocketTimeoutException e) {
			return callBack(url);
		}
	}

	public static String callBack(String url) throws Exception {
		HttpClient httpclient = null;
		BufferedReader bis = null;
		StringBuffer result = new StringBuffer();
		try {
			httpclient = new DefaultHttpClient();
			HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
				@Override
				public boolean retryRequest(IOException exception, int executionCount, org.apache.http.protocol.HttpContext context) {
					if (executionCount >= 3) {
						// 如果超过最大重试次数，那么就不要继续了
						return false;
					}
					if (exception instanceof NoHttpResponseException) {
						// 如果服务器丢掉了连接，那么就重试
						return true;
					}
					if (exception instanceof SSLHandshakeException) {
						// 不要重试SSL握手异常
						return false;
					}
					HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
					boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
					if (idempotent) {
						// 如果请求被认为是幂等的，那么就重试
						return true;
					}
					return false;
				}
			};

			httpclient.getParams().setParameter("http.method.retry-handler", myRetryHandler);
			HttpGet getMethod = new HttpGet(url);
			HttpResponse executeMethod = httpclient.execute(getMethod);
			InputStream inputStream = executeMethod.getEntity().getContent();

			bis = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;

			while ((line = bis.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
		return result.toString();
	}
}
