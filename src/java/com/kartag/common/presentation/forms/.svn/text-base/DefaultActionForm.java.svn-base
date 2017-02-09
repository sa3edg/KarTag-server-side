/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kartag.common.presentation.forms;

import java.util.ArrayList;
import java.util.LinkedHashMap;


import org.apache.commons.beanutils.BeanUtils;

import com.kartag.common.dao.DaoFactory;
import com.kartag.common.model.IModel;
import com.kartag.common.util.BeanUtil;
import com.kartag.server.model.SystemUser;
import com.kartag.server.model.UserChannel;
import com.kartag.server.presentation.action.SystemUserActions;
/**
 *
 * @author saisaid
 */
public  class DefaultActionForm extends org.apache.struts.validator.ValidatorForm  implements IActionForm{

  
	private static final long serialVersionUID = 1L;
    private String action = "";
    public DefaultActionForm()
    {
    	
    }
	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		return action;
	}
	@Override
	public ArrayList<DefaultActionForm> listItems(IModel bean, IActionForm form, LinkedHashMap<String, String> filters) throws Exception{
		// TODO Auto-generated method stub
		ArrayList<DefaultActionForm> items = new ArrayList<DefaultActionForm>();
		ArrayList<? extends IModel> rows = DaoFactory.create(bean).list(bean, filters);
	    for(IModel e : rows)
		{
	    	IActionForm newForm = form.getClass().newInstance();
	    	BeanUtils.copyProperties(newForm, e);
			items.add((DefaultActionForm)newForm);
		}
		return items;
	}
	
	@Override
	public boolean add(IModel bean, DefaultActionForm actionForm) throws Exception{
		// TODO Auto-generated method stub
		BeanUtils.copyProperties(bean, actionForm);
		return DaoFactory.create(bean).store(BeanUtil.convertBeanPropertiesToUTF(bean));
		
	}
	@Override
	public void edit(IModel bean, DefaultActionForm actionForm, Object primaryKey) throws Exception{
		// TODO Auto-generated method stub
		IModel newBean = DaoFactory.create(bean).selectByPrimaryKey(bean, primaryKey);
		BeanUtils.copyProperties(actionForm, newBean);
	}
	@Override
	public boolean update(IModel bean, DefaultActionForm actionForm) throws Exception{
		// TODO Auto-generated method stub
		BeanUtils.copyProperties(bean, actionForm);
		BeanUtil.convertBeanPropertiesToUTF(bean);
		return DaoFactory.create(bean).update(bean);
		
	}
	@Override
	public boolean delete(IModel bean, DefaultActionForm actionForm) throws Exception{
		// TODO Auto-generated method stub
		BeanUtils.copyProperties(bean, actionForm);
		BeanUtil.convertBeanPropertiesToUTF(bean);
		return DaoFactory.create(bean).delete(bean);
		
	}
	@Override
	public boolean deleteAll(IModel bean, DefaultActionForm actionForm) throws Exception{
		// TODO Auto-generated method stub
		return DaoFactory.create(bean).deleteAllRows(bean);
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
}
