package com.kartag.business.processing.processors;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.TripDao;

public class RejectTripProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(RejectTripProcessor.class);
	private final static String USER_ID_PARAM = "userId";
	private final static String TRIP_ID_PARAM = "tripId";
	private final static String FROM_UID_PARAM = "fromUid";

	@Override
	public Response process() {

		Response response = new Response();
		try {
			TripDao dao = new TripDao();
			String result = dao.rejectJoinRequest(
					request.getParameters().get(USER_ID_PARAM), request
							.getParameters().get(TRIP_ID_PARAM),
							request.getParameters().get(FROM_UID_PARAM));
			if (result == null) {
				response.setStatus(Response.RESPONSE_STATUS_ERROR);
				response.createErrorNode(response);
			} else {
				response.setStatus(Response.RESPONSE_STATUS_SUCCESS);
				response.setResponse(result);
				response.mergeResponse(response);
			}
			return response;

		} catch (Exception ex) {
			response.setStatus(Response.RESPONSE_STATUS_ERROR);
			response.createErrorNode(response);
			logger.error("000000003", ex.getMessage(), ex);
		}
		return response;
	}
}