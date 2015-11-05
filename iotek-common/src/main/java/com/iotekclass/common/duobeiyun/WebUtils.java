package com.iotekclass.common.duobeiyun;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.Lists;

public class WebUtils {

	private static final String DEFAULT_CHARSET = "UTF8";

	public static String multipartPost(String url, Map<String, String> params, String filename, File fileToUpload) {
		System.out.println(url);
		HttpPost post = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(Charset.forName(DEFAULT_CHARSET));
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody("slidesFile", fileToUpload, ContentType.DEFAULT_BINARY, filename);
		for (String key:params.keySet()) {
			builder.addTextBody(key, params.get(key), ContentType.DEFAULT_BINARY);
		}
		
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpclient.execute(post);

			try {
			    System.out.println(response.getStatusLine());
			    HttpEntity entity2 = response.getEntity();
			    String rt = EntityUtils.toString(entity2);
			    return rt;
			} finally {
			    response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String post(String url, Map<String, String> params) {
		System.out.println(url);
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> paramsList = Lists.newArrayList();
			
			for(String k:params.keySet()) {
				paramsList.add(new BasicNameValuePair(k, params.get(k)));
			}
			
			httpPost.setEntity(new UrlEncodedFormEntity(paramsList, Charset.forName(DEFAULT_CHARSET)));
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
			    System.out.println(response2.getStatusLine());
			    HttpEntity entity2 = response2.getEntity();
			    String rt = EntityUtils.toString(entity2);
			    return rt;
			} finally {
			    response2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String buildQueryString(Map<String, String> params) throws Exception{
		String q = "";
		for(String key:params.keySet()) {
			if(q.length() > 0) {
				q += "&"; 
			}
			q += URLEncoder.encode(key, DEFAULT_CHARSET)+"="+URLEncoder.encode(params.get(key), DEFAULT_CHARSET);
		}
		
		return q;
	}

}
