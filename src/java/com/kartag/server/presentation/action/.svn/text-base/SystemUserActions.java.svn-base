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
import com.kartag.common.util.BeanUtil;
import com.kartag.server.model.Country;
import com.kartag.server.model.SystemUser;
import com.kartag.server.model.UserRole;
import com.kartag.server.presentation.form.CountryForm;
import com.kartag.server.presentation.form.SystemUserForm;

public class SystemUserActions extends DefaultDispatchAction {

	public static final String userId_req_param_name = "userId";
	private static LocalLogger logger = LocalLogger
			.getLogger(SystemUserActions.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = "";
		SystemUserForm obj = (SystemUserForm) form;
		SystemUser user = new SystemUser();
		ArrayList<? extends DefaultActionForm> users = obj.listItems(user, obj,
				null);
		obj.setUsers(users);
		// logger.info("3310001");
		target = new String("listing");
		return mapping.findForward(target);
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		target = new String("addUser");
		return mapping.findForward(target);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!validate(mapping, form, request)) {
			return mapping.findForward("addUser");
		}
		String target = "";
		if (form != null) {
			SystemUserForm userForm = (SystemUserForm) form;
			userForm.setUserRule(userForm.getType());
			SystemUser user = new SystemUser();
			BeanUtils.copyProperties(user, userForm);
			UserRole rule = new UserRole();
			rule.setUserName(user.getUserName());
			rule.setUserRole(userForm.getType());
			String[] fields = new String[2];
			fields[0] = "user_name";
			fields[1] = "user_pass";

			String[] values = new String[2];
			values[0] = user.getUserName();
			values[1] = user.getUserPass();
			boolean isUserAddesSuccessed = DaoFactory.create(user)
					.storeHashedField(user, fields, values,
							SystemUser.HASHED_FIELD_NAME);
			boolean isRuleAddesSuccessed = DaoFactory.create(rule).store(rule);

			if (isUserAddesSuccessed && isRuleAddesSuccessed) {
				ArrayList<? extends DefaultActionForm> users = userForm
						.listItems(user, userForm, null);
				userForm.setUsers(users);
				logger.debug("000000005", new String[]{user.getClass().getSimpleName()});
				target = new String("listing");
			} else {
				target = new String("failure");
			}
		}
		return mapping.findForward(target);
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			SystemUserForm userForm = (SystemUserForm) form;
			SystemUser user = new SystemUser();
			BeanUtils.copyProperties(user, userForm);
			BeanUtil.convertBeanPropertiesToUTF(user);
			LinkedHashMap<String, Object> conditions = new LinkedHashMap<String, Object>();
			conditions.put("user_name", user.getUserName());
			conditions.put("user_pass", user.getUserPass());
			SystemUser storedUser = (SystemUser) DaoFactory.create(user)
					.retrieveHashedField(user, SystemUser.HASHED_FIELD_NAME,
							conditions);

			if (storedUser != null) {
				UserRole role = new UserRole();
				UserRole userRole = (UserRole) DaoFactory.create(role)
						.selectByPrimaryKey(role, storedUser.getUserName());
				HttpSession session = request.getSession();
				session.setAttribute("USER", storedUser);
				session.setAttribute("USER_ROLE", userRole.getUserRole());
				target = new String("home");
//				if (SystemUser.ADMIN.equals(userRole.getUserRole())) {
//					// logger.info("3310004", user.getUserName());
//					target = new String("home");
//				} else {
//					// userForm.setAction("manageUser&"
//					// + SystemUserActions.userId_req_param_name + "="
//					// + storedUser.getUserName());
//					// request.setAttribute(SystemUserActions.userId_req_param_name,
//					// storedUser.getUserName());
//					// return manageUser(mapping, userForm, request, response);
//					request.setAttribute(
//							SystemUserActions.userId_req_param_name,
//							storedUser.getUserName());
//					userForm.setAction("manageUser");
//					target = manageUser(userForm, request, false);
//					CountryForm fo = new CountryForm();
//					fo.setCountries(userForm.getChannels());
//					return mapping.findForward(target);
//				}
			}

			else {
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
			form = new SystemUserForm();
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				request.getSession().setAttribute(mapping.getAttribute(), form);
			}
		}
		SystemUserForm obj = (SystemUserForm) form;
		obj.setAction(target);
		SystemUser user = (SystemUser) DaoFactory.create(new SystemUser())
				.selectByPrimaryKey(new SystemUser(), id);
		BeanUtils.copyProperties(obj, user);
		target = new String("edit");

		return mapping.findForward(target);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			SystemUserForm obj = (SystemUserForm) form;
			SystemUser user = new SystemUser();
			LinkedHashMap<String, Object> conditions = new LinkedHashMap<String, Object>();
			conditions.put("user_name", user.getUserName());
			boolean updated = DaoFactory.create(user).editHashedField(user,
					SystemUser.HASHED_FIELD_NAME, conditions);
			if (updated) {
				ArrayList<? extends DefaultActionForm> users = obj.listItems(
						new SystemUser(), obj, null);
				obj.setUsers(users);
				logger.debug("000000006", new String[]{user.getClass().getSimpleName()});
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
			SystemUserForm obj = (SystemUserForm) form;
			SystemUser user = new SystemUser();
			BeanUtils.copyProperties(user, obj);
			boolean deleted = DaoFactory.create(user).delete(user);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> users = obj.listItems(
						new SystemUser(), obj, null);
				obj.setUsers(users);
				target = new String("listing");
				logger.debug("000000007", new String[]{user.getClass().getSimpleName()});
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
			SystemUserForm obj = (SystemUserForm) form;
			SystemUser user = new SystemUser();
			BeanUtils.copyProperties(user, obj);
			boolean deleted = DaoFactory.create(user).deleteAllRows(user);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> users = obj.listItems(
						new SystemUser(), obj, null);
				obj.setUsers(users);
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
		SystemUserForm obj = (SystemUserForm) form;
		ArrayList<? extends DefaultActionForm> users = obj.listItems(
				new SystemUser(), obj, null);
		obj.setUsers(users);
		target = new String("back");
		return mapping.findForward(target);
	}

	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		target = new String("home");
		return mapping.findForward(target);
	}

	public ActionForward manageUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SystemUserForm obj = (SystemUserForm) form;
		String target = manageUser(obj, request, true);
		return mapping.findForward(target);
	}

	private String manageUser(SystemUserForm obj, HttpServletRequest request,
			boolean hasIdParameter) throws Exception {
		String target = "";
		UserRole role = new UserRole();
		SystemUser user = new SystemUser();
		String userId = hasIdParameter ? request
				.getParameter(SystemUserActions.userId_req_param_name)
				: (String) request
						.getAttribute(SystemUserActions.userId_req_param_name);
		UserRole userRole = (UserRole) DaoFactory.create(role)
				.selectByPrimaryKey(role, userId);
		user.setUserName(userRole.getUserName());
		user.setType(userRole.getUserRole());
		if (SystemUser.SUPER_USER.equals(user.getType())) {

			ArrayList<? extends DefaultActionForm> channels = obj
					.listUserChannels(user);
			obj.setChannels(channels);
			target = new String("manageCountries");
		} else if (SystemUser.MALL_USER.equals(user.getType())) {
			ArrayList<? extends DefaultActionForm> channels = obj
					.listUserChannels(user);
			obj.setChannels(channels);
			target = new String("manageMalls");
		} else if (SystemUser.STORE_USER.equals(user.getType())) {
			ArrayList<? extends DefaultActionForm> channels = obj
					.listUserChannels(user);
			obj.setChannels(channels);
			target = new String("manageStores");
		}
		return target;
	}

	public ActionForward forwardAssignCountry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String target = "";
		// CountryForm obj = (CountryForm) form;
		String id = request.getParameter("userId");
		ArrayList<CountryForm> items = new ArrayList<CountryForm>();
		ArrayList<? extends IModel> rows = DaoFactory.create(new Country())
				.list(new Country(), null);
		for (IModel e : rows) {
			CountryForm newForm = new CountryForm();
			BeanUtils.copyProperties(newForm, e);
			newForm.setUserId(id);
			items.add(newForm);
		}
		request.setAttribute("userId", id);
		request.setAttribute("countries", items);
		target = new String("assignCOuntry");
		return mapping.findForward(target);
	}
	
	public ActionForward forwardAssignMalls(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String target = "";
		// CountryForm obj = (CountryForm) form;
//		String id = request.getParameter("userId");
//		ArrayList<MallForm> items = new ArrayList<MallForm>();
//		ArrayList<? extends IModel> rows = DaoFactory.create(new Mall())
//				.list(new Mall(), null);
//		for (IModel e : rows) {
//			MallForm newForm = new MallForm();
//			BeanUtils.copyProperties(newForm, e);
//			newForm.setUserId(id);
//			items.add(newForm);
//		}
//		request.setAttribute("userId", id);
//		request.setAttribute("malls", items);
//		target = new String("assignMall");
		return mapping.findForward(target);
	}

	public ActionForward forwardAssignStores(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String target = "";
		// CountryForm obj = (CountryForm) form;
//		String id = request.getParameter("userId");
//		ArrayList<StoreForm> items = new ArrayList<StoreForm>();
//		ArrayList<? extends IModel> rows = DaoFactory.create(new Store())
//				.list(new Store(), null);
//		for (IModel e : rows) {
//			StoreForm newForm = new StoreForm();
//			BeanUtils.copyProperties(newForm, e);
//			newForm.setUserId(id);
//			items.add(newForm);
//		}
//		request.setAttribute("userId", id);
//		request.setAttribute("stores", items);
//		target = new String("assignStore");
		return mapping.findForward(target);
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		request.getSession(false).removeAttribute("USER");
		request.getSession(false).removeAttribute("USER_ROLE");
		request.getSession(false).invalidate();
		target = new String("logout");
		return (mapping.findForward(target));
	}
}
