package com.kartag.business.common;

import javax.servlet.http.HttpServletResponse;

public interface IResponse {
    public static final String RESPONSE_STATUS_REQUEST_METHOD_ERROR = "\"notsupportedmethod\"";
    public static final String RESPONSE_STATUS_ERROR = "\"error\"";
    public static final String RESPONSE_STATUS_SUCCESS = "\"success\"";
	public void write(HttpServletResponse response);
}
