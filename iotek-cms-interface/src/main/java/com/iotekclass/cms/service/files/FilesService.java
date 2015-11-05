package com.iotekclass.cms.service.files;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.iotekclass.model.files.Files;
import com.iotekclass.service.BaseService;

/**
 * @ClassName FilesService.java
 * @Description 
 * @Author sq.xiao@iotek.com.cn
 * @Date 2014年12月11日 下午6:00:42
 * @Version 1.0
 */
public interface FilesService extends BaseService<Files> {
	/**
	 * 文件
	 */
	public static final int FILE_TYPE = 1;
	/**
	 * 图片
	 */
	public static final int IMG_TYPE = 2;
	
	List<Map<String, Object>>  getFileAttributeByGoodsId(int goodsId);
	
	/**
	 * 
	 * @Description: 获取文件id
	 * @param files
	 * @return
	 * @throws
	 */
	int getFileId(Files files);
	/**
	 * 
	 * @Description: 保存文件并返回id
	 * @param files
	 * @return
	 * @throws
	 */
	int saveFiles(Files files);
	
	/**
	 * 
	 * @Description: 上传文件至云服务器	add by lbl
	 * @param uploadFile
	 * @param fileName
	 * @return
	 * @throws
	 */
	Files uploadFile(File uploadFile,String filename,int type);
	/**
	 * 
	 * @Description: 上传文件至云服务器(指定路径)	add by lbl
	 * @param uploadFile
	 * @param filename
	 * @param type
	 * @param path
	 * @return
	 * @throws
	 */
	Files uploadFile(File uploadFile,String filename,int type,String path);
	/**
	 * 
	 * @Description: 上传文件至云服务器 返回fileId	add by lbl
	 * @param uploadFile
	 * @param fileName
	 * @return
	 * @throws
	 */
	int uploadFile(File uploadFile,String filename);
	/**
	 * 
	 * @Description: 获取文件大小	add by lbl
	 * @param file
	 * @return
	 * @throws
	 */
	String getFileSize(File file);
}
