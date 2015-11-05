package com.iotekclass.cms.web.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.cache.redis.RedisClientTemplate;

/**
 * ClassName: IndexAction
 * Description：首页
 * Author：王啸
 * Date：2014年9月11日 上午11:41:52
 * 
 * @version
 */
@Controller
@RequestMapping(value="/index")
public class IndexAction extends BaseController {

	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	/**
	 * 
	 * @Description: 首页控制
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "index/index";
	}

//	@Action(value = "wecome", results = { @Result(name = SUCCESS, location = "/web/pages/index/wecome.jsp") })
	@RequestMapping(value="/wecome", method = { RequestMethod.GET, RequestMethod.POST })
	public String wecome() {
		return "index/wecome";
	}
	
}
