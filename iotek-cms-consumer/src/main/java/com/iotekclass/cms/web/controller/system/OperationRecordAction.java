package com.iotekclass.cms.web.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.permission.ManageOperationRecord;
import com.iotekclass.cms.service.permission.ManageOperationRecordService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: OperationRecordAction   
 * @Description： 后台操作日志action
 * @Author：hujing
 * @Date：2015年6月19日 下午2:39:12
 * @version
 */
@Controller
@RequestMapping(value="/systemManage")
public class OperationRecordAction extends BaseController {

	/**
	 * 显示条数
	 */
	private static final int PAGE_SIZE = 10;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page<ManageOperationRecord> page = new Page(PAGE_SIZE);

	private String username;
	/**
	 * 密码
	 */
	private String startTime;
	/**
	 * 备注
	 */
	private String endTime;

	@Reference(version = "1.0.0")
	ManageOperationRecordService opetarionRecordService;

	/**
	 * 
	 * @Description: 操作日志列表页
	 * @Author：hujing
	 * @Date：2015年4月28日 下午1:59:34
	 * @return
	 * @throws
	 */
//	@Action(value = "operationRecordInfo", results = { @Result(name = SUCCESS, location = "/web/pages/system/operation_record.jsp") })
	@RequestMapping(value="/operationRecordInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String operationRecordInfo() {
		return "/system/operation_record";
	}

	/**
	 * 
	 * @Description: 按条件查询列表页
	 * @Author：hujing
	 * @Date：2015年6月19日 下午4:04:00
	 * @return
	 * @throws
	 */
//	@Action(value="searchOperationRecord",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/searchOperationRecord", method = { RequestMethod.GET, RequestMethod.POST })
	public void search(Writer writer,HttpServletRequest request) {
		dealJspParams(request);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		page= opetarionRecordService.getAllByParamsPage(params, page);
		try {
			writer.write(objToJsonData("page", page));
		} catch (IOException e) {
			e.printStackTrace();
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

	/**
	 * 简单处理页面传递过来的参数
	 * @param request
	 */
	private void dealJspParams(HttpServletRequest request) {
		username = convertStr(request.getParameter("username"));
		startTime = convertStr(request.getParameter("startTime"));
		endTime = convertStr(request.getParameter("endTime"));
		String pageNo = request.getParameter("pageNo");
		if (!StringUtil.isEmpty(pageNo)) {
			setPageNo(convertStr(request.getParameter("pageNo")));
		}
	}
}
