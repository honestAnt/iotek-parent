/**
 * 
 */
package com.iotekclass.cms.utils;

import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iotekclass.common.util.memcache.MemCachedManager;

/**
 * @Description 解析shiro默认权限配置,并把其添加到缓存当中
 * @author wangfengbao
 * @date 2015年5月8日 下午5:08:33
 *
 */
@Service("parseShiroDefaultMap")
public class ParseShiroDefaultMap {

	/**
	 * shiro权限
	 */
	public static final String SHIRO_DEFAULT_STR = "shiro_default_str";
	
	private static String path = "security/applicationContext-cms-shiro.xml";

	private static Logger logger = LoggerFactory.getLogger(ParseShiroDefaultMap.class);

	MemCachedManager memCachedManager = MemCachedManager.getInstance();
	
	/**
	 * 
	 * @description 解析shiro权限配置 
	 * @author wangfengbao
	 * @date 2015年5月11日 下午4:18:33
	 *
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String parseXml() {
		StringBuilder sb = new StringBuilder();
		SAXReader reader = new SAXReader();
		try {
			InputStream in1 = ParseShiroDefaultMap.class.getClassLoader().getResourceAsStream(path);
			//			Document  document = reader.read(new File(path));
			Document document = reader.read(in1);
			//1.获取文档的根节点.  
			Element root = document.getRootElement();
			//2.取得根节点下所有的子节点.
			for (Iterator it = root.elementIterator(); it.hasNext();) {
				Element element = (Element) it.next();
				System.out.println("父节点: " + element.getName());
				if (!"description".equals(element.getName()) && element.attributeValue("name").contains("shiroFilter")) {//找到shiro bean
					for (Iterator node = element.elementIterator(); node.hasNext();) {
						Element elementNode = (Element) node.next();
						System.out.println("子节点：" + elementNode.attributeValue("name"));
						if (elementNode.attributeValue("name").contains("filterChainDefinitions")) {//找到filterChainDefinitions
							//获取所有的配置信息

							Element element2 = elementNode.element("value");
							String text2 = element2.getText();
							System.out.println("权限节点信心: "+text2);
							logger.debug(path+"下所有权限信息: "+text2);
							sb.append(text2);
						} else if (elementNode.attributeValue("name").contains("successUrl")) {
//							System.out.println(elementNode.attributeValue("value").replaceAll("&nbsp;", ","));
							sb.append(elementNode.attributeValue("value"));
						}
					}
				}
			}

		} catch (DocumentException e) {
			logger.debug("解析shiro配置文件失败!");
			e.printStackTrace();
		}
		return sb.toString();
	}

	//测试
	public static void main(String[] args) {
		ParseShiroDefaultMap defaultMap = new ParseShiroDefaultMap();
		//		defaultMap.parseXml();
		System.out.println(defaultMap.parseXml());
		/*Ini ini = new Ini();
		ini.load(defaultMap.parseXml());
		//did they explicitly state a 'urls' section?  Not necessary, but just in case:
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
		    //no urls section.  Since this _is_ a urls chain definition property, just assume the
		    //default section contains only the definitions:
		    section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		Map<String, String> map = new HashMap<String, String>(section);*/
		System.out.println(defaultMap.parseXml().contains("/user/loginx "));
	}
}
