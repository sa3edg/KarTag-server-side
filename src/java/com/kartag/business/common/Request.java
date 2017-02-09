package com.kartag.business.common;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Request implements IRequest{
	
	private boolean authenticated = false;
	private String orderType = "";
	private String source = "";
	private String key = "";
	private LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
	public static Request createRequest(HttpServletRequest request) {
		Request req = new Request();
		String source = request.getHeader("User-Agent");
		if(source != null && "".equals(""))
		{
			if(source.indexOf("android") != -1 || source.indexOf("Android") != -1)
			{
				req.setSource(Request.SOURCE_DEVICE_ANDROID);
			}
			if(source.indexOf("iphone") != -1 || source.indexOf("ios") != -1)
			{
				req.setSource(Request.SOURCE_DEVICE_IPHONE);
			}
			if(source.indexOf("mobile") != -1 || source.indexOf("mobile") != -1)
			{
				req.setSource(Request.SOURCE_DEVICE_MOBILE);
			}
			if(source.indexOf("MSIE") != -1 || source.indexOf("Firefox") != -1  || source.indexOf("Chrome") != -1 
					|| source.indexOf("Opera") != -1 || source.indexOf("Safari") != -1)
			{
				req.setSource(Request.SOURCE_BROWSEER);
			}
		}
		
		Map<String, String[]> parms = request.getParameterMap();
		
		req.setOrderType(request.getParameter(RequestParameters.ACTION));
		req.setKey(request.getParameter(RequestParameters.KEY));
		req.setAuthenticate(Boolean.getBoolean(request.getParameter(RequestParameters.AUTHENTICATE)));
		
		for(String key : parms.keySet())
		{
			if(!RequestParameters.ACTION.equals(key) || !RequestParameters.KEY.equals(key) 
					|| !RequestParameters.AUTHENTICATE.equals(key))
			{
				String value = ((String[]) parms.get( key ))[ 0 ];
				req.getParameters().put(key, value);
			}
		}
		return req;
	}

	@Override
	public void setAuthenticate(boolean authenticated) {
		this.authenticated = authenticated;
		
	}

	@Override
	public boolean isAuthenticate() {
		return this.authenticated;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setParameters(LinkedHashMap<String, String> parameters) {
		this.parameters = parameters;
	}

	public LinkedHashMap<String, String> getParameters() {
		return parameters;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return source;
	}
	public void setSource(String source) {
		// TODO Auto-generated method stub
		this.source = source;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
