package com.iotekclass.cms.web.controller.homepage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.homepage.FriendlyLink;
import com.iotekclass.cms.service.files.FilesService;
import com.iotekclass.cms.service.homepage.FriendlyLinkService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.FriendlyLinkOpenWayEnum;
import com.iotekclass.common.constants.enums.FriendlyLinkTypeEnum;
import com.iotekclass.common.util.DateUtil;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.common.util.upyun.UPYunUtil;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: FriendlyLinkController   
 * @Description： 友情链接控制器
 * @Author：hujing
 * @Date：2015年7月14日 上午11:01:41
 * @version
 */
@Controller
@RequestMapping(value = "/friendlyLinkManage")
public class FriendlyLinkController extends BaseController {

	@Reference(version = "1.0.0")
	private FriendlyLinkService friendlyLinkService;
	@Reference(version = "1.0.0")
	private FilesService filesService;

	private static final int PAGE_SIZE = 10;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page<FriendlyLink> page = new Page(PAGE_SIZE);

	/**
	 * 
	 * @Description: 友情链接列表页
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:19:14
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {
		model.addAttribute("friendlyLinkTypeEnum",
				EnumSet.of(FriendlyLinkTypeEnum.WORD_URL, FriendlyLinkTypeEnum.PICTURE_URL));
		return "homepage/friendly_link_list";
	}

	/**
	 * @throws IOException 
	 * 
	 * @Description: 友情链接筛选
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:19:36
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/indexSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public void indexSearch(Writer out, HttpServletRequest request) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", request.getParameter("name"));
		params.put("type", request.getParameter("type"));
		setPageNo(request.getParameter("pageNo"));
		page = friendlyLinkService.getByParams(params, page);
		params.put("page", page);
		String json = binder.toJson(params);
		out.write(json);
	}

	/**
	 * 
	 * @Description: 进入添加友情链接页面
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:20:02
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/toAddLink", method = { RequestMethod.GET, RequestMethod.POST })
	public String toAddLink(Model model) {
		model.addAttribute("friendlyLinkTypeEnum",
				EnumSet.of(FriendlyLinkTypeEnum.WORD_URL, FriendlyLinkTypeEnum.PICTURE_URL));
		model.addAttribute("friendlyLinkOpenWayEnum",
				EnumSet.of(FriendlyLinkOpenWayEnum.NEW_DIALOG, FriendlyLinkOpenWayEnum.NOW_DIALOG));
		return "/homepage/add_friendly_link";
	}

	/**
	 * 
	 * @Description: 添加友情链接
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:20:35
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addLink", method = { RequestMethod.GET, RequestMethod.POST })
	public String addLink(HttpServletRequest request) {
		FriendlyLink friendlyLink = new FriendlyLink();
		friendlyLink.setName(StringUtil.html(request.getParameter("name")));
		friendlyLink.setOpenWay(StringUtil.isEmpty(request.getParameter("openWay")) ? 0 : Integer.parseInt(request
				.getParameter("openWay")));
		friendlyLink.setTitle(request.getParameter("title"));
		friendlyLink.setType(StringUtil.isEmpty(request.getParameter("type")) ? 0 : Integer.parseInt(request
				.getParameter("type")));
		friendlyLink.setUrl(request.getParameter("url"));
		//System.out.println(request.getParameter("fileId"));
		friendlyLink.setFilePath(request.getParameter("filePath"));
		friendlyLink.setCreateUser(this.getLoginUserId(request));
		friendlyLinkService.save(friendlyLink);
		return "redirect:/friendlyLinkManage/index";
	}

	/**
	 * @throws IOException 
	 * 
	 * @Description: 校验用户名唯一性
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:20:54
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/validLinkName", method = { RequestMethod.GET, RequestMethod.POST })
	public void validLinkName(Writer out, @RequestParam("name") String name) throws IOException {
		FriendlyLink friendlyLink = friendlyLinkService.getByName(name);
		if (friendlyLink != null) {
			String json = binder.toJson("200");
			out.write(json);
		}
	}

	/**
	 * 上传链接图片
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/uploadLinkImage", method = { RequestMethod.GET, RequestMethod.POST })
	public void uploadLinkImage(Writer out, @RequestParam("imgFile") MultipartFile file) throws IOException {
		try {
			StringBuilder remotePath = new StringBuilder();
			String filename = file.getOriginalFilename();
			// 远程全路径
			remotePath.append(UPYunUtil.DIR_ROOT).append("iotek2").append(UPYunUtil.DIR_ROOT).append("friendly_link")
					.append(UPYunUtil.DIR_ROOT).append(DateUtil.getCurrentDateSString()).append(UPYunUtil.DIR_ROOT)
					.append(filename);

			File files = convertMultipartFileToIoFile(file);
			logger.debug("上传的文件是否存在: "+files.exists());
			logger.debug("上传的文件路径: "+files.getAbsolutePath());
			logger.debug("上传的文件大小: "+files.length());
			boolean flag = UPYunUtil.uploadImage(remotePath.toString(), convertMultipartFileToIoFile(file));
			logger.debug("准备上传文件: " + remotePath);
			if (flag) {
				logger.debug("上次文件成功!");
				String json = binder.toJson("totalPath:" + remotePath);
				out.write(json);
			} else {
				logger.debug("上次文件失败!");
				out.write(binder.toJson("500"));
			}
		} catch (Exception e) {
			logger.debug("上次文件失败!");
			out.write(binder.toJson("500"));
		}

	}

	/**
	 * 
	 * @Description: 进入详情页
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:20:02
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/toDetailLink", method = { RequestMethod.GET, RequestMethod.POST })
	public String toDetailLink(Model model, @RequestParam("id") int id) {
		model.addAttribute("friendlyLinkTypeEnum",
				EnumSet.of(FriendlyLinkTypeEnum.WORD_URL, FriendlyLinkTypeEnum.PICTURE_URL));
		model.addAttribute("friendlyLinkOpenWayEnum",
				EnumSet.of(FriendlyLinkOpenWayEnum.NEW_DIALOG, FriendlyLinkOpenWayEnum.NOW_DIALOG));
		FriendlyLink friendlyLink = friendlyLinkService.getById(id);
		if (friendlyLink != null) {
			String totalPath = "";
			if (friendlyLink.getType() == 2) {
				totalPath = friendlyLink.getFilePath();
				/*Files files = filesService.getById(friendlyLink.getFileId());
				if(files != null){
					totalPath = files.getFilePathUrl()+files.getFileName()+"."+files.getFileFormat();
				}*/
			}
			model.addAttribute("totalPath", totalPath);
		}
		model.addAttribute("friendlyLink", friendlyLink);
		return "/homepage/detail_friendly_link";
	}

	/**
	 * @throws IOException 
	 * 
	 * @Description: 通过id获取该链接信息（是否存在）
	 * @Author：hujing
	 * @Date：2015年7月13日 上午9:51:24
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/findById", method = { RequestMethod.GET, RequestMethod.POST })
	public void findById(Writer out, @RequestParam("id") int id) throws IOException {
		FriendlyLink friendlyLink = friendlyLinkService.getById(id);
		if (friendlyLink != null) {
			String json = binder.toJson("200");
			out.write(json);
		}
	}

	/**
	 * 
	 * @Description: 更新友情链接
	 * @Author：hujing
	 * @Date：2015年7月13日 上午10:30:53
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/updateLink", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateLink(HttpServletRequest request) {
		FriendlyLink friendlyLink = new FriendlyLink();
		friendlyLink.setId(StringUtil.isEmpty(request.getParameter("id")) ? 0 : Integer.parseInt(request
				.getParameter("id")));
		friendlyLink.setName(StringUtil.html(request.getParameter("name")));
		friendlyLink.setOpenWay(StringUtil.isEmpty(request.getParameter("openWay")) ? 0 : Integer.parseInt(request
				.getParameter("openWay")));
		friendlyLink.setTitle(request.getParameter("title"));
		friendlyLink.setType(StringUtil.isEmpty(request.getParameter("type")) ? 0 : Integer.parseInt(request
				.getParameter("type")));
		friendlyLink.setUrl(request.getParameter("url"));
		friendlyLink.setFilePath(request.getParameter("filePath"));
		//friendlyLink.setCreateUser(this.getLoginUserId(request));
		//friendlyLink.setUpdateTime(new Date());
		friendlyLink.setUpdateUser(this.getLoginUserId(request));
		friendlyLinkService.update(friendlyLink);
		return "redirect:/friendlyLinkManage/index";
	}

	/**
	 * @throws IOException 
	 * 
	 * @Description: 删除友情链接
	 * @Author：hujing
	 * @Date：2015年7月13日 上午10:30:53
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/toDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public void toDelete(Writer out, @RequestParam("id") int id) throws IOException {
		friendlyLinkService.delete(id);
		out.write("200");
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
