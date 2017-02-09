package com.kartag.server.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kartag.business.notification.NotificationService;
import com.kartag.common.logging.LocalLogger;
import com.kartag.common.model.IModel;
import com.kartag.common.presentation.actions.DefaultDispatchAction;
import com.kartag.server.model.Country;
import com.kartag.server.model.Handset;
import com.kartag.server.presentation.form.NotificationForm;

public class NotificationActions extends DefaultDispatchAction {

	private static LocalLogger logger = LocalLogger
			.getLogger(NotificationActions.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = "";
		populateTargets(request);
		target = new String("listing");
		return mapping.findForward(target);
	}

	public ActionForward send(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean result = sendNotification((NotificationForm)form);
		populateTargets(request);
		ActionRedirect redirect = new ActionRedirect(mapping.findForward("success"));
		if(result){
			logger.debug("000000008", new String[]{"successfully"});
			redirect.addParameter("result","succed");
		}else{
			logger.debug("000000008", new String[]{"un successfully"});
			redirect.addParameter("result","failed");
		}
		return redirect;
	}

	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = new String("home");
		return mapping.findForward(target);
	}
	
	private void populateTargets(HttpServletRequest request) throws Exception
	{
		Country bean = new Country();
		ArrayList<? extends IModel> countries = bean.getDao().list(bean, null);
		@SuppressWarnings("unchecked")
		List<Country> countriesList  = (List<Country>) countries;
		Country allCountries = new Country();
		allCountries.setId(-1);
		allCountries.setName("all countries");
		countriesList.add(0, (Country)allCountries);
		Country[] countriesArray = new Country[countriesList.size()];
		request.setAttribute("countries", (Country[])countriesList.toArray(countriesArray));
		
		Handset allPlatform = new Handset();
		allPlatform.setId(-1);
		allPlatform.setName("all platforms");
		
		Handset android = new Handset();
		android.setId(0);
		android.setName("android");
		
		Handset iphone = new Handset();
		iphone.setId(1);
		iphone.setName("Iphone");
		List<Handset> handsets = new ArrayList<Handset>();
		handsets.add(allPlatform);
		handsets.add(android);
		handsets.add(iphone);
		
		Handset[] handsetsArray = new Handset[handsets.size()];
		request.setAttribute("handsets", (Handset[])handsets.toArray(handsetsArray));
		
	}
	
	private boolean sendNotification(NotificationForm form)
	{
		try
		{
		if("-1".equals(form.getCountryId())){
			if("-1".equals(form.getTargetHandset())){
				NotificationService.getInstance().notifyAllUsers(form.getNotificationMessage(), true, true);
			}
			else if("0".equals(form.getTargetHandset())){
				NotificationService.getInstance().notifyAllUsers(form.getNotificationMessage(), true, false);
			}
			else if("1".equals(form.getTargetHandset())){
				NotificationService.getInstance().notifyAllUsers(form.getNotificationMessage(), false, true);
			}
		}
		else{
			if("-1".equals(form.getTargetHandset())){
				NotificationService.getInstance().notifyAllUsers(Integer.parseInt(form.getCountryId()), form.getNotificationMessage(), true, true);
			}
			else if("0".equals(form.getTargetHandset())){
				NotificationService.getInstance().notifyAllUsers(Integer.parseInt(form.getCountryId()),form.getNotificationMessage(), true, false);
			}
			else if("1".equals(form.getTargetHandset())){
				NotificationService.getInstance().notifyAllUsers(Integer.parseInt(form.getCountryId()),form.getNotificationMessage(), false, true);
			}
		}
		}
		catch(Exception ex)
		{
			logger.error("000000003", ex.getMessage(), ex);
			return false;
		}
		return true;
	}
}