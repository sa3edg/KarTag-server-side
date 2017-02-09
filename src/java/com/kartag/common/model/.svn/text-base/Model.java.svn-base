package com.kartag.common.model;

import java.util.ArrayList;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kartag.common.presentation.forms.DefaultActionForm;

@JsonIgnoreProperties({ "validatorResults", "page", "action", "resultValueMap", "multipartRequestHandle", "servletWrapper", "multipartRequestHandler" })
public abstract class Model extends DefaultActionForm implements IModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7213939444312249684L;
	@JsonIgnore
	public String getAsJSON() {
		
		try
		{
	       ObjectMapper mapper = new ObjectMapper();
//	       mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
	       return mapper.writeValueAsString(this) ;
	       
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	@JsonIgnore
	public String getArrayAsJSON(ArrayList<? extends IModel> objects) {
		
		try
		{
	       ObjectMapper mapper = new ObjectMapper();
//	       mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
	       return mapper.writeValueAsString(objects) ;
	       
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@JsonIgnore
	public String getArrayAsJSON(Set<? extends IModel> objects) {
		
		try
		{
	       ObjectMapper mapper = new ObjectMapper();
//	       mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
	       return mapper.writeValueAsString(objects) ;
	       
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public abstract int getBeanParentId();

}
