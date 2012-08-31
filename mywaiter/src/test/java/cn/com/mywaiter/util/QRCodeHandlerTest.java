package cn.com.mywaiter.util;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class QRCodeHandlerTest {
	String fileName;
	String content;
	QRCodeHandler qrHandler;

	@Before
	public void setUp() {

		fileName = "E:\\temp\\tmp2" + System.getProperty("file.separator")
				+ "test-qr-code.jpg";
		System.out.println("file : " + fileName + " java.io.tmpDir : ");
		content = "hello world, this is my first QRCode \n http://www.mywaiter.com.cn \n this is only the test , you can use your mobile phone to capture the picture to visit our site";
		qrHandler = new QRCodeHandler();
	}

	@Test
	public void testEncode() {
		Assert.assertNotNull(qrHandler);
		qrHandler.encode(content, null, null, fileName);

		File tmpFile = new File(fileName);
		Assert.assertNotNull(tmpFile);
	}

	@Test
	public void testDecode() {
		Assert.assertNotNull(qrHandler);

		File tmpFile = new File(fileName);
		Assert.assertNotNull(tmpFile);

		String tmpCont = qrHandler.decode(tmpFile.getAbsolutePath());

		Assert.assertNotNull(tmpCont);
		Assert.assertEquals(content, tmpCont);
	}

}
