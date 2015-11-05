/**
 * 
 */
package com.iotekclass.common.util.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: CipherHelper
 * Description： 页面GET参数加密和解密辅助类
 * Author：ZengAihui
 * Date：2013年8月30日 上午9:12:40
 * 
 * @version
 */

public final class CipherHelper {

	/**
	 * 禁止构造
	 */
	private CipherHelper() {
	}

	private static Logger logger = LoggerFactory.getLogger(CipherHelper.class);

	private static final String ALGORITHM = "AES";// 加密算法

	private static final String encoding = "UTF-8";

	public static final String GLOBAL_SECRETKEY = "01234567";// 公共加密Key

	private static final int keysize = 128;

	private static final char hexidecimal = 0xFF;// 使用它做&运算，可以把byte值变成 int类型的值,也会将 -128～0 间的负值都转成正值

	private static final int radix = 16;

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String cipher(String content, String password) {
		byte[] encryptResult = encrypt(content, password);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		// BASE64位加密
		encryptResultStr = encrypto(encryptResultStr);
		return encryptResultStr;
	}

	/**
	 * AES解密
	 * 
	 * @param encryptResultStr
	 * @param password
	 * @return
	 */
	public static String decipher(String encryptResultStr, String password) {
		// BASE64位解密
		String decrpt = decrypto(encryptResultStr);
		byte[] decryptFrom = parseHexStr2Byte(decrpt);
		byte[] decryptResult = decrypt(decryptFrom, password);
		if (decryptResult == null) {
			return null;
		} else {
			return new String(decryptResult);
		}
	}

	/**
	 * 解密数据
	 * 
	 * @param cipherData
	 * @return
	 * @throws Exception
	 */
	public static int decipherData(String cipherData) throws Exception {
		int result = 0;
		if (StringUtils.isNotBlank(cipherData)) {// 判断非空
			String numString = CipherHelper.decipher(cipherData, CipherHelper.GLOBAL_SECRETKEY);
			if (StringUtils.isNotBlank(numString) && StringUtils.isNumeric(numString)) {
				// 其中判断非空是为了当前台用户恶意切断参数导致空指针
				long tempVal = Long.valueOf(numString).longValue();
				if ((tempVal > Integer.MIN_VALUE) && (tempVal < Integer.MAX_VALUE)) {// 判断是否在整型范围内
					result = (int) tempVal;
				}
			}
		}
		return result;
	}

	/**
	 * 加密字符串
	 */
	public static String encrypto(String str) {
		String result = str;
		if ((result != null) && (result.length() > 0)) {
			try {
				byte[] encodeByte = Base64.encodeBase64(str.getBytes(encoding));
				result = new String(encodeByte);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// base64加密超过一定长度会自动换行 需要去除换行符
			result = result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
		}
		return result;
	}

	/**
	 * 解密字符串
	 */
	public static String decrypto(String str) {
		byte[] encodeByte = Base64.decodeBase64(str);
		return new String(encodeByte);
	}

	/**
	 * 加密
	 * 
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			// 防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(keysize, secureRandom);
			// kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes(encoding);
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage());
		} catch (BadPaddingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content 待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			// 防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(keysize, secureRandom);
			// kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage());
		} catch (BadPaddingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (byte element : buf) {
			String hex = Integer.toHexString(element & hexidecimal);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < (hexStr.length() / 2); i++) {
			try {
				int high = Integer.parseInt(hexStr.substring(i * 2, (i * 2) + 1), radix);
				int low = Integer.parseInt(hexStr.substring((i * 2) + 1, (i * 2) + 2), radix);
				result[i] = (byte) ((high * radix) + low);
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				return null;
			}

		}
		return result;
	}

}
