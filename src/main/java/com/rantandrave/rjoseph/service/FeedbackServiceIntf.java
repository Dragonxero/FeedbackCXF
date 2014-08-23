package com.rantandrave.rjoseph.service;

import com.rantandrave.rjoseph.model.UserFeedback;

public interface FeedbackServiceIntf {

	public boolean save(UserFeedback feedback);

	public UserFeedback getCustomer(int id);

}