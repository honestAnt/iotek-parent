package com.iotekclass.common.util.jxls;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * jxls工具类，根据模板生成Excel
 * 
 * @author ZengAihui
 * 
 */
@Component
public class ExcelGenerator {

	private static final String DEFAULT_SHEET_NAME = "sheet";

	/**
	 * 导出无动态表头的Excel文件
	 * <p>
	 * 参考重载的有动态表头注释
	 * </p>
	 * 
	 * @param destOutputStream
	 * @param templateInputStream
	 * @param data
	 * @param dataKey
	 * @param maxRowPerSheet
	 * @throws Exception
	 */
	public void generateExcelByTemplate(OutputStream destOutputStream, InputStream templateInputStream, List data,
			String dataKey, int maxRowPerSheet) throws Exception {
		generateExcelByTemplate(destOutputStream, templateInputStream, null, null, data, dataKey, maxRowPerSheet);
	}

	/**
	 * 通过Excel模版生成Excel文件
	 * <p>
	 * 创建Excel模版，变量类似JSP tag风格。 例如：
	 * <ul>
	 * <li>无动态表头
	 * 
	 * <pre>
	 * 序号   名称  规格  创建时间    价格
	 * &lt;jx:forEach items="${vms}" var="vm"&gt;
	 * ${vm.id} ${vm.name} ${vm.scale} ${vm.created} ${vm.price}
	 * &lt;/jx:forEach&gt;
	 * </pre>
	 * 
	 * </li>
	 * <li>有动态表头
	 * 
	 * <pre>
	 * 项目/数量/时间    &lt;jx:forEach items="${dates}" var="date"&gt;${date} &lt;/jx:forEach&gt;
	 * &lt;jx:forEach items="${itemsx}" var="item"&gt;            
	 * ${item.name}    &lt;jx:forEach items="${item.counts}" var="count"&gt; ${count}    &lt;/jx:forEach&gt;
	 * &lt;/jx:forEach&gt;
	 * </pre>
	 * 
	 * </li>
	 * </ul>
	 * 调用该方法则生成对应的Excel文件。
	 * </p>
	 * <p>
	 * 注意：dataKey不能是items, items是保留字，如果用items则会提示：Collection is null并抛出NullPointerException
	 * </p>
	 * 
	 * @param destOutputStream Excel输出流
	 * @param templateInputStream Excel模版输入流
	 * @param header 动态表头
	 * @param headerKey 表头的变量
	 * @param data 数据项
	 * @param dataKey 数据项变量
	 * @param maxRowPerSheet 每个sheet最多行数
	 * @throws Exception
	 */
	public void generateExcelByTemplate(OutputStream destOutputStream, InputStream templateInputStream, List header,
			String headerKey, List data, String dataKey, int maxRowPerSheet) throws Exception {
		Workbook workbook = null;
		List<List> splitData = null;
		Map<String, List> beanMap = Maps.newHashMap();
		List<String> sheetNames = Lists.newArrayList();
		splitData = splitList(data, maxRowPerSheet);
		sheetNames = new ArrayList<String>(splitData.size());
		for (int i = 0; i < splitData.size(); i++) {
			sheetNames.add(DEFAULT_SHEET_NAME + i);
		}
		if (header != null) {
			beanMap.put(headerKey, header);
		}
		XLSTransformer transformer = new XLSTransformer();
		workbook = transformer.transformMultipleSheetsList(templateInputStream, splitData, sheetNames, dataKey,
				beanMap, 0);
		workbook.write(destOutputStream);
	}

	/**
	 * 导出无动态表头的Excel文件，目标文件和模版文件均为文件路径
	 * <p>
	 * 参考重载的有动态表头注释
	 * </p>
	 * 
	 * @param destFilePath
	 * @param templateFilePath
	 * @param data
	 * @param dataKey
	 * @param maxRowPerSheet
	 * @throws Exception
	 */
	public void generateExcelByTemplate(String destFilePath, String templateFilePath, List data, String dataKey,
			int maxRowPerSheet) throws Exception {
		generateExcelByTemplate(destFilePath, templateFilePath, null, null, data, dataKey, maxRowPerSheet);
	}

	/**
	 * 导出有动态表头的Excel文件，目标文件和模版文件均为文件路径
	 * <p>
	 * 参考重载的有动态表头注释
	 * </p>
	 * 
	 * @param destFilePath
	 * @param templateFilePath
	 * @param header
	 * @param headerKey
	 * @param data
	 * @param dataKey
	 * @param maxRowPerSheet
	 * @throws Exception
	 */
	public void generateExcelByTemplate(String destFilePath, String templateFilePath, List header, String headerKey,
			List data, String dataKey, int maxRowPerSheet) throws Exception {
		generateExcelByTemplate(new FileOutputStream(destFilePath), new FileInputStream(templateFilePath), header,
				headerKey, data, dataKey, maxRowPerSheet);
	}

	/**
	 * 根据Sheet大小生成多sheet
	 * 
	 * @param data
	 * @param maxRowPerSheet
	 * @return
	 */
	private List<List> splitList(List data, int maxRowPerSheet) {
		int listSize = data.size();
		int subListSize = (int) Math.ceil((double) listSize / maxRowPerSheet);// ceil 无条件进位，主要满足 1/4 5/4 默认整数相除被截断的问题
		List<List> splitData = Lists.newArrayList();
		int toIndex = 0;
		for (int i = 0; i < subListSize; i++) {
			List subList = Lists.newArrayList();
			toIndex = i + 1;
			if (toIndex == subListSize) {
				subList = data.subList(maxRowPerSheet * i, listSize);
			} else {
				subList = data.subList(maxRowPerSheet * i, maxRowPerSheet * toIndex);
			}
			splitData.add(subList);
		}
		return splitData;
	}
}
