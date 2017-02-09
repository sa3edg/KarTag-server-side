package com.kartag.business.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Response implements IResponse{
	private String response = "";
	private String status = "";
	private static final String RESULT_NODE = "\"result\"";
	private static final String DATA_NODE = "\"data\"";
	public static final String ERROR_STATUS = "error";
	private ByteArrayOutputStream strmBytes = new ByteArrayOutputStream();
    private DataOutputStream strmDataType = new DataOutputStream(strmBytes) ;
	public void write(HttpServletResponse response)
	{
		try
		{
	       strmDataType.write(getResponse().getBytes("UTF-8"));
		
		   byte [] bytesArray=strmBytes.toByteArray();
		   response.getOutputStream().write(bytesArray, 0, bytesArray.length);
		}
		catch(Exception ex)
		{
			
		}
	}
	public void writeInvalidRequestMethodError(HttpServletResponse response)
	{
		try
		{
	       strmDataType.write((new ObjectMapper().writeValueAsString(RESPONSE_STATUS_REQUEST_METHOD_ERROR)).getBytes());
		
		   byte [] bytesArray=strmBytes.toByteArray();
		   response.getOutputStream().write(bytesArray, 0, bytesArray.length);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public void writeError(HttpServletResponse response)
	{
		try
		{
			strmDataType.write((new ObjectMapper().writeValueAsString(RESPONSE_STATUS_ERROR)).getBytes());
		
		   byte [] bytesArray=strmBytes.toByteArray();
		   response.getOutputStream().write(bytesArray, 0, bytesArray.length);
		}
		catch(Exception ex)
		{
			
		}
	}
	public void mergeResponse(Response res)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append(RESULT_NODE + ":" + res.getStatus() + ",");
		buf.append(DATA_NODE + ":" + res.getResponse()+ "}");
		res.setResponse(buf.toString());
	}
	
	public void createErrorNode(Response res)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append(RESULT_NODE + ":" + res.getStatus() + "}");
		res.setResponse(buf.toString());
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponse() {
		return response;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}

}
