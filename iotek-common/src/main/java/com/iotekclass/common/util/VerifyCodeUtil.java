package com.iotekclass.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class VerifyCodeUtil {

	public static Random random = new Random();

	public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'Z',
			'X', 'C', 'V', 'B', 'N', 'M' };

	// 产生随机数
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	// 获取随机色
	public static Color getRandomColor(int fc, int bc) {

		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}

	public static InputStream getVeriCodeImageInputStream(int width, int height, String code)
			throws IOException {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(getRandomColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandomColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(code, 6, 16);

		// 图象生效
		g.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		ByteArrayInputStream input = new ByteArrayInputStream(
				output.toByteArray());

		return input;
	}
}
