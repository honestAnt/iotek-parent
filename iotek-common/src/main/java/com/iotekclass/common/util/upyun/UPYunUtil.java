package com.iotekclass.common.util.upyun;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.iotekclass.common.util.upyun.UpYun.PARAMS;

public class UPYunUtil {

	// 运行前先设置好以下三个参数
	private static final String FILE_BUCKET_NAME = "iotekfile";
	private static final String IMAGE_BUCKET_NAME = "iotekimg";
	private static final String USER_NAME = "dev";
	private static final String USER_PWD = "iotek_dev";
	public static final String IMAGE_FILE_SECRET = "iotek";
	public static final String UPYUN_ICON_DIR = "upload";

	/** 绑定的域名 */
	public static final String IMAGE_URL = "http://" + IMAGE_BUCKET_NAME
			+ ".b0.upaiyun.com";
	public static final String FILE_URL = "http://" + FILE_BUCKET_NAME
			+ ".b0.upaiyun.com";

	/** 根目录 */
	public static final String DIR_ROOT = "/";

	private static UpYun upyun4Image = new UpYun(IMAGE_BUCKET_NAME, USER_NAME,
			USER_PWD);

	private static UpYun upyun4File = new UpYun(FILE_BUCKET_NAME, USER_NAME,
			USER_PWD);
	
	public static final String UPYUN_IOTEK2_DIR = "iotek2";

	/**
	 * 
	 * @Description: 上传文件
	 * @param remotePath
	 *            远程路径
	 * @param file
	 *            本地文件
	 * @param encrypt
	 *            是否MD5加密文件
	 * @return
	 * @throws IOException
	 * @throws
	 */
	public static boolean uploadFile(String remotePath, File file,
			boolean encrypt) throws IOException {

		// 设置待上传文件的 Content-MD5 值
		// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
		if (encrypt) {
			upyun4File.setContentMD5(UpYun.md5(file));
		}

		return upyun4File.writeFile(remotePath, file, true);
	}

	public static boolean uploadFile(String remotePath, File file)
			throws IOException {

		return uploadFile(remotePath, file, false);
	}

	/**
	 * 
	 * @Description: 创建目录
	 * @param dirPath
	 *            目录路径
	 * @return
	 * @throws
	 */
	public static boolean makeDir(String dirPath) {

		// 方法2：创建多级目录，自动创建父级目录（最多10级）
		return upyun4File.mkDir(dirPath, true);
	}

	/**
	 * 
	 * @Description: 创建目录
	 * @param filePath
	 *            文件路径
	 * @param savePath
	 *            本地保存的路径
	 * @return
	 * @throws IOException
	 * @throws
	 */
	public static boolean readFile(String filePath, File savePath)
			throws IOException {

		return upyun4File.readFile(filePath, savePath);

	}

	/**
	 * 
	 * @description 获取云盘文件信息
	 * @author wangfengbao
	 * @date 2015年1月21日 下午6:03:00
	 *
	 * @param filePath 文件路径(带文件名，返回文集那内容；不给，返回文件基本信息【名称/大小】。)
	 * @return
	 */
	public static String getFileInfo(String filePath) {
		
		return upyun4File.readFile(filePath);
	}
	
	/**
	 * 
	 * @Description: 删除文件
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws
	 */
	public static boolean deleteFile(String filePath) {

		return upyun4File.deleteFile(filePath);

	}

	/**
	 * 
	 * @Description: 删除目录
	 * @param dirPath
	 *            目录路径,必须为空目录
	 * @return
	 * @throws
	 */
	public static boolean removeDir(String dirPath) {

		// 带删除的目录必须存在，并且目录下已不存在任何文件或子目录
		return upyun4File.rmDir(dirPath);
	}

	/**
	 * 
	 * @Description: 上传图片
	 * @param remoteImagePath
	 *            远程路径
	 * @param imageFile
	 *            本地文件对象
	 * @return
	 * @throws IOException
	 * @throws
	 */
	public static boolean uploadImage(String remoteImagePath, File imageFile,
			boolean encrypt) throws IOException {

		// 设置待上传文件的 Content-MD5 值
		// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
		upyun4Image.setContentMD5(UpYun.md5(imageFile));

		// 设置待上传文件的"访问密钥"
		// 注意：
		// 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
		// 举例：
		// 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
		// 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
		if (encrypt) {
			upyun4Image.setFileSecret(IMAGE_FILE_SECRET);
		}

		// 上传文件，并自动创建父级目录（最多10级）
		return upyun4Image.writeFile(remoteImagePath, imageFile,
				true);

	}

	public static boolean uploadImage(String remoteImagePath, File imageFile)
			throws IOException {

		return uploadImage(remoteImagePath, imageFile, false);
	}

	/**
	 * 
	 * @Description: 裁剪上传图片
	 * @param remoteImagePath
	 *            远程路径
	 * @param imageFile
	 *            本地文件对象
	 * @param x
	 *            裁剪图片的x坐标
	 * @param y
	 *            裁剪图片的y坐标
	 * @param width
	 *            裁剪图片的宽度
	 * @param height
	 *            裁剪图片的高度
	 * @return
	 * @throws IOException
	 * @throws
	 */
	public static boolean cropImage(String remoteImagePath, File imageFile,
			int x, int y, int width, int height)
			throws IOException {

		// 图片裁剪功能具体可参考：http://wiki.upyun.com/index.php?title=图片裁剪
		Map<String, String> params = new HashMap<String, String>();

		// 设置图片裁剪，参数格式：x,y,width,height
		params.put(PARAMS.KEY_X_GMKERL_CROP.getValue(), x + "," + y + ","
				+ width + "," + height);

		// 上传文件，并自动创建父级目录（最多10级）
		return upyun4Image.writeFile(remoteImagePath, imageFile, true, params);

	}
}
