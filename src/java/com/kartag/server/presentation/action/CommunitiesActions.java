package com.kartag.server.presentation.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kartag.common.logging.LocalLogger;
import com.kartag.common.presentation.actions.DefaultDispatchAction;
import com.kartag.common.util.DateUtil;
import com.kartag.server.model.Community;
import com.kartag.server.presentation.form.CommunityForm;

public class CommunitiesActions extends DefaultDispatchAction {

	private static LocalLogger logger = LocalLogger
			.getLogger(CommunitiesActions.class);
	public static final String COUNTRY_ID_PARAM = "countryId";

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = "";
		CommunityForm actionForm = (CommunityForm) form;
		Community bean = new Community();
		actionForm.setCommunities(getList(COUNTRY_ID_PARAM, request, actionForm, bean));

		// logger.info("3310001");
		target = new String("listing");
		return mapping.findForward(target);
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		request.setAttribute(COUNTRY_ID_PARAM,
				request.getParameter(COUNTRY_ID_PARAM));
		target = new String("addCmmunity");
		return mapping.findForward(target);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			CommunityForm actionForm = (CommunityForm) form;
			Community bean = new Community();
			actionForm.setEffStartDate(DateUtil.getSimpleDateFromString(actionForm.getStartDate()));
			actionForm.setEffEndDate(DateUtil.getSimpleDateFromString(actionForm.getEndDate()));
			boolean isAddedSuccessed = actionForm.add(bean, actionForm);
			if (isAddedSuccessed) {
				actionForm.setCommunities(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
			form = new CommunityForm();
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				request.getSession().setAttribute(mapping.getAttribute(), form);
			}
		}
		CommunityForm actionForm = (CommunityForm) form;
		Community bean = new Community();
		// bean.setMallId(Integer.parseInt(id));
		actionForm.edit(bean, actionForm, Integer.parseInt(id));
		//request.setAttribute("cityId", String.valueOf(bean.getParentId()));
		target = new String("edit");

		return mapping.findForward(target);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			CommunityForm actionForm = (CommunityForm) form;
			Community bean = new Community();
			actionForm.setEffStartDate(DateUtil.getSimpleDateFromString(actionForm.getStartDate()));
			actionForm.setEffEndDate(DateUtil.getSimpleDateFromString(actionForm.getEndDate()));
			boolean updated = actionForm.update(bean, actionForm);
			if (updated) {
				actionForm.setCommunities(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
			CommunityForm actionForm = (CommunityForm) form;
			Community bean = new Community();
			boolean deleted = actionForm.delete(bean, actionForm);
			if (deleted) {
				actionForm.setCommunities(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
			CommunityForm actionForm = (CommunityForm) form;
			Community bean = new Community();
			boolean deleted = actionForm.deleteAll(bean, actionForm);
			if (deleted) {
				actionForm.setCommunities(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
		CommunityForm actionForm = (CommunityForm) form;
		actionForm.setCommunities(getList(COUNTRY_ID_PARAM, request, actionForm, new Community()));
		target = new String("back");
		return mapping.findForward(target);
	}
}
