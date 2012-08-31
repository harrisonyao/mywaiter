package cn.com.mywaiter.web.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.ServletRequestAware;
import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * 
 * 基础Action
 * @author Harrison
 *
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4907073245785057170L;

	private HttpServletRequest request;
	private Map<Object, Object> session;
	private String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @return the session
	 */
	public Map<Object, Object> getSession() {
		return session;
	}
}
