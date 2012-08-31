/**
 * 
 */
package cn.com.mywaiter.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.mywaiter.util.QRCodeHandler;

import com.opensymphony.webwork.ServletActionContext;

/**
 * @author Harrison
 * 
 */
public class GenQRCodeAction extends BaseAction {
	private Logger log = Logger.getLogger(GenQRCodeAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -3673557858931334128L;

	private String content;
	private String url;
	
	private QRCodeHandler qrCodeHandler;

	/**
	 * 
	 * @return
	 */
	public String getGenaratorPage() {
		return SUCCESS;
	}

	public String genarateQRCodeJPG() throws IOException {
		if (!StringUtils.isNotBlank(content)) {
			super.setErrorMsg("请输入有效内容～！");
		}

		if (!StringUtils.isNotBlank(url)) {
			super.setErrorMsg("请输入有效页面URL地址～！");
		}

		FileInputStream in = null;
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType("image/jpeg");

		if (qrCodeHandler != null) {
			String fileName = qrCodeHandler.getQrCodePicFolder()
					+ System.getProperty("file.separator")
					+ System.currentTimeMillis() + ".jpg";
			log.debug("generated qrcode file name : " + fileName);

			qrCodeHandler.encode(content + " /n " + url, null, null, fileName); //生成图片
			
			File imgFile = new File(fileName);
			FileInputStream imgFileIn = new FileInputStream(imgFile);
			if(imgFile.canRead()){
				while(imgFileIn.available()>0){
					out.write(imgFileIn.read());//jpeg流输出
				}
			}
			imgFileIn.close();
			out.close();
		}
		return SUCCESS;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public QRCodeHandler getQrCodeHandler() {
		return qrCodeHandler;
	}

	public void setQrCodeHandler(QRCodeHandler qrCodeHandler) {
		this.qrCodeHandler = qrCodeHandler;
	}

}
