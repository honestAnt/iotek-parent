package com.iotekclass.cms.web.controller.information;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.homepage.RecommendPosition;
import com.iotekclass.cms.model.information.Information;
import com.iotekclass.cms.model.informationsort.InformationSort;
import com.iotekclass.cms.service.homepage.RecommendPositionService;
import com.iotekclass.cms.service.information.InformationService;
import com.iotekclass.cms.service.informationsort.InformationSortService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.InformationStatusEnum;
import com.iotekclass.common.util.Config;
import com.iotekclass.common.util.ConvertCharToLabel;
import com.iotekclass.common.util.DateUtil;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.common.util.upyun.UPYunUtil;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName: InformationController   
 * @Description： 资讯相关操作：添加资讯   
 * @Author：袁亚明
 * @Date：2015年7月13日 下午5:40:28
 * @version
 */

@Controller
@RequestMapping(value="/information")
public class InformationController extends BaseController{

	@Reference(version = "1.0.0")
	private InformationService informationService;

	@Reference(version = "1.0.0")
	RecommendPositionService recommendPositionService;

	@Reference(version = "1.0.0")
	InformationSortService informationSortService;
	
	private Page<Information> page = new Page<Information>(5);
	
	public void setPage(Page<Information> page) {
		this.page = page;
	}
	
	
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
	
	
	/**
	 * 
	 * @Description: 审核按钮，转到审核页面
	 * @Author：袁亚明
	 * @Date：2015年7月24日 上午9:18:59
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/reviewAddInformation", method={RequestMethod.GET,RequestMethod.POST})
	public String reviewAddInformation(int id,int statusType,Model model){
		List<InformationSort> list = informationSortService.getByParentId(0);
		Information infor = informationService.getById(id);
		List<RecommendPosition> recommendPosition =  recommendPositionService.getAll();
		model.addAttribute("recommendPosition", recommendPosition);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("infor", infor);
		model.addAttribute("statusType", statusType);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		String strDate = null;
		if(id!=0){
			strDate = sdf.format(infor.getDeliveryTime());
		}
		model.addAttribute("deliveryTime", strDate);
		return "/information/addinformation";
	}
	
	/**
	 * 
	 * @Description: 初始化添加咨询，加载一级分类
	 * @Author：袁亚明
	 * @Date：2015年7月14日 下午3:57:06
	 * @param model
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addInformation", method={RequestMethod.GET,RequestMethod.POST})
	public String addInformation(int id,int statusType,Model model){
		List<InformationSort> list = informationSortService.getByParentId(0);
		Information infor = informationService.getById(id);
		List<RecommendPosition> recommendPosition =  recommendPositionService.getAll();
		model.addAttribute("recommendPosition", recommendPosition);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("infor", infor);
		model.addAttribute("statusType", statusType);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		String strDate = null;
		if(id!=0){
			strDate = sdf.format(infor.getDeliveryTime());
		}
		model.addAttribute("deliveryTime", strDate);
		return "/information/addinformation";
	}
	
	
	/**
	 * 
	 * @Description: 初始化编辑咨询，加载一级分类
	 * @Author：袁亚明
	 * @Date：2015年7月14日 下午3:57:06
	 * @param model
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/editInformation", method={RequestMethod.GET,RequestMethod.POST})
	public String editInformation(int id,int statusType,Model model){
		List<InformationSort> list = informationSortService.getByParentId(0);
		Information infor = informationService.getById(id);
		List<RecommendPosition> recommendPosition =  recommendPositionService.getAll();
		model.addAttribute("recommendPosition", recommendPosition);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("infor", infor);
		model.addAttribute("statusType", statusType);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		String strDate = null;
		if(id!=0){
			strDate = sdf.format(infor.getDeliveryTime());
		}
		model.addAttribute("deliveryTime", strDate);
		return "/information/addinformation";
	}
	
	/**
	 * 
	 * @Description: 根据一级分类的选择，加载二级分类
	 * @Author：袁亚明
	 * @Date：2015年7月14日 下午3:56:42
	 * @param levelId
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/secondLevel", method={RequestMethod.GET,RequestMethod.POST})
	public Map<String,Object> secondLevel(@RequestParam("levelId") int levelId){
		List<InformationSort> list = informationSortService.getByParentId(levelId);
		Map<String,Object> modelMap = new HashMap<String,Object>();
		modelMap.put("list", list);
		return modelMap;
	}
	
	
	/**
	 * 
	 * @Description: 保存咨询信息
	 * @Author：袁亚明
	 * @Date：2015年7月14日 下午4:54:15
	 * @param information
	 * @throws
	 */
	@RequestMapping(value = "/saveInformation", method={RequestMethod.GET,RequestMethod.POST})
	public String saveInformation(@ModelAttribute Information information,
								@RequestParam("deliveryTime_Param") String deliveryTime,HttpServletRequest request){
		int userId = getLoginUserId(request);
		information.setCreateUser(userId);
		if(deliveryTime.equals("")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			deliveryTime=sdf.format(new Date()); 
		}
		information.setDeliveryTime(DateUtil.string2Date(deliveryTime));		
		String titleTemp = StringUtil.html(information.getTitle());
		information.setTitle(titleTemp);
		information.setKeywords(StringUtil.html(information.getKeywords()));
		information.setSource(StringUtil.html(information.getSource()));
		information.setDescription(StringUtil.html(information.getDescription()));
		
		information.setStatus(InformationStatusEnum.APPROVING.getValue());
		if (!StringUtil.isEmpty(information.getContent())) {
			String content = information.getContent();
			content = ConvertCharToLabel.toLabel(content); 
		    content = content.replace("'", "&apos;");
			content = content.replace("\n\r", "");
			content = content.replace("\r\n", "");//这才是正确的！
			content = content.replace("\t", "");
			information.setContent(content);
		}
		
		String tempContent = information.getContent().replace("<script>", "&lt;script&gt;");
		tempContent = tempContent.replace("</script>", "&lt;/script&gt;");
		information.setContent(tempContent);
		informationService.saveInformation(information);
		return "redirect:/information/informationListSearch";
	}
	
	
	
	/**
	 * 
	 * @Description: 更新资讯信息
	 * @Author：袁亚明
	 * @Date：2015年7月14日 下午4:54:15
	 * @param information
	 * @throws
	 */
	@RequestMapping(value = "/updateInformation", method={RequestMethod.GET,RequestMethod.POST})
	public String updateInformation(@ModelAttribute Information information,
								@RequestParam("deliveryTime_Param") String deliveryTime,HttpServletRequest request){
		int userId = getLoginUserId(request);
		information.setCreateUser(userId);
		if(deliveryTime.equals("")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			deliveryTime=sdf.format(new Date()); 
		}
		information.setDeliveryTime(DateUtil.string2Date(deliveryTime));		
		String titleTemp = StringUtil.html(information.getTitle());
		information.setTitle(titleTemp);
		information.setKeywords(StringUtil.html(information.getKeywords()));
		information.setSource(StringUtil.html(information.getSource()));
		information.setDescription(StringUtil.html(information.getDescription()));
		
		information.setStatus(InformationStatusEnum.APPROVING.getValue());
		if (!StringUtil.isEmpty(information.getContent())) {
			String content = information.getContent();
			content = ConvertCharToLabel.toLabel(content); 
		    content = content.replace("'", "&apos;");
			content = content.replace("\n\r", "");
			content = content.replace("\r\n", "");//这才是正确的！
			content = content.replace("\t", "");
			information.setContent(content);
		}
		
		String tempContent = information.getContent().replace("<script>", "&lt;script&gt;");
		tempContent = tempContent.replace("</script>", "&lt;/script&gt;");
		information.setContent(tempContent);
		if(information.getId()>0){
			information.setUpdateUser(userId);
			informationService.update(information);
		}
		return "redirect:/information/informationListSearch";
	}
	
	/**
	 * 
	 * @Description: 资讯列表点击，转到查询
	 * @Author：袁亚明
	 * @Date：2015年7月24日 上午9:18:59
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/informationListIn", method={RequestMethod.GET,RequestMethod.POST})
	public String informationListIn(@RequestParam(value="firstLevelId",required=false) Integer firstLevelId
			,@RequestParam(value="secondLevelId",required=false) Integer secondLevelId,
			@RequestParam(value="status",required=false) Integer status,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="beginTime",required=false) String beginTime,
			@RequestParam(value="endTime",required=false) String endTime,Model model,
			HttpServletRequest request){
		setPageNo(request.getParameter("pageNo"));
		page.setPageSize(10);
		//分类一
		List<InformationSort> list = informationSortService.getByParentId(0);
		if(status==null){
			status=1;
		}
		page = informationService.informationSearch(firstLevelId,secondLevelId,status,title,beginTime,endTime,page);
		request.setAttribute("page", page);
		model.addAttribute("status", status);
		model.addAttribute("title", title);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("firstLevelId", firstLevelId);
		model.addAttribute("secondLevelId", secondLevelId);
		return "/information/informationList";
	} 
	
	/**
	 * 
	 * @Description: 资讯列表进入，通过搜索条件查询
	 * @Author：袁亚明
	 * @Date：2015年7月15日 上午10:13:01
	 * @param firstLevelId
	 * @param second_level_id
	 * @param status
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/informationListSearch", method={RequestMethod.GET,RequestMethod.POST})
	public String informationList(@RequestParam(value="firstLevelId",required=false) Integer firstLevelId
			,@RequestParam(value="secondLevelId",required=false) Integer secondLevelId,
			@RequestParam(value="status",required=false) Integer status,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="beginTime",required=false) String beginTime,
			@RequestParam(value="endTime",required=false) String endTime,Model model,
			HttpServletRequest request){
		setPageNo(request.getParameter("pageNo"));
		page.setPageSize(10);
		//分类一
		List<InformationSort> list = informationSortService.getByParentId(0);
		if(status==null){
			status=1;
		}
		page = informationService.informationSearch(firstLevelId,secondLevelId,status,title,beginTime,endTime,page);
		request.setAttribute("page", page);
		model.addAttribute("status", status);
		model.addAttribute("title", title);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("firstLevelId", firstLevelId);
		model.addAttribute("secondLevelId", secondLevelId);
		return "/information/informationList";
	}
	
	
	/**
	 * 
	 * @Description: 审核列表点击，转到查询
	 * @Author：袁亚明
	 * @Date：2015年7月24日 上午9:18:59
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/informationListReviewIn", method={RequestMethod.GET,RequestMethod.POST})
	public String informationListReviewIn(@RequestParam(value="firstLevelId",required=false) Integer firstLevelId
			,@RequestParam(value="secondLevelId",required=false) Integer secondLevelId,
			@RequestParam(value="status",required=false) Integer status,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="beginTime",required=false) String beginTime,
			@RequestParam(value="endTime",required=false) String endTime,Model model,
			HttpServletRequest request){
		setPageNo(request.getParameter("pageNo"));
		page.setPageSize(10);
		//分类一
		List<InformationSort> list = informationSortService.getByParentId(0);
		status = InformationStatusEnum.APPROVING.getValue();
		page = informationService.informationSearch(firstLevelId,secondLevelId,status,title,beginTime,endTime,page);
		request.setAttribute("page", page);
		model.addAttribute("status", status);
		model.addAttribute("title", title);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("firstLevelId", firstLevelId);
		model.addAttribute("secondLevelId", secondLevelId);
		return "/information/reviewInformationList";
	} 
	
	
	/**
	 * 
	 * @Description: 审核列表进入，通过搜索条件查询
	 * @Author：袁亚明
	 * @Date：2015年7月15日 上午10:13:01
	 * @param firstLevelId
	 * @param second_level_id
	 * @param status
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/informationListReview", method={RequestMethod.GET,RequestMethod.POST})
	public String informationListReview(@RequestParam(value="firstLevelId",required=false) Integer firstLevelId
			,@RequestParam(value="secondLevelId",required=false) Integer secondLevelId,
			@RequestParam(value="status",required=false) Integer status,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="beginTime",required=false) String beginTime,
			@RequestParam(value="endTime",required=false) String endTime,Model model,
			HttpServletRequest request){
		setPageNo(request.getParameter("pageNo"));
		page.setPageSize(10);
		//分类一
		List<InformationSort> list = informationSortService.getByParentId(0);
		status = InformationStatusEnum.APPROVING.getValue();
		page = informationService.informationSearch(firstLevelId,secondLevelId,status,title,beginTime,endTime,page);
		request.setAttribute("page", page);
		model.addAttribute("status", status);
		model.addAttribute("title", title);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("firstLevelList", list);
		model.addAttribute("firstLevelId", firstLevelId);
		model.addAttribute("secondLevelId", secondLevelId);
		return "/information/reviewInformationList";
	}
	
	
	/**
	 * 
	 * @Description: 删除一条资讯
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:19:23
	 * @param id
	 * @param status
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteInformation", method={RequestMethod.GET,RequestMethod.POST})
	public int deleteInformation(@RequestParam("id") int id,@RequestParam("status") int status){
		informationService.delete(id);
		return status;
	}
	
	
	/**
	 * 
	 * @Description: 更改状态
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:18:49
	 * @param id
	 * @param status
	 * @param returnReason
	 * @param request
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/reviewInformation", method={RequestMethod.GET,RequestMethod.POST})
	public void releaseInformation(@RequestParam("id") int id,@RequestParam("status") int status,
										@RequestParam(value="returnReason",required=false) String returnReason,HttpServletRequest request){
		int userId = getLoginUserId(request);
		informationService.updateByStatus(id,status,returnReason,userId);
	}
	
	
	/**
	 * 
	 * @Description: 富文本编辑框，上传图片
	 * @Author：袁亚明
	 * @Date：2015年7月20日 下午1:01:11
	 * @return
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping (value = "/uploadPicture")
	public void uploadPicture(@RequestParam("imgFile") MultipartFile file,HttpServletResponse response) {
		File imgFile = convertMultipartFileToIoFile(file);
		String imgFileFileName = file.getOriginalFilename();
		try {		
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			//图片存放地址
			StringBuffer remotePath = new StringBuffer();
			//获取文件扩展名
			String suffix = StringUtil.getFileSuffix(imgFileFileName);
			if (imgFile.length() > (1024 * 1024 * 3)) {
				JSONObject obj = new JSONObject();
				obj.put("error", 1);
				obj.put("message", "上传的文件不能超过3M，请重新上传");
				this.outHtmlString(obj.toJSONString(),response);
			} else if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(suffix)) {
				JSONObject obj = new JSONObject();
				obj.put("error", 1);
				obj.put("message", "只允许" + extMap.get("image") + "格式。");
				this.outHtmlString(obj.toJSONString(),response);
			} else {
				
				// 远程全路径
				remotePath.append(UPYunUtil.DIR_ROOT).append(UPYunUtil.UPYUN_IOTEK2_DIR).append(UPYunUtil.DIR_ROOT)
					.append("informationcms").append(UPYunUtil.DIR_ROOT).append(DateUtil.getCurrentDateSString()).append('.').append(suffix);

				boolean flag = UPYunUtil.uploadImage(remotePath.toString(), imgFile);
				
				if (flag) {
					logger.debug("上传图片成功");
				}

 				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", Config.getImgSuffix() + remotePath.toString());
				this.outHtmlString(obj.toJSONString(),response);
			}
		} catch (Exception e) {
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("message", "系统异常，请重试");
			this.outHtmlString(obj.toJSONString(),response);
		}
	}
	
	/**
	 * 
	 * @Description: 根据Id查询文章内容-资讯
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午5:49:19
	 * @param id
	 * @return
	 * @throws
	 */
	
	@RequestMapping("/previewInformation")
	public String previewInformation(@RequestParam("id") int id,Model model){
		Information information = informationService.getById(id);
		List<String> list  = new ArrayList<String>();
		list = Arrays.asList(information.getContent().split("【page】"));
		model.addAttribute("contentList", list);
		model.addAttribute("information", information);
		return "/information/preview_information";
	}
	
	
	/**
	 * 
	 * @Description: 根据Id查询文章内容-审核
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午5:49:19
	 * @param id
	 * @return
	 * @throws
	 */
	
	@RequestMapping("/previewInformationReview")
	public String previewInformationReview(@RequestParam("id") int id,Model model){
		Information information = informationService.getById(id);
		List<String> list  = new ArrayList<String>();
		list = Arrays.asList(information.getContent().split("【page】"));
		model.addAttribute("contentList", list);
		model.addAttribute("information", information);
		return "/information/preview_information";
	}
	
	
	/**
	 * 
	 * @Description: 输出流
	 * @Author：袁亚明
	 * @Date：2015年7月20日 下午4:12:08
	 * @param str
	 * @param response
	 * @throws
	 */
	public void outHtmlString(String str,HttpServletResponse response){
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
