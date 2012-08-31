/**
 * 
 */
package cn.com.mywaiter.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author Harrison
 * 
 */
public class QRCodeHandler {
	
	private String qrCodePicFolder;

	/**
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public void encode(String contents, Integer width, Integer height,
			String imgPath) {
		int defaultWidth = 300;
		int defaultHeight = 300;
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE,
					width == null ? defaultWidth : width.intValue(),
					height == null ? defaultHeight : height.intValue(), hints);

			if (StringUtils.isNotBlank(imgPath)) {
				StringBuffer pathSB = new StringBuffer();
				pathSB.append(imgPath);
				pathSB.delete(imgPath.lastIndexOf(System
						.getProperty("file.separator")), imgPath.length()); // 去除文件路径上的文件名路径
				File pathFile = new File(pathSB.toString());
				if(!pathFile.exists()){
					pathFile.mkdirs(); // 创建路径
				}
			}

			MatrixToImageWriter.writeToFile(bitMatrix, "jpeg",
					new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param imgPath
	 * @return content
	 */
	public String decode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "GBK");

			result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @return the qrCodePicFolder
	 */
	public String getQrCodePicFolder() {
		return qrCodePicFolder;
	}

	/**
	 * @param qrCodePicFolder the qrCodePicFolder to set
	 */
	public void setQrCodePicFolder(String qrCodePicFolder) {
		this.qrCodePicFolder = qrCodePicFolder;
	}
}
