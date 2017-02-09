package com.kartag.business.processing;


import static com.kartag.common.config.Constants.APP_KEY_ANDROID;
import static com.kartag.common.config.Constants.APP_KEY_IPHONE;
import com.kartag.business.common.Request;
import com.kartag.business.exception.NotAuthenticatedException;
import com.kartag.business.exception.ValidationException;
import com.kartag.business.validation.IAuthenticator;
import com.kartag.business.validation.IOrderValidator;

public  abstract class AbstractProcessor implements IProcessor, IOrderValidator, IAuthenticator{

	protected Request request;
	@Override
	public boolean authenticated() {
		return ((getRequest().getSource().equals(Request.SOURCE_DEVICE_ANDROID) && getRequest().getKey().equals(APP_KEY_ANDROID))
				|| (getRequest().getSource().equals(Request.SOURCE_DEVICE_IPHONE) && getRequest().getKey().equals(APP_KEY_IPHONE)));
	}

	@Override
	public boolean checkSource() {
		String source = getRequest().getSource();
		if(source.equals(Request.SOURCE_BROWSEER))
		{
			return false;
		}
		return true;
	}

	@Override
	public void preprocess() {
		
		//check rquest source
		if(!checkSource())
		{
			throw new ValidationException();
		}
		//check authentication
		if(getRequest().isAuthenticate())
		{
			if(!authenticated())
			{
				throw new NotAuthenticatedException();
			}
		}
		
		
	}

	@Override
	public void terminate() {
		
	}
	
	@Override
	public boolean validate()
	{
		return true;
	}
	
	@Override
	public Request getRequest() {
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public boolean isResponseExpected() {
		return true;
	}

}
