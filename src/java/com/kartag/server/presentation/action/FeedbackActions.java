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
import com.kartag.server.model.Feedback;
import com.kartag.server.model.SystemUser;
import com.kartag.server.model.UserChannel;
import com.kartag.server.presentation.form.FeedbackForm;

public class FeedbackActions extends DefaultDispatchAction {

	private static LocalLogger logger = LocalLogger
			.getLogger(FeedbackActions.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		FeedbackForm actionForm = (FeedbackForm) form;
		Feedback bean = new Feedback();

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
				FeedbackForm newObj = new FeedbackForm();
				BeanUtils.copyProperties(newObj, e);
				if(ids.contains(newObj.getId()))
				{
					filteredList.add((DefaultActionForm)newObj);
				}
			}
			actionForm.setFeedbacks(filteredList);
		}
		else
		{
		   actionForm.setFeedbacks(countries);
		}
		target = new String("listing");
		return mapping.findForward(target);
	}

	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			FeedbackForm actionForm = (FeedbackForm) form;
			Feedback bean = new Feedback();
			boolean deleted = actionForm.delete(bean, actionForm);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setFeedbacks(list);
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
			FeedbackForm actionForm = (FeedbackForm) form;
			Feedback bean = new Feedback();
			boolean deleted = actionForm.deleteAll(bean, actionForm);
			if (deleted) {
				ArrayList<? extends DefaultActionForm> list = actionForm
						.listItems(bean, actionForm, null);
				actionForm.setFeedbacks(list);
				// logger.info("3310002", bean.getClass().getSimpleName());
				target = new String("listing");
			} else {
				target = new String("failure");
			}
		}
		return mapping.findForward(target);
	}
}