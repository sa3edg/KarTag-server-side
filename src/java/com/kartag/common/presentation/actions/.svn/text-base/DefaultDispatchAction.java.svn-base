package com.kartag.common.presentation.actions;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.kartag.common.model.Model;
import com.kartag.common.presentation.forms.DefaultActionForm;


public abstract class DefaultDispatchAction extends DispatchAction {

	
	public ArrayList<? extends DefaultActionForm> getList(
			String parentIdName, HttpServletRequest request, Model actionForm, Model bean)
			throws Exception {

		LinkedHashMap<String, String> filterParamter = new LinkedHashMap<String, String>();
		String id = "-1";
		if (bean.getBeanParentId() == 0) {
			id = request.getParameter(parentIdName);
		} else {
			id = "" + bean.getBeanParentId();
		}
		filterParamter.put(parentIdName, id);
		request.setAttribute(parentIdName, id);
		
		ArrayList<? extends DefaultActionForm> items = actionForm.listItems(
				bean, actionForm, filterParamter);
		return items;

	}
	
	protected boolean validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request)
	{
		ActionErrors errors = form.validate(mapping, request);  
		if(!errors.isEmpty())  
		{  
		   saveErrors(request,errors);  
		   return false;
		}
		return true;
	}
}
