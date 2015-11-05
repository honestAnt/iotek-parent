package com.iotekclass.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iotekclass.common.constants.Constants;
import com.iotekclass.common.util.EntityUtils;
import com.iotekclass.common.util.MD5Util;
import com.iotekclass.common.util.memcache.MemCachedManager;
import com.iotekclass.persist.BaseMapper;

/**
 * ClassName: BaseServiceImpl
 * Description： 业务处理类的基类，包含一些常用的操作函数
 * Author：王啸
 * Date：2013-8-25 下午12:44:40
 * 
 * @version
 */
@Service("baseService")
@SuppressWarnings("serial")
public abstract class BaseServiceImpl<T> implements BaseService<T>, Serializable {

	public static final Logger logger = Logger.getLogger(BaseServiceImpl.class);
	/* 数据操作接口引用 */
	protected BaseMapper<T> mapper;

	private String tableName;

	protected MemCachedManager memManager;

	public BaseServiceImpl(BaseMapper<T> mapper, String tableName) {
		this.mapper = mapper;
		this.tableName = tableName;
		memManager = MemCachedManager.getInstance();
	}

	public BaseServiceImpl() {
		super();
	}

	@Override
	public List<T> getAll() {
		return mapper.getAll(tableName);
	}

	@Override
	public T getById(int id) {
		return mapper.getById(tableName, id);
	}

	@Override
	public int save(T t) {
		return mapper.save(t);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(tableName, id);
	}

	@Override
	public int update(T t) {
		return mapper.update(t);
	}

	public T getByParams(Map params) {
		return mapper.getByParams(tableName, params);
	}

	protected List<T> getListByParams(Map params) {
		return mapper.getListByParams(tableName, params);
	}

	/**
	 * @Description: 实现数据传输对象的属性copy
	 * @param sour
	 * @param dest
	 * @throws
	 */
	public void doTrans(Object sour, Object dest) {
		EntityUtils.doTrans(sour, dest);
	}
	
	/** 
	 * @Description: 同步用户信息到CRM (新增或修改)
	 * @param mapData(用户数据)
	 * @param url (访问资源路径)
	 * @param hostAdd (访问主机地址)
	 * @param port (主机端口)
	 * @param visitType (访问方式http/https)
	 * @throws 
	*/ 
	@Override
	public void sendUserDataToCrm(Map<String, String> mapData, String url,
			String hostAdd, int port, String visitType) {
		//创建httpclient对象
		HttpClient client = new HttpClient();   
		//设置请求对象参数
        client.getHostConfiguration().setHost(hostAdd, port, visitType);   
        //设置编码格式
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        //创建提交信息方法
        HttpMethod method = getPostMethod(mapData,url);//使用POST方式提交数据   
        
         try {
        	 //执行提交操作
			client.executeMethod(method);
		} catch (HttpException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
         
         method.releaseConnection();  
		
	}
	
	/**  
     * 使用POST方式提交数据  
     * @return  
     */   
    private static HttpMethod getPostMethod(Map<String, String> mapData, String url){   
    	
    	PostMethod post = new PostMethod(url);  

    	//初始化参数对象
    	NameValuePair[] pars = new NameValuePair[mapData.size()+1];
    	//解析参数
    	int i = -1;
		for (Map.Entry<String, String> entry : mapData.entrySet()) {
			i+= 1;
			NameValuePair simcard = new NameValuePair(entry.getKey(), entry.getValue());   
			pars[i] = simcard;
		}
		//绑定安全令牌
		NameValuePair token = new NameValuePair("token", MD5Util.md5(Constants.IOTEK_CRM_TOKEN));
		pars[mapData.size()] = token;
    	//绑定参数
        post.setRequestBody(pars);   
        return post;   
    }   

}
