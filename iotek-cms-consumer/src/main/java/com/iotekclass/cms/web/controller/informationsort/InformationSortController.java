package com.iotekclass.cms.web.controller.informationsort;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.informationsort.InformationSort;
import com.iotekclass.cms.service.informationsort.InformationSortService;
import com.iotekclass.cms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: InformationSortController
 * @Description： 资讯分类 Controller
 * @Author：张帅
 * @Date：2015年7月14日 上午10:14:23
 * @version
 */
@Controller
@RequestMapping(value = "/informationSort")
public class InformationSortController extends BaseController {

	private int sortNum = 0;
	@Autowired
	private HttpServletRequest request; // 这里可以获取到request

	private InformationSort informationSort = new InformationSort();

	@Reference(version = "1.0.0")
	private InformationSortService informationSortService;

	// 标识操作状态
	private String code;

	/**
	 * @Description: 分类列表页
	 * @Author：张帅
	 * @Date：2015年7月14日 下午1:58:51
	 * @param model
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/infoSort", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String infoSort() {
		return "informationsort/information_sort";
	}

	/**
	 * @Description:分类列表
	 * @Author：张帅
	 * @Date：2015年7月16日 下午5:22:54
	 * @param out
	 * @throws IOException
	 * @throws
	 */
	@RequestMapping(value = "/infoSortJosn", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void infoSortJosn(Writer out) throws IOException {
		List<Map<String, Object>> list = informationSortService.getInfoSort();
		String json = binder.toJson(list);
		out.write(json);
	}

	/**
	 * @throws IOException
	 * @Description: 验证分类名称是否存在
	 * @Author：张帅
	 * @Date：2015年7月15日 上午11:12:54
	 * @param out
	 * @throws IOException
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/verifySortName", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String verifySortName(
			@RequestParam(value = "sortName") String sortName) {
		String code = "";

		informationSort = informationSortService.verifySortName(sortName);

		if (informationSort != null) {
			code = "200";
		} else {
			code = "500";
		}
		return code;
	}

	/**
	 * 
	 * @Description: 根据Id返回分类对象
	 * @Author：张帅
	 * @Date：2015年7月16日 下午5:22:33
	 * @param id
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfoSortById", method = { RequestMethod.GET,
			RequestMethod.POST })
	public InformationSort getInfoSortById(@RequestParam(value = "id") int id) {

		informationSort = informationSortService.getById(id);

		return informationSort;

	}

	/**
	 * @Description: 新增分类
	 * @Author：张帅
	 * @Date：2015年7月14日 下午3:53:50
	 * @param informationSort
	 * @param model
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/addInfoSort", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addInfoSort(@ModelAttribute InformationSort informationSort) {
		informationSort.setSortNumber(++sortNum);
		informationSort.setCreateUser(getLoginUserId(request));
		int i = informationSortService.save(informationSort);
		code = (i == 1) ? "200" : "500";
		return code;
	}

	 
	/**
	 * @Description: 返回查询所有分类JSON数据
	 * @Author：张帅
	 * @Date：2015年7月16日 下午5:21:56
	 * @param parentId
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/infoSortList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<InformationSort> infoSortList(
			@RequestParam(value = "parentId") int parentId) {
		List<InformationSort> infoSortList = informationSortService
				.getByParentId(parentId);
		return infoSortList;
	}

	/**
	 * @Description:修改分类
	 * @Author：张帅
	 * @Date：2015年7月14日 下午3:54:12
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/updInfoSort", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String updInfoSort(@ModelAttribute InformationSort informationSort) {

		informationSort.setUpdateTime(new Date());
		informationSort.setUpdateUser(getLoginUserId(request));
		int i= informationSortService.update(informationSort);
		code = (i == 1) ? "200" : "500";
		return code;
	}

	/**
	 * 
	 * 
	 * @Description: 删除
	 * @Author：张帅
	 * @Date：2015年7月14日 下午3:54:25
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delInfoSort", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String delInfoSort(@RequestParam(value = "id") int id) {

		// 查询子节点数
		List<InformationSort> listInfo = informationSortService
				.getByParentId(id);
		// 引用数量
		int count = informationSortService.countRefById(id);

		if (listInfo.size() > 0) {// 删除的为父节点，并且其有子节点
			code = "500";
		} else if (count > 0) {// 节点已经有资讯引用
			code = "505";
		} else {
			informationSortService.delete(id);
			code = "200";
		}
		return code;
	}
}
