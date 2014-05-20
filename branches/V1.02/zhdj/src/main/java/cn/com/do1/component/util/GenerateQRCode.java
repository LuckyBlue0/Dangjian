package cn.com.do1.component.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import cn.com.do1.common.util.DateUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @ClassName: GenerateQRCode
 * @Description: 生成二维码
 * @author wenjianhai
 * @company 广州市道一信息技术有限公司
 * @date 2012-11-9
 * @version V1.0
 */

public final class GenerateQRCode {

	private static final Logger log = Logger.getLogger(GenerateQRCode.class);

	private static final GenerateQRCode instance = new GenerateQRCode();

	private GenerateQRCode() {
	}

	public static GenerateQRCode getInstance() {
		return instance;
	}

	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * @Title: encode
	 * @Description: 生成二维码
	 * @author wenjianhai
	 * @date 2012-11-8
	 */
	public static void encode() {
		// 二维码内容
		StringBuffer params = new StringBuffer();

		params.append("智慧党建");
		String path = "D:\\qrcodeImg\\";
		try {
			String imgPath = GenerateQRCode.getInstance().generate("","会议", params.toString(), path, 200, 200);
			System.out.println(imgPath);

			System.out.println("生成二维码成功");

		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		encode();
	}
	

	/**
	 * @Title: generate
	 * @Description: 生成二维码
	 * @param assetsName
	 *            二维码图片名称
	 * @param params
	 *            二维码信息
	 * @param width
	 *            生成的图片的宽
	 * @param height
	 *            生成的图片的高
	 * @param path
	 *            二维码图片存放目录
	 * @throws Exception
	 * @return String 二维码图片名称
	 * @author wenjianhai
	 * @date 2012-11-9
	 */
	public static String generate(String assetsName, String params, String path, int width, int height) throws Exception {

		log.info("GenerateQRCode-->start to generate qrcode.");
		log.info("the qrcode's save path is:" + path);
		String tomcatPath = path.substring(0, path.indexOf("qrcodeImg"));
		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		// 二维码图片名称
		String fileName = assetsName.concat("_").concat(DateUtil.formartCurrentDateTime()).concat(".jpg");

		// 二维码图片存放路径
		path = path.concat(fileName);

		log.info("the qrcode's path is:" + path);

		file = new File(path);

		if (!file.exists()) {
			file.createNewFile();
		}

		BitMatrix bitMatrix = new MultiFormatWriter().encode(params, BarcodeFormat.QR_CODE, width, height);

		writeToFile(bitMatrix, "png", file, tomcatPath);

		log.info("GenerateQRCode-->end to generate qrcode.");

		return fileName;
	}

	public String generate(String id, String assetsName, String params, String path, int width, int height) throws Exception {

		log.info("GenerateQRCode-->start to generate qrcode.");
		log.info("the qrcode's save path is:" + path);
		String tomcatPath = path.substring(0, path.indexOf("qrcode"));
		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		// 二维码图片名称
		String fileName = id.concat("_").concat(assetsName).concat(".jpg");

		path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		// 二维码图片存放路径
		path = path.concat(fileName);

		log.info("the qrcode's path is:" + path);

		file = new File(path);

		if (!file.exists()) {
			file.createNewFile();
		}

		BitMatrix bitMatrix = new MultiFormatWriter().encode(params, BarcodeFormat.QR_CODE, width, height);

		writeToFile(id, assetsName, bitMatrix, "png", file, tomcatPath);

		log.info("GenerateQRCode-->end to generate qrcode.");

		return fileName;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file, String tomcatPath) throws Exception {

		BufferedImage image = toBufferedImage(matrix);

		BufferedImage logo = ImageIO.read(new File(tomcatPath + "\\logo.jpg"));
		Graphics2D g = image.createGraphics(); // logo宽高
		int width = image.getWidth() / 5;
		int height = image.getHeight() / 5; // logo起始位置，此目的是为logo居中显示
		int x = (image.getWidth() - width) / 2;
		int y = (image.getHeight() - height) / 2;
		g.drawImage(logo, x, y, 30, 30, null);
		g.dispose();

		ImageIO.write(image, format, file);

	}

	public static void writeToFile(String id, String assetsName, BitMatrix matrix, String format, File file, String tomcatPath) throws Exception {

		BufferedImage image = toBufferedImage(matrix);

		BufferedImage logo = ImageIO.read(new File(tomcatPath + "\\qrcode\\logo.jpg"));
		Graphics2D g = image.createGraphics(); // logo宽高
		int width = image.getWidth() / 5;
		int height = image.getHeight() / 5; // logo起始位置，此目的是为logo居中显示
		int x = (image.getWidth() - width) / 2;
		int y = (image.getHeight() - height) / 2;
		g.drawImage(logo, x, y, 30, 30, null);
		g.setColor(Color.black);
		if (assetsName.contains("NO")) {
			g.drawString(assetsName, x, 198);
		} else {
			g.drawString(id + "_" + assetsName, x - (id.length() + assetsName.length()) * 2, 198);
		}
		g.dispose();

		ImageIO.write(image, format, file);

	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {

		int width = matrix.getWidth();

		int height = matrix.getHeight();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);

			}

		}

		return image;

	}

	/**
	 * 二维码添加自定义logo（关键部分）
	 */
	public static void overlapImage(BufferedImage image, String imgPath, String logoPath) {
		try {
			BufferedImage logo = ImageIO.read(new File(logoPath));
			Graphics2D g = image.createGraphics(); // logo宽高
			int width = image.getWidth() / 5;
			int height = image.getHeight() / 5; // logo起始位置，此目的是为logo居中显示
			int x = (image.getWidth() - width) / 2;
			int y = (image.getHeight() - height) / 2;
			g.drawImage(logo, x, y, width, height, null);
			g.dispose();
			ImageIO.write(image, "png", new File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
