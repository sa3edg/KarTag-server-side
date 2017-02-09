package com.kartag.server.presentation.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kartag.common.presentation.actions.DefaultDispatchAction;
import com.kartag.server.model.Report;
import com.kartag.server.presentation.form.ReportingForm;
import com.kartag.server.reporting.Reporting;

public class ReportingActions extends DefaultDispatchAction {

	public static final String COUNTRY_ID_PARAM = "countryId";
	public static final String POOL_ID_PARAM = "poolId";
	public static final String COMMUNITY_ID_PARAM = "communityId";
	public ActionForward filter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = "";
		ReportingForm actionForm = (ReportingForm) form;
		Report report = Reporting.report(actionForm);
		actionForm.setAcceptedTrips(report.getAcceptedTrips());
		actionForm.setAndroidUsers(report.getAndroidUsers());
		actionForm.setRejectedTrips(report.getRejectedTrips());
		actionForm.setGiveArideRequested(report.getGiveArideRequested());
		actionForm.setIphoneUsers(report.getIphoneUsers());
		actionForm.setNeedArideRequested(report.getNeedArideRequested());
		actionForm.setRegisteredUsers(report.getRegisteredUsers());
		actionForm.setTripsCount(report.getTripsCount());
		target = new String("listing");
		return mapping.findForward(target);
	}
}
