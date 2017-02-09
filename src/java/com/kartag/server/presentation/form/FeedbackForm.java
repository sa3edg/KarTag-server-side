package com.kartag.server.presentation.form;

import java.util.ArrayList;

import com.kartag.common.presentation.forms.DefaultActionForm;
import com.kartag.server.model.Feedback;

public class FeedbackForm extends Feedback{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4374713488071069473L;
	
	private ArrayList<? extends DefaultActionForm> feedbacks;

	public void setFeedbacks(ArrayList<? extends DefaultActionForm> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public ArrayList<? extends DefaultActionForm> getFeedbacks() {
		return feedbacks;
	}
	
}

