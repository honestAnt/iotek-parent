/**
 * 
 */
package com.iotekclass.cms.web.controller.banner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.banner.HomeBanner;
import com.iotekclass.cms.service.banner.HomeBannerService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.HomeBannerEnableEnum;
import com.iotekclass.common.util.DateUtil;
import com.iotekclass.common.util.MD5Util;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.common.util.upyun.UPYunUtil;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HomeBannerController   
 * @Description： cms首页广告位管理
 * @Author：王凤宝
 * @Date：2015年8月3日 上午10:49:19
 * @version
 */
@Controller
@RequestMapping(value="/banner")
public class HomeBannerController extends BaseController {
	
	@Reference(version = "1.0.0")
	HomeBannerService homeBannerService;
	
	private static final int PAGE_SIZE = 5;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page<HomeBanner> page = new Page(PAGE_SIZE);
	
	/**
	 * 
	 * @Description: 去广告列表页
	 * @Author：王凤宝
	 * @Date：2015年8月3日 上午10:51:04
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/bannerList")
	public String bannerList(Model model) {
		model.addAttribute("typeList", EnumSet.of(
				HomeBannerEnableEnum.AVAILABLE,HomeBannerEnableEnum.UNAVAILABLE));
		setPageNo("0");
		model.addAttribute("page", homeBannerService.getPageByParams(null, page));
		return "/banner/banner_list";
	}
	
	/**
	 * 
	 * @Description: 广告列表页搜索
	 * @Author：王凤宝
	 * @Date：2015年8月3日 上午10:51:04
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/searchBannerList")
	public String searchBannerList(HttpServletRequest request,Model model) {
		model.addAttribute("typeList", EnumSet.of(
				HomeBannerEnableEnum.AVAILABLE,HomeBannerEnableEnum.UNAVAILABLE));
		String name = request.getParameter("name");
		String enabled = request.getParameter("enabled");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "%"+name+"%");
		map.put("enabled", enabled);
		model.addAttribute("name", name);
		model.addAttribute("enabled", enabled);
		if (!StringUtil.isEmpty(name) 
				|| !"0".equals(enabled)) {
			setPageNo("");
		} else {
			setPageNo(request.getParameter("pageNo"));
		}
		model.addAttribute("page", homeBannerService.getPageByParams(map, page));
		return "/banner/banner_list";
	}
	
	/**
	 * 
	 * @Description: 去广告添加页
	 * @Author：王凤宝
	 * @Date：2015年8月3日 上午10:51:04
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/toAddBanner")
	public String toAddBanner(HttpServletRequest request,Model model) {
		model.addAttribute("typeList", EnumSet.of(
				HomeBannerEnableEnum.AVAILABLE,HomeBannerEnableEnum.UNAVAILABLE));
		model.addAttribute("page", homeBannerService.getPageByParams(null, page));
		return "/banner/add_banner";
	}
	
	/**
	 * 
	 * @Description: 去广告编辑页
	 * @Author：王凤宝
	 * @Date：2015年8月3日 上午10:51:04
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/toUpdateBanner")
	public String toUpdateBanner(HttpServletRequest request,Model model) {
		model.addAttribute("typeList", EnumSet.of(
				HomeBannerEnableEnum.AVAILABLE,HomeBannerEnableEnum.UNAVAILABLE));
		model.addAttribute("page", homeBannerService.getPageByParams(null, page));
		if (!StringUtil.isEmpty(request.getParameter("id"))) {
			model.addAttribute("homeBanner", homeBannerService.getById(Integer.parseInt(request.getParameter("id"))));
			model.addAttribute("id",request.getParameter("id"));
		} else {
			return "redirect:/banner/bannerList";
		}
		return "/banner/add_banner";
	}
	
	
	/**
	 * 
	 * @Description: 上传广告图片
	 * @Author：王凤宝
	 * @Date：2015年8月3日 上午10:51:04
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/uploadBannerImage")
	public void uploadBannerImage(@RequestParam("file") MultipartFile multipartFile,Writer out) {
		StringBuilder sb = new StringBuilder();
		String fileName = multipartFile.getOriginalFilename();
		String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
		String filePrefix = fileName.substring(0,fileName.lastIndexOf("."));
		sb.append(UPYunUtil.DIR_ROOT).append("home_banner")
		.append(UPYunUtil.DIR_ROOT).append(DateUtil.getCurrentDateSString())
		.append(UPYunUtil.DIR_ROOT).append(
				MD5Util.md5(filePrefix))
		.append(fileSuffix);
		try {
			boolean result = UPYunUtil.uploadImage(sb.toString(), convertMultipartFileToIoFile(multipartFile));
			if (result) {
				out.write(objToJsonData("imgPath", sb.toString()));
			} else {
				out.write(objToJsonData("fail", "500"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 保存信息
	 * @Author：王凤宝
	 * @Date：2015年8月3日 下午3:57:36
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/saveBannerInfo")
	public String saveBannerInfo(@ModelAttribute HomeBanner homeBanner,HttpServletRequest request) {
		
		if (homeBanner.getId() > 0) {
			homeBanner.setUpdateUser(getLoginUserId(request));
			homeBannerService.update(homeBanner);
		} else {
			homeBanner.setCreateUser(getLoginUserId(request));
			homeBannerService.save(homeBanner);
		}
		return "redirect:/banner/bannerList";
	}
	
	/**
	 * 
	 * @Description: 删除广告信息
	 * @Author：王凤宝
	 * @Date：2015年8月3日 下午3:57:36
	 * @return
	 * @throws
	 */
	@ResponseBody
	@SuppressWarnings("static-access")
	@RequestMapping(value="/delBannerInfo")
	public void delBannerInfo(@RequestParam("id") int id,HttpServletResponse response) {
		homeBannerService.delete(id);
		response.setStatus(response.SC_OK);
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
