package com.iotekclass.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHepler {
	public static final int IMAGE_SHOW_WIDTH = 500;
	public static final int IMAGE_SHOW_HEIGHT = 312;

	/** */
	/**
	 * 图像切割方法
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param x
	 *            目标切片起点x坐标
	 * @param y
	 *            目标切片起点y坐标
	 * @param destWidth
	 *            目标切片宽度
	 * @param destHeight
	 *            目标切片高度
	 * @throws IOException
	 */
	public static void cutImage(String srcImageFile, int x, int y, int destWidth, int destHeight, String desImageFile)
			throws IOException {
		Image img;
		ImageFilter cropFilter;
		// 读取源图像
		BufferedImage bi = ImageIO.read(new File(srcImageFile));
		int srcWidth = bi.getWidth(); // 源图宽度
		int srcHeight = bi.getHeight(); // 源图高度
		// 按比例计算实际的裁剪位置和大小
		if ((srcWidth >= destWidth) && (srcHeight >= destHeight)) {
			cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
			// 截取图片
			img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bi.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			// 输出为文件
			ImageIO.write(tag, "JPEG", new File(desImageFile));
		}

	}

	public static void scaleImage(File srcImageFile, File desImageFile, int desWidth, int desHeight) throws IOException {
		BufferedImage src = ImageIO.read(srcImageFile);
		int srcWidth = src.getWidth();
		int srcHeight = src.getHeight();
		
		float heightRatio = srcHeight * 1f
				/ desHeight;
		float widthRatio = srcWidth * 1f
				/ desWidth;
		if (heightRatio > 1 && widthRatio > 1) {
			if (heightRatio > widthRatio) {
				desWidth = (int) (srcWidth / heightRatio);
			} else {
				desHeight = (int) (srcHeight/ widthRatio);
			}
		}

		BufferedImage bufferedImage = new BufferedImage(desWidth, desHeight, BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(src.getScaledInstance(desWidth, desHeight, Image.SCALE_SMOOTH), 0, 0, null);
		ImageIO.write(bufferedImage, "JPEG", new FileOutputStream(desImageFile));

	}
}
