package com.kartag.business.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kartag.business.common.Request;
import com.kartag.business.common.Response;
import com.kartag.common.logging.LocalLogger;

public class FrontController extends Action {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(FrontController.class);
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res)
			throws IOException, ServletException {

		Response response = new Response();
		try {
			if(form == null)
			{
			   Request request = Request.createRequest(req);
			   response = OrderFacad.handleOrder(request);
			   if(response != null)
			   {
			      response.write(res);
			      logger.info("000000001", response.getResponse());
			   }
			}
			else
			{
				response.setStatus(Response.RESPONSE_STATUS_REQUEST_METHOD_ERROR);
				response.createErrorNode(response);
				response.write(res);
				logger.info("000000002");
			}
		}
		catch (Exception ex) {
			response.setStatus(Response.RESPONSE_STATUS_ERROR);
			response.createErrorNode(response);
			response.write(res);
			logger.error("000000003", ex.getMessage(), ex);

		} finally {

		}
		return (mapping.findForward(""));

	}
}
