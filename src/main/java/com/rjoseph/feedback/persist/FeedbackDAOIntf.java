package com.rjoseph.feedback.persist;

import com.rjoseph.feedback.model.UserFeedback;

public interface FeedbackDAOIntf {

	public Integer save(UserFeedback feedback);
	
	public boolean delete(String userName, int id);

	public UserFeedback getFeedback(int id);

}