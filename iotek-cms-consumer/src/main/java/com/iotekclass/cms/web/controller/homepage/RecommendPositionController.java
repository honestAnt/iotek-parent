package com.iotekclass.cms.web.controller.homepage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.homepage.RecommendPosition;
import com.iotekclass.cms.model.information.Information;
import com.iotekclass.cms.service.homepage.RecommendPositionService;
import com.iotekclass.cms.service.information.InformationService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.InformationStatusEnum;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * 
 * @ClassName: RecommendPositionController   
 * @Description： 推荐位控制器
 * @Author：hujing
 * @Date：2015年7月15日 下午1:56:12
 * @version
 */
@Controller
@RequestMapping(value = "/recommendPositionManage")
public class RecommendPositionController extends BaseController{

	@Reference(version = "1.0.0")
	private RecommendPositionService recommendPositionService;
	@Reference(version = "1.0.0")
	private InformationService informationService;
	
	private static final int PAGE_SIZE = 10;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page<RecommendPosition> page = new Page(PAGE_SIZE);
	// 标识操作状态
	private String code;

	//定义推荐位ID 为数据库推荐位表中资讯类推荐位信息ID
	private int featured = 25;
	
	//定义查询条数 
	private int number = 2 ;
	/**
	 * 
	 * @Description: 进入推荐位首页
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:19:14
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model){
		return "homepage/recommend_position_list";
	}
	/**
	 * @throws IOException 
	 * 
	 * @Description: 异步推荐位列表
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:19:36
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/indexSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public void indexSearch(Writer out,HttpServletRequest request) throws IOException{
		setPageNo(request.getParameter("pageNo"));
		page = recommendPositionService.getAllPage(page);
		String json = binder.toJson(page);
		out.write(json);
	}


	/**
	 * @throws IOException 
	 * 
	 * @Description: 添加推荐位
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:20:35
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addRecommend", method = { RequestMethod.GET, RequestMethod.POST })
	public void addRecommend(Writer out,HttpServletRequest request) throws IOException{
		RecommendPosition recommendPosition = new RecommendPosition();
		recommendPosition.setName(request.getParameter("name"));
		recommendPosition.setCreateUser(this.getLoginUserId(request));
		recommendPositionService.save(recommendPosition);
		out.write(recommendPosition.getId()+"");
	}
	
	/**
	 * 
	 * @Description: 删除推荐位
	 * @Author：Joshua
	 * @Date：2015年7月22日 下午6:52:52
	 * @param out
	 * @param request
	 * @throws IOException
	 * @throws
	 */
	@RequestMapping(value = "/delRecommend", method = { RequestMethod.GET, RequestMethod.POST })
	public void delRecommend(Writer out ,@RequestParam(value="id") int id) throws IOException{
		int i=0;
		//查询推荐位下是否有资讯
		List<Information> inforList = informationService.getFirstInfo(InformationStatusEnum.PUBLISHED.getValue(), id, number);
		//再用的或者ID为25的初始数据不能删除
		if(inforList!=null&&inforList.size()>0 || id == featured){ 
			code="500";
		}else{
			i=recommendPositionService.delete(id);
			code = (i == 1) ? "200" : "505";
		}
		out.write(code);
	}

	/**
	 * 
	 * @Description: 修改推荐位
	 * @Author：Joshua
	 * @Date：2015年7月22日 下午6:56:13
	 * @param out
	 * @param id
	 * @throws IOException
	 * @throws
	 */
	@RequestMapping(value = "/updRecommend", method = { RequestMethod.GET, RequestMethod.POST })
	public void updRecommend(Writer out ,@ModelAttribute RecommendPosition recommendPosition,HttpServletRequest request) throws IOException{
		recommendPosition.setUpdateUser(this.getLoginUserId(request));;
		int i =recommendPositionService.update(recommendPosition);
		code = (i == 1) ? "200" : "500";
		out.write(code);
	}
	/**
	 * 
	 * @Description: 打开修改
	 * @Author：Joshua
	 * @Date：2015年7月22日 下午7:04:13
	 * @param out
	 * @param recommendPosition
	 * @param request
	 * @throws IOException
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/toUpdRecommend", method = { RequestMethod.GET, RequestMethod.POST })
	public RecommendPosition toUpdRecommend(@RequestParam int id ) {
		
		return recommendPositionService.getById(id);
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @Description: 校验推荐位名称唯一性
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:20:54
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/validRecommendName", method = { RequestMethod.GET, RequestMethod.POST })
	public void validRecommendName(Writer out,@RequestParam("name") String name) throws IOException{
		RecommendPosition recommendPosition = recommendPositionService.getByName(name);
		if(recommendPosition != null){
			String json = binder.toJson("200");
			out.write(json);
		}
	}
	
	/**
	 * 
	 * @Description: 设置页面页数
	 * @param pageNo
	 * @throws
	 */
	public void setPageNo(String pageNo) {
		// 获取分页参数
		int currentPage;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNumeric(pageNo)) {
			currentPage = Integer.valueOf(pageNo).intValue();
		} else {
			currentPage = 1;
		}
		page.setCurrentPage(currentPage);
	}

}
