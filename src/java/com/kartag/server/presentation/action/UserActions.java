package com.kartag.server.presentation.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kartag.common.logging.LocalLogger;
import com.kartag.common.presentation.actions.DefaultDispatchAction;
import com.kartag.common.presentation.forms.DefaultActionForm;
import com.kartag.server.model.User;
import com.kartag.server.presentation.form.UserForm;

public class UserActions extends DefaultDispatchAction {

	private static LocalLogger logger = LocalLogger.getLogger(UserActions.class); 
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = "";
		UserForm actionForm = (UserForm) form;
		User bean = new User();
		// 1# work around because casting an ArrayList 's generic to a
		// superclass does not work
		// UserForm[] CarsArray = (UserForm[])obj.selectItems(User);
		// ArrayList<UserForm> Cars = new
		// ArrayList<UserForm>(Arrays.asList(CarsArray));

		// 2# work around because casting an ArrayList 's generic to a
		// superclass does not work
		ArrayList<? extends DefaultActionForm> Cars = actionForm.listItems(bean, actionForm, null);
		actionForm.setUsers(Cars);
		//logger.info("3310001");
		target = new String("listing");
		return mapping.findForward(target);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			UserForm actionForm = (UserForm) form;
			User bean = new User();
			boolean isAddedSuccessed = actionForm.add(bean, actionForm);
			if (isAddedSuccessed ) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setUsers(list);
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
			form = new UserForm();
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				request.getSession().setAttribute(mapping.getAttribute(), form);
			}
		}
		UserForm actionForm = (UserForm) form;
		User bean = new User();
		//bean.setMallId(Integer.parseInt(id));
		actionForm.edit(bean, actionForm, Integer.parseInt(id));
		target = new String("edit");

		return mapping.findForward(target);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			UserForm actionForm = (UserForm) form;
			User bean = new User();
			boolean updated = actionForm.update(bean, actionForm);
			if (updated) {
				ArrayList<? extends DefaultActionForm> list = actionForm
				   .listItems(bean, actionForm, null);
		        actionForm.setUsers(list);
		        //logger.info("3310002", bean.getClass().getSimpleName());
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
			UserForm actionForm = (UserForm) form;
			User bean = new User();
			boolean deleted = actionForm.delete(bean, actionForm);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> list = actionForm
				   .listItems(bean, actionForm, null);
		        actionForm.setUsers(list);
		        logger.debug("000000006", new String[]{bean.getClass().getSimpleName()});
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
			UserForm actionForm = (UserForm) form;
			User bean = new User();
			boolean deleted = actionForm.deleteAll(bean, actionForm);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> list = actionForm
				   .listItems(bean, actionForm, null);
		        actionForm.setUsers(list);
		        //logger.info("3310002", bean.getClass().getSimpleName());
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
		UserForm actionForm = (UserForm) form;
		ArrayList<? extends DefaultActionForm> list = actionForm
				.listItems(new User(), actionForm, null);
		actionForm.setUsers(list);
		target = new String("back");
		return mapping.findForward(target);
	}
}