package com.kartag.server.presentation.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.kartag.common.presentation.forms.DefaultActionForm;
import com.kartag.common.util.DateUtil;
import com.kartag.server.model.Community;

public class CommunityForm extends Community{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1382613305696434920L;
	private ArrayList<? extends DefaultActionForm> communities;
	private String startDate = "";
	private String endDate = "";	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		if("".equals(startDate))
        {
        	startDate = DateUtil.getCurrentDateString();
        }
        
        if("".equals(endDate))
        {
        	endDate = DateUtil.getCurrentDateString();
        }
        
        
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setCommunities(ArrayList<? extends DefaultActionForm> communities) {
		this.communities = communities;
	}
	public ArrayList<? extends DefaultActionForm> getCommunities() {
		return communities;
	}

	
}
