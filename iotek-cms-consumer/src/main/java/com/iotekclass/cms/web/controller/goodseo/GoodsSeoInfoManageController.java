package com.iotekclass.cms.web.controller.goodseo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.goodseo.GoodsSeoInfo;
import com.iotekclass.cms.service.goods.GoodsLevelService;
import com.iotekclass.cms.service.goods.GoodsService;
import com.iotekclass.cms.service.goodseo.GoodsSeoInfoService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.GoodsLevelTypeEnum;
import com.iotekclass.common.constants.enums.GoodsSeoStatusEnum;
import com.iotekclass.common.constants.enums.GoodsStatusEnum;
import com.iotekclass.common.util.DateUtil;
import com.iotekclass.model.dto.ManageGoodsListDto;
import com.iotekclass.model.goods.GoodsLevel;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GoodsSeoInfoManageController.java
 * @Description 商品SEO信息Controller
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年7月16日 下午5:50:33
 * @Version 1.0
 */

@Controller
@RequestMapping(value="/goodsSeoInfo")
public class GoodsSeoInfoManageController extends BaseController {

	@Reference(version = "1.0.0")
	private GoodsService goodsService;

	@Reference(version = "1.0.0")
	private GoodsLevelService goodsLevelService;

	@Reference(version = "1.0.0")
	private GoodsSeoInfoService goodsSeoInfoService;
	
	/**
	 *分页组件 
	 */
	Page<ManageGoodsListDto> page = new Page<ManageGoodsListDto>(PAGE_SIZE);

	/**
	 * 每页多少条数据
	 */
	private static final int PAGE_SIZE = 10;
	
	
	/**
	 * @Description: 进入商品SEO信息首页
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月17日 上午9:59:57
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/goodsSeoListIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String goodsSeoListIndex(Model model) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = goodsService.getGoodsListAboutGoodsManageIndex();

		model.addAttribute("goodsStatus", GoodsStatusEnum.ON_THE_SHELF.getValue());
		model.addAttribute("fromGoodsListVisitFlag", false);
		model.addAttribute("seoStatus", GoodsSeoStatusEnum.RELEASED.getValue());
		model.addAttribute("dataMap", dataMap);
		
		return "goodseo/goods_seo_list";
	}
	
	
	/**
	 * @Description: 进入商品SEO信息审核列表页
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月17日 上午9:59:57
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/goodsSeoAuditListIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String goodsSeoAuditListIndex(Model model) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = goodsService.getGoodsListAboutGoodsManageIndex();

		model.addAttribute("goodsStatus", GoodsStatusEnum.ON_THE_SHELF.getValue());
		model.addAttribute("fromGoodsListVisitFlag", false);
		model.addAttribute("seoStatus", GoodsSeoStatusEnum.PENDING_AUDIT.getValue());
		model.addAttribute("dataMap", dataMap);
		
		return "goodseo/goods_seo_list";
	}
	
	
	/**
	 * @throws IOException 
	 * @Description: 查询商品二级分类
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月17日 上午10:56:33
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/getSecondGoodsLevel", method = {RequestMethod.GET, RequestMethod.POST})
	public void querySecondGoodsLevel(Writer out, HttpServletRequest request) throws IOException {
		
		int firstLevelId = Integer.parseInt(request.getParameter("firstLevelId"));
		List<GoodsLevel> secondLevelList = goodsLevelService.getAllForHession(GoodsLevelTypeEnum.FIRST_SECOND.getValue(), firstLevelId);
		out.write(binder.toJson(secondLevelList));
	}
	
	
	/**
	 * @throws IOException 
	 * @Description: 查询符合条件的商品SEO信息列表-->商品列表
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月4日 下午2:39:12
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/searchGoodsSeoList", method = {RequestMethod.GET, RequestMethod.POST})
	public void searchGoodsList(Writer out, HttpServletRequest request) throws IOException {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("firstLevelId", request.getParameter("firstLevelId"));
		paramsMap.put("secondLevelId", request.getParameter("secondLevelId"));
		paramsMap.put("thirdLevelId", request.getParameter("thirdLevelId"));
		paramsMap.put("startTime", request.getParameter("startTime"));
		paramsMap.put("endTime", request.getParameter("endTime"));
		paramsMap.put("searchStr", request.getParameter("searchStr"));
		paramsMap.put("goodsStatus", request.getParameter("goodsStatus"));
		paramsMap.put("typeId", 1);
		paramsMap.put("fromGoodsListVisitFlag", Boolean.parseBoolean(request.getParameter("fromGoodsListVisitFlag")));
		paramsMap.put("seoStatus", Integer.parseInt(request.getParameter("seoStatus")));
		setPageNo(request.getParameter("pageNo"));
		//分页
		page = goodsService.searchGoodsList(paramsMap,page);
//		out.write(objToJsonData("pageNo", page.getCurrentPage()));
		out.write(binder.toJson(page));
	}
	
	
	
	/**
	 * @Description: 进入新增商品SEO信息页面
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月20日 上午11:03:51
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/addGoodsSeoIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String addGoodsSeoIndex(Model model, HttpServletRequest request) {
		int goodsId =0;
		if(request.getParameter("goodsId")!=null){
			goodsId = Integer.parseInt(request.getParameter("goodsId"));
		}
		Map<String, Object> dataMap = goodsSeoInfoService.getGoodSeoInfoByGoodsId(goodsId);
		model.addAttribute("dataMap", dataMap);
		
		return "goodseo/operation_goods_seo";
	}
	
	/**
	 * @Description: 进入编辑商品SEO信息页面
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月20日 上午11:03:51
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/updateGoodsSeoIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateGoodsSeoIndex(Model model, HttpServletRequest request) {
		int goodsId =0;
		if(request.getParameter("goodsId")!=null){
			goodsId = Integer.parseInt(request.getParameter("goodsId"));
		}
		Map<String, Object> dataMap = goodsSeoInfoService.getGoodSeoInfoByGoodsId(goodsId);
		model.addAttribute("dataMap", dataMap);
		
		return "goodseo/operation_goods_seo";
	}
	
	
	
	/**
	 * @Description: 进入审核商品SEO信息页面
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月20日 上午11:03:51
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/goodsSeoAuditIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String goodsSeoAuditIndex(Model model, HttpServletRequest request) {
		int goodsId =0;
		if(request.getParameter("goodsId")!=null){
			goodsId = Integer.parseInt(request.getParameter("goodsId"));
		}
		Map<String, Object> dataMap = goodsSeoInfoService.getGoodSeoInfoByGoodsId(goodsId);
		model.addAttribute("dataMap", dataMap);
		
		return "goodseo/operation_goods_seo";
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 * @Description: 新增SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午1:44:55
	 * @param out
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/addGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void addGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		this.setParams(paramsMap, request);
		int operationNum = goodsSeoInfoService.operateGoodsSeoInfo(paramsMap);
		
		out.write(binder.toJson(operationNum));
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 * @Description: 修改SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午1:44:55
	 * @param out
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/updateGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void updateGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		this.setParams(paramsMap, request);
		int operationNum = goodsSeoInfoService.operateGoodsSeoInfo(paramsMap);
		
		out.write(binder.toJson(operationNum));
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 * @Description: 将退回状态的SEO信息提交审核
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午1:44:55
	 * @param out
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/submitGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void submitGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		this.setParams(paramsMap, request);
		int operationNum = goodsSeoInfoService.operateGoodsSeoInfo(paramsMap);
		
		out.write(binder.toJson(operationNum));
	}
	
	/**
	 * 
	 * @Description: 设置参数 
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月23日 下午3:35:00
	 * @param paramsMap
	 * @param request 
	 * @throws
	 */
	public void setParams(Map<String, Object> paramsMap, HttpServletRequest request){
		paramsMap.put("updateFlag", request.getParameter("updateFlag"));
		paramsMap.put("seoStatus", request.getParameter("seoStatus"));
		paramsMap.put("seoId", request.getParameter("seoId"));
		paramsMap.put("goodsId", request.getParameter("goodsId"));
		paramsMap.put("title", request.getParameter("seoTitle"));
		paramsMap.put("keywords", request.getParameter("seoKeywords"));
		paramsMap.put("keywordsDesc", request.getParameter("seoKeywordsDesc"));
		paramsMap.put("loginUserId", getLoginUserId(request));
	}
	
	
	/**
	 * 
	 * @Description: 校验是否有需要删除的seo信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午3:21:01
	 * @param out
	 * @param request
	 * @throws IOException 
	 * @throws
	 */
	@RequestMapping(value = "/validGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void validGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		Map<String, Object> dataMap = goodsSeoInfoService.getGoodSeoInfoByGoodsId(Integer.parseInt(request.getParameter("goodsId")));
		GoodsSeoInfo seoInfo = (GoodsSeoInfo) dataMap.get("seoInfo");
		boolean hasSeoFlag = false;
		if(seoInfo!=null){
			hasSeoFlag = true;
		}
		out.write(binder.toJson(hasSeoFlag));
	}
	
	
	/**
	 * 
	 * @Description: 删除SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午2:21:41
	 * @param out
	 * @param request
	 * @throws IOException 
	 * @throws
	 */
	@RequestMapping(value = "/deleteGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void deleteGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		int deleteNum = goodsSeoInfoService.deleteGoodsSeoInfoByGoodsId(Integer.parseInt(request.getParameter("goodsId")));
		out.write(binder.toJson(deleteNum));
	}
	
	
	
	/**
	 * 
	 * @Description: 审核发布SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月22日 下午1:42:09
	 * @param out
	 * @param request
	 * @throws IOException 
	 * @throws
	 */
	@RequestMapping(value = "/auditGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void auditGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		
		GoodsSeoInfo seoInfo = new GoodsSeoInfo();
		seoInfo.setId(Integer.parseInt(request.getParameter("seoId")));
		seoInfo.setStatus(GoodsSeoStatusEnum.RELEASED.getValue());
		seoInfo.setAuditUser(getLoginUserId(request));
		seoInfo.setAuditTime(DateUtil.getNow());
		seoInfo.setUpdateUser(getLoginUserId(request));
		
		int auditNum = goodsSeoInfoService.update(seoInfo);
		
		out.write(binder.toJson(auditNum));
	}
	
	
	
	/**
	 * 
	 * @Description: 退回SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月22日 下午2:50:58
	 * @param out
	 * @param request
	 * @throws IOException 
	 * @throws
	 */
	@RequestMapping(value = "/sendBackGoodsSeoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void sendBackGoodsSeoInfo(Writer out, HttpServletRequest request) throws IOException {
		
		GoodsSeoInfo seoInfo = new GoodsSeoInfo();
		seoInfo.setId(Integer.parseInt(request.getParameter("seoId")));
		seoInfo.setStatus(GoodsSeoStatusEnum.SEND_BACK.getValue());
		seoInfo.setAuditUser(getLoginUserId(request));
		seoInfo.setAuditTime(DateUtil.getNow());
		seoInfo.setBackReason(request.getParameter("seoBackReason"));
		seoInfo.setUpdateUser(getLoginUserId(request));
		
		int auditNum = goodsSeoInfoService.update(seoInfo);
		
		out.write(binder.toJson(auditNum));
	}
	
	
	/**
	 * @Description: 设置页面页数
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月17日 上午10:30:21
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
