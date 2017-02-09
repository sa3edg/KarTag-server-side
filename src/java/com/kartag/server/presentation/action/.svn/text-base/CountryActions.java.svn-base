package com.kartag.server.presentation.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kartag.common.dao.DaoFactory;
import com.kartag.common.logging.LocalLogger;
import com.kartag.common.model.IModel;
import com.kartag.common.presentation.actions.DefaultDispatchAction;
import com.kartag.common.presentation.forms.DefaultActionForm;
import com.kartag.server.model.Country;
import com.kartag.server.model.SystemUser;
import com.kartag.server.model.UserChannel;
import com.kartag.server.presentation.form.CountryForm;

public class CountryActions extends DefaultDispatchAction {

	private static LocalLogger logger = LocalLogger
			.getLogger(CountryActions.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		CountryForm actionForm = (CountryForm) form;
		Country bean = new Country();

		ArrayList<? extends DefaultActionForm> countries = actionForm.listItems(
				bean, actionForm, null);
		
		HttpSession session = request.getSession();

		String userRole = (String) session.getAttribute("USER_ROLE");
		if (SystemUser.SUPER_USER.equals(userRole)) {
			SystemUser user = (SystemUser) session.getAttribute("USER");
			LinkedHashMap<String, String> filters = new LinkedHashMap<String, String>();
			filters.put(SystemUserActions.userId_req_param_name,
					user.getUserName());

			UserChannel userChannel = new UserChannel();
			ArrayList<? extends IModel> channels = DaoFactory.create(
					userChannel).list(userChannel, filters);

			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (IModel e : channels) {
				UserChannel newObj = userChannel.getClass().newInstance();
				BeanUtils.copyProperties(newObj, e);
				ids.add(newObj.getChannelId());
			}
			ArrayList<DefaultActionForm> filteredList = new ArrayList<DefaultActionForm>();
			for (DefaultActionForm e : countries) {
				CountryForm newObj = new CountryForm();
				BeanUtils.copyProperties(newObj, e);
				if(ids.contains(newObj.getId()))
				{
					filteredList.add((DefaultActionForm)newObj);
				}
			}
			actionForm.setCountries(filteredList);
		}
		else
		{
		   actionForm.setCountries(countries);
		}
		target = new String("listing");
		return mapping.findForward(target);
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		target = new String("addCountry");
		return mapping.findForward(target);
	}

	public ActionForward assign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		CountryForm country = (CountryForm) form;
		UserChannel userChannel = new UserChannel();
		userChannel.setUserName(country.getUserId());
		userChannel.setChannelId(country.getId());
		Country co = (Country) DaoFactory.create(new Country())
				.selectByPrimaryKey(new Country(), country.getId());
		userChannel.setChannelName(co.getName());
		boolean assigned = DaoFactory.create(userChannel).store(userChannel);
		if (assigned) {
			SystemUser user = new SystemUser();
			user.setUserName(country.getUserId());
			ArrayList<? extends DefaultActionForm> channels = country
					.listChannels(user, new Country(), country);
			country.setCountries(channels);
			target = new String("listing");
		} else {
			target = new String("failure");
		}
		return mapping.findForward(target);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			CountryForm actionForm = (CountryForm) form;
			Country bean = new Country();
			boolean isAddedSuccessed = actionForm.add(bean, actionForm);
			if (isAddedSuccessed) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setCountries(list);
				logger.debug("000000005", new String[]{bean.getClass().getSimpleName()});
				target = new String("listing");
			} else {
				target = new String("failure");
			}
		}
		return mapping.findForward(target);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String target = "edit";
		if (form == null) {
			form = new CountryForm();
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				request.getSession().setAttribute(mapping.getAttribute(), form);
			}
		}
		CountryForm actionForm = (CountryForm) form;
		Country bean = new Country();
		actionForm.edit(bean, actionForm, Integer.parseInt(id));
		target = new String("edit");

		return mapping.findForward(target);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			CountryForm actionForm = (CountryForm) form;
			Country bean = new Country();
			boolean updated = actionForm.update(bean, actionForm);
			if (updated) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setCountries(list);
				logger.debug("000000006", new String[]{bean.getClass().getSimpleName()});
				target = new String("listing");
			} else {
				target = new String("failure");
			}
		}
		return mapping.findForward(target);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			CountryForm actionForm = (CountryForm) form;
			Country bean = new Country();
			boolean deleted = actionForm.delete(bean, actionForm);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setCountries(list);
				logger.debug("000000007", new String[]{bean.getClass().getSimpleName()});
				target = new String("listing");
			} else {
				target = new String("failure");
			}
		}
		return mapping.findForward(target);
	}

	public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			CountryForm actionForm = (CountryForm) form;
			Country bean = new Country();
			boolean deleted = actionForm.deleteAll(bean, actionForm);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setCountries(list);
				// logger.info("3310002", bean.getClass().getSimpleName());
				target = new String("listing");
			} else {
				target = new String("failure");
			}
		}
		return mapping.findForward(target);
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		CountryForm actionForm = (CountryForm) form;
		ArrayList<? extends DefaultActionForm> list = actionForm.listItems(
				new Country(), actionForm, null);
		actionForm.setCountries(list);
		target = new String("back");
		return mapping.findForward(target);
	}

	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		CountryForm country = (CountryForm) form;
		SystemUser user = new SystemUser();
		user.setUserName(country.getUserId());
		// SystemUserForm userForm = new SystemUserForm();
		ArrayList<? extends DefaultActionForm> channels = country.listChannels(
				user, new Country(), country);
		country.setCountries(channels);
		target = new String("listing");
		return mapping.findForward(target);
	}
}