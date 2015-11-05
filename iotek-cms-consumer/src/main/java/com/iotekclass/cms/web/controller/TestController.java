package com.iotekclass.cms.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iotekclass.common.cache.redis.RedisClientTemplate;

/**
 * 
 * ClassName: IDEController   
 * Description： 在线编译控制器
 * Author：王啸
 * Date：2015年5月20日 下午4:19:44
 * @version
 */
@Controller
//@RequestMapping(value = "/ide")
public class TestController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test() {
		//redisClientTemplate.set("hi", "hi");
		String string = redisClientTemplate.get("hi");
		logger.debug(string);
		return "home/ide";
	}

	@RequestMapping(value = "/testredis", method = { RequestMethod.GET, RequestMethod.POST })
	public String testredis(Model model,HttpServletRequest request,@RequestParam("") String paxx) {
		//redisClientTemplate.set("hi", "hi");
		String string = redisClientTemplate.get("hi");
		model.addAttribute("hi", string);
		logger.debug(string);
		return "home/ide";
	}

	@RequestMapping(value = "/testjson", method = { RequestMethod.GET, RequestMethod.POST })
	public void testjson(Writer out) throws IOException {
		//redisClientTemplate.set("hi", "hi");
//		User user = new User();
		/*user.setId(1);
		user.setEmail("wangxiao");
		user.setPassword("wangxiao");
		String json = binder.toJson(user);
		out.write(json);*/
		out.write("wangxiao");
	}

	/*@ModelAttribute("onlineCompile")
	private OnlineCompile getOnlineCompile(@RequestParam("courseWareId") int courseWareId) {
		return onlineCompileService.getOnlineCompilesByCoursewareId(courseWareId);
	}*/

	/*@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public String home1(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "play_ide";
	}*/

}
