package com.iotekclass.common.duobeiyun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class AppBulidSign {

    /**
     * 生成签名结果
     *
     * @param sArray 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray, String key) {
        String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        prestr = prestr + key; //把拼接后的字符串再与安全校验码直接连接起来

        String mysign;
        try {
            mysign = DigestUtils.md5Hex(prestr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集错误");
        }

        return mysign;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("mobileDevice") || key.equals("v")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        params = paraFilter(params);
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 做map的转换
     *
     * @param parameterMap
     * @return
     */
    public static Map<String, String> convertRequestMap(Map<String, String[]> parameterMap) {
        Map<String, String> alipayParameter = new HashMap<String, String>();
        for (Iterator<String> iter = parameterMap.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();

            String[] values = parameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            alipayParameter.put(name, valueStr);
        }

        return alipayParameter;
    }
}
