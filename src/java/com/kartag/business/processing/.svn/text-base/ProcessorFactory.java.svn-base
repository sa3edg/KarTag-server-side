package com.kartag.business.processing;

import java.util.HashMap;
import java.util.Map;

import com.kartag.business.common.OrderTypes;
import com.kartag.business.common.Request;
import com.kartag.business.exception.BadProcessingException;
import com.kartag.business.processing.processors.AcceptTripProcessor;
import com.kartag.business.processing.processors.AddFeedbackProcessor;
import com.kartag.business.processing.processors.AddMessageProcessor;
import com.kartag.business.processing.processors.AddReplyProcessor;
import com.kartag.business.processing.processors.AddTripProcessor;
import com.kartag.business.processing.processors.AddUserProcessor;
import com.kartag.business.processing.processors.ChangePasswordProcessor;
import com.kartag.business.processing.processors.DeleteMessageProcessor;
import com.kartag.business.processing.processors.DeleteTripProcessor;
import com.kartag.business.processing.processors.ForgetPasswordProcessor;
import com.kartag.business.processing.processors.GetCommunitiesProcessor;
import com.kartag.business.processing.processors.GetCommunityTripsProcessor;
import com.kartag.business.processing.processors.GetFilteredTripsProcessor;
import com.kartag.business.processing.processors.GetMessageRepliesProcessor;
import com.kartag.business.processing.processors.GetTripsProcessor;
import com.kartag.business.processing.processors.GetUserMessagesProcessor;
import com.kartag.business.processing.processors.GetUserNewMessagesNotificationsContProcessor;
import com.kartag.business.processing.processors.GetUserNotificationsProcessor;
import com.kartag.business.processing.processors.GetUserTripsProcessor;
import com.kartag.business.processing.processors.CommunityLoginProcessor;
import com.kartag.business.processing.processors.RateTripProcessor;
import com.kartag.business.processing.processors.RejectTripProcessor;
import com.kartag.business.processing.processors.SendJoinRequestToTripProcessor;
import com.kartag.common.exception.BadRuntimeException;
import com.kartag.common.logging.LocalLogger;

public class ProcessorFactory {

	/** Create class logger. */
	private static LocalLogger logger = LocalLogger.getLogger(ProcessorFactory.class);

	/** Map which maps schemaTypes to Processors. */
	private Map<String, Class<?>> orderType2Processor;

	/** Singleton instance. */
	private static ProcessorFactory self = null;
	
	private ProcessorFactory()
	{
		orderType2Processor = new HashMap<String, Class<?>>();
		orderType2Processor.put(OrderTypes.GET_USER_TRIPS_ORDER, GetUserTripsProcessor.class);
		orderType2Processor.put(OrderTypes.GET_TRIPS_ORDER, GetTripsProcessor.class);
		orderType2Processor.put(OrderTypes.ADD_TRIP_ORDER, AddTripProcessor.class);
		orderType2Processor.put(OrderTypes.ACCEPT_TRIP_ORDER, AcceptTripProcessor.class);
		orderType2Processor.put(OrderTypes.REJECT_TRIP_ORDER, RejectTripProcessor.class);
		orderType2Processor.put(OrderTypes.ADD_USER_ORDER, AddUserProcessor.class);
		orderType2Processor.put(OrderTypes.SEND_JOIN_TRIP_ORDER, SendJoinRequestToTripProcessor.class);
		orderType2Processor.put(OrderTypes.ADD_FEEDBACK_ORDER, AddFeedbackProcessor.class);
		orderType2Processor.put(OrderTypes.ADD_MESSAGE_ORDER, AddMessageProcessor.class);
		orderType2Processor.put(OrderTypes.ADD_REPLY_ORDER, AddReplyProcessor.class);
		orderType2Processor.put(OrderTypes.DELETE_MESSAGE_ORDER, DeleteMessageProcessor.class);
		orderType2Processor.put(OrderTypes.GET_USER_MESSAGES_ORDER, GetUserMessagesProcessor.class);
//		orderType2Processor.put(OrderTypes.GET_USER_MESSAGES_UPDATES_ORDER, GetUserMessagesUpdatesProcessor.class);
		orderType2Processor.put(OrderTypes.GET_MESSAGES_NOTIFICATION_COUNT_ORDER, GetUserNewMessagesNotificationsContProcessor.class);
		orderType2Processor.put(OrderTypes.GET_USER_NOTIFICATION_ORDER, GetUserNotificationsProcessor.class);
//		orderType2Processor.put(OrderTypes.GET_USER_NOTIFICATION_UPDATES_ORDER, GetUserNotificationUpdatesProcessor.class);
		orderType2Processor.put(OrderTypes.GET_FILTERED_TRIPS_ORDER, GetFilteredTripsProcessor.class);
		orderType2Processor.put(OrderTypes.RATE_TRIP_ORDER, RateTripProcessor.class);
		orderType2Processor.put(OrderTypes.DELETE_TRIP_ORDER, DeleteTripProcessor.class);
		orderType2Processor.put(OrderTypes.GET_MESSAGE_REPLIES_ORDER, GetMessageRepliesProcessor.class);
		orderType2Processor.put(OrderTypes.GET_COMMUNITY_TRIPS, GetCommunityTripsProcessor.class);
		orderType2Processor.put(OrderTypes.LOGIN_ORDER, CommunityLoginProcessor.class);
		orderType2Processor.put(OrderTypes.FORGET_PASSWORD, ForgetPasswordProcessor.class);
		orderType2Processor.put(OrderTypes.CHANGE_PASSWORD, ChangePasswordProcessor.class);
		orderType2Processor.put(OrderTypes.GET_COMMUNITIES, GetCommunitiesProcessor.class);
	}

	/**
	 * Singleton getter.
	 * 
	 * @return the singleton instance.
	 */
	public static synchronized ProcessorFactory getInstance() {
		if (self == null) {
			ProcessorFactory temp = new ProcessorFactory();
			self = temp;
		}
		return self;
	}

	/**
	 * Shortcut for {@link #createProcessor(Request, Element)}.
	 * 
	 * @param request
	 *            the request
	 * @return the created processor
	 */
	public static IProcessor create(Request request) {
		return getInstance().createProcessor(request);
	}

	/**
	 * The factory method to create a processor depending on the schemaType of
	 * the request.
	 * 
	 * @param request
	 *            the request
	 * @return the created processor
	 * @throws BadArgumentException
	 *             on unknown schema type
	 */
	public IProcessor createProcessor(Request request) {
		// get the schema type of the request
		String orderType = request.getOrderType();
		assert orderType != null;

		// create processor
		IProcessor processor = createProcessor(orderType);

		// if failed, signal error
		if (processor == null) {
			throw new BadProcessingException();
		}

		// provide data
		processor.setRequest(request);

		return processor;
	}

	protected IProcessor createProcessor(String orderType) {
		IProcessor ret = null;

		Class<?> clazz = (Class<?>) orderType2Processor.get(orderType);
		if (clazz != null) {
			try {
				ret = (IProcessor) clazz.newInstance();
			} catch (Exception e) {
				logger.error("000000003", e.getMessage(), e);
				throw new BadRuntimeException(e);
			}
		}

		return ret;
	}

}
