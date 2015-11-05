package com.iotekclass.model.files;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.common.util.MD5Util;
import com.iotekclass.cms.model.IdEntity;

/**
 * @ClassName Files.java
 * @Description 文件系统的文件主表
 * @Author sq.xiao@iotek.com.cn
 * @Date 2014年12月10日 下午4:54:58
 * @Version 1.0
 */

public class Files extends IdEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8694578210700052852L;

	public static final String tableName = "tb_files";
	
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 文件格式
	 */
	private String fileFormat;
	
	/**
	 * 文件大小
	 */
	private String fileSize;
	
	/**
	 * 文件类型(1:图片	2:其他)
	 */
	private int fileType;
	
	/**
	 * 保存路径
	 */
	private String filePathUrl;
	
	/**
	 * 文件来源(1:网校	2:其他)
	 */
	private int fileSource;
	
	/**
	 * 文件描述
	 */
	private String fileDescribe;
	
	/**
	 * 上传时间
	 */
	private Date fileUploadTime;
	
	/**
	 * 文件上传人
	 */
	private int fileUploadUser;
	
	/**
	 * 下载次数
	 */
	private int fileDownloadCount;
	
	/**
	 * 是否有效(1:有效 	2:无效)
	 */
	private int fileIsActive;
	
	/**
	 * 是否删除(1:是  	0:否)
	 */
	private int fileIsDelete;
	
	private String fullPath;

	
	
	public Files() {

	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public String getFilePathUrl() {
		return filePathUrl;
	}

	public void setFilePathUrl(String filePathUrl) {
		this.filePathUrl = filePathUrl;
	}

	public int getFileSource() {
		return fileSource;
	}

	public void setFileSource(int fileSource) {
		this.fileSource = fileSource;
	}

	public String getFileDescribe() {
		return fileDescribe;
	}

	public void setFileDescribe(String fileDescribe) {
		this.fileDescribe = fileDescribe;
	}

	public Date getFileUploadTime() {
		return fileUploadTime;
	}

	public void setFileUploadTime(Date fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public int getFileUploadUser() {
		return fileUploadUser;
	}

	public void setFileUploadUser(int fileUploadUser) {
		this.fileUploadUser = fileUploadUser;
	}

	public int getFileDownloadCount() {
		return fileDownloadCount;
	}

	public void setFileDownloadCount(int fileDownloadCount) {
		this.fileDownloadCount = fileDownloadCount;
	}

	public int getFileIsActive() {
		return fileIsActive;
	}

	public void setFileIsActive(int fileIsActive) {
		this.fileIsActive = fileIsActive;
	}

	public int getFileIsDelete() {
		return fileIsDelete;
	}

	public void setFileIsDelete(int fileIsDelete) {
		this.fileIsDelete = fileIsDelete;
	}
	
	public String getFullPath() {
		if(fileName == null || "".equals(fileName) || fileFormat == null || "".equals(fileFormat) 
				|| filePathUrl.contains(".")) {
			return filePathUrl;
		} else {
			return filePathUrl + MD5Util.md5(fileName).substring(0,4) + "." + fileFormat;
		}
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
}
