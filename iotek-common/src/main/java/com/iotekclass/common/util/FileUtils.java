/**
 * 
 */
package com.iotekclass.common.util;

import java.io.*;

/**
 * ClassName: FileUtils
 * Description： 文件操作工具类
 * Author：王啸
 * Date：2013年12月16日 下午3:51:49
 * 
 * @version
 */
public class FileUtils {
	/**
	 * 
	 * @Description: 删除文件夹
	 * @param folderPath
	 * @throws
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 删除指定文件夹下所有文件
	 * @param path
	 * @return
	 * @throws
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (String element : tempList) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + element);
			} else {
				temp = new File(path + File.separator + element);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + element);// 先删除文件夹里面的文件
				delFolder(path + "/" + element);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @Description: 创建文件,讲源代码写入该文件中
	 * @param content
	 * @param fileName
	 * @throws
	 */
	/**
	 * 
	 * @Description: 创建文件,将内容写入该文件中
	 * @param content
	 * @param fileName
	 * @throws
	 */
	public static void writeFile(String content, String basePath, String fileName) {
		File file = new File(basePath);
		try {
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(basePath + fileName);
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Description: 创建文件,将内容写入该文件中
	 * @param content
	 * @param fileName
	 * @throws
	 */
	public static void writeFileWithUtf_8(String content, String basePath, String fileName) {
	  File file = new File(basePath);
	  try {
		if (!file.exists()) {
		  file.mkdirs();
		}
		file = new File(basePath + fileName);
		FileOutputStream fo = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fo,"utf-8");
		PrintWriter pw = new PrintWriter(osw);
		pw.println(content);
		pw.flush();
		pw.close();
	  } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  } catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	  }
	}
}
