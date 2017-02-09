package com.kartag.server.presentation.form;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.beanutils.BeanUtils;

import com.kartag.common.dao.DaoFactory;
import com.kartag.common.model.IModel;
import com.kartag.common.presentation.forms.DefaultActionForm;
import com.kartag.common.presentation.forms.IActionForm;
import com.kartag.server.model.Country;
import com.kartag.server.model.SystemUser;
import com.kartag.server.model.UserChannel;
import com.kartag.server.presentation.action.SystemUserActions;

public class SystemUserForm extends SystemUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = -523983216581162297L;


	private String userRule = "";
	
	private ArrayList<? extends DefaultActionForm> channels;
	
	private ArrayList<? extends DefaultActionForm> users;
	public SystemUserForm()
	{
	}
	public ArrayList<? extends DefaultActionForm> listUserChannels(SystemUser user)
			throws Exception {
		IModel targetBean = null;
		IActionForm targetForm = null;
		if(SystemUser.SUPER_USER.equals(user.getType()))
		{
			targetBean = new Country();
			targetForm = new CountryForm();
		}
//		else if(SystemUser.MALL_USER.equals(user.getType()))
//		{
//			targetBean = new Mall();
//			targetForm = new MallForm();
//		}
//		else if(SystemUser.STORE_USER.equals(user.getType()))
//		{
//			targetBean = new Store();
//			targetForm = new StoreForm();
//		}
		ArrayList<? extends DefaultActionForm> items = listChannels(user, targetBean, targetForm);
		return items;

	}
	public ArrayList<DefaultActionForm> listChannels(SystemUser user,IModel bean, IActionForm form) throws Exception{
		ArrayList<DefaultActionForm> items = new ArrayList<DefaultActionForm>();
		LinkedHashMap<String, String> filters = new LinkedHashMap<String, String>();
		filters.put(SystemUserActions.userId_req_param_name, user.getUserName());
		
		UserChannel userChannel = new UserChannel();
		ArrayList<? extends IModel> channels = DaoFactory.create(userChannel).list(userChannel, filters);
	    
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(IModel e : channels)
		{
			UserChannel newObj = userChannel.getClass().newInstance();
	    	BeanUtils.copyProperties(newObj, e);
	    	ids.add(newObj.getChannelId());
		}
		
		if(!channels.isEmpty())
		{
		ArrayList<? extends IModel> rows = DaoFactory.create(bean).list(bean, "id", ids);
		for(IModel e : rows)
		{
	    	IActionForm newForm = form.getClass().newInstance();
	    	BeanUtils.copyProperties(newForm, e);
				items.add((DefaultActionForm)newForm);
		}
		}
		return items;
	}
	public void setUsers(ArrayList<? extends DefaultActionForm> users) {
		this.users = users;
	}

	public ArrayList<? extends DefaultActionForm> getUsers() {
		return users;
	}

	public void setUserRule(String userRule) {
		this.userRule = userRule;
	}

	public String getUserRule() {
		return userRule;
	}
	public void setChannels(ArrayList<? extends DefaultActionForm> channels) {
		this.channels = channels;
	}
	public ArrayList<? extends DefaultActionForm> getChannels() {
		return channels;
	}
}
