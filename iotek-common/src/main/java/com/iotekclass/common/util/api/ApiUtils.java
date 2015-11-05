package com.iotekclass.common.util.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Class api接口工具类
 *
 * @version        2.0
 * 2015.09.07 at 02:01:42 CST
 * @author         wangfengbao    
 */
public class ApiUtils {

//    String httpUrl = "http://apis.baidu.com/heweather/weather/free";
//    String httpArg = "city=beijing";

    /**
     * @param httpUrl
     *            :请求接口
     * @param httpArg(请求参数:city=beijing)
     * @param apikey(请求客户端密钥)
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg,String apikey) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", apikey);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        http://v.showji.com/Locating/showji.com20150416273007.aspx?m="+tel+"&output=json&timestamp="+time,
     String str = request("http://v.showji.com/Locating/showji.com20150416273007.aspx","?m=13248330504&&output=json&timestamp="+new Date().getTime(),null);
        System.out.println(str);
    }
}



