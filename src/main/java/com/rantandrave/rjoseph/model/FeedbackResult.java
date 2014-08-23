package com.rantandrave.rjoseph.model;

import javax.validation.constraints.DecimalMin;

public class FeedbackResult {

	private int feedbackId = -1;

	// Constructor
	public FeedbackResult(int id) {
		this.feedbackId = id;
	}

	@DecimalMin(value = "0")
	public int getFeedbackId() {
		return feedbackId;
	}
}
