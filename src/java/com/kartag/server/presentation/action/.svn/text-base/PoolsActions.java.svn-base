package com.kartag.server.presentation.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kartag.common.logging.LocalLogger;
import com.kartag.common.presentation.actions.DefaultDispatchAction;
import com.kartag.server.model.Pool;
import com.kartag.server.presentation.form.PoolForm;

public class PoolsActions extends DefaultDispatchAction {

	private static LocalLogger logger = LocalLogger
			.getLogger(PoolsActions.class);
	public static final String COUNTRY_ID_PARAM = "countryId";

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = "";
		PoolForm actionForm = (PoolForm) form;
		Pool bean = new Pool();
		actionForm.setPools(getList(COUNTRY_ID_PARAM, request, actionForm, bean));

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
		target = new String("addPool");
		return mapping.findForward(target);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "";
		if (form != null) {
			PoolForm actionForm = (PoolForm) form;
			Pool bean = new Pool();
			boolean isAddedSuccessed = actionForm.add(bean, actionForm);
			if (isAddedSuccessed) {
				actionForm.setPools(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
			form = new PoolForm();
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				request.getSession().setAttribute(mapping.getAttribute(), form);
			}
		}
		PoolForm actionForm = (PoolForm) form;
		Pool bean = new Pool();
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
			PoolForm actionForm = (PoolForm) form;
			Pool bean = new Pool();
			boolean updated = actionForm.update(bean, actionForm);
			if (updated) {
				actionForm.setPools(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
			PoolForm actionForm = (PoolForm) form;
			Pool bean = new Pool();
			boolean deleted = actionForm.delete(bean, actionForm);
			if (deleted) {
				actionForm.setPools(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
			PoolForm actionForm = (PoolForm) form;
			Pool bean = new Pool();
			boolean deleted = actionForm.deleteAll(bean, actionForm);
			if (deleted) {
				actionForm.setPools(getList(COUNTRY_ID_PARAM, request, actionForm, bean));
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
		PoolForm actionForm = (PoolForm) form;
		actionForm.setPools(getList(COUNTRY_ID_PARAM, request, actionForm, new Pool()));
		target = new String("back");
		return mapping.findForward(target);
	}
}
