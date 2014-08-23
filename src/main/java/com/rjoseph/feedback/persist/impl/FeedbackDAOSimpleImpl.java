package com.rjoseph.feedback.persist.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.rjoseph.feedback.model.UserFeedback;
import com.rjoseph.feedback.persist.FeedbackDAOIntf;

@Named("feedbackDAOSimple")
public class FeedbackDAOSimpleImpl implements FeedbackDAOIntf {
	
	Map<Integer, UserFeedback> feedbackMap = new HashMap<>();
	
	private Integer id = 0;

	@Override
	public UserFeedback save(UserFeedback feedback) {
		feedback.setId(++id);
		feedbackMap.put(feedback.getId(), feedback);
		
		//Return the update/persisted UserFeedback instance
		return feedback;
	}

	@Override
	public boolean delete(String userName, int id) {
		UserFeedback uFeed = feedbackMap.get(id);
		boolean isCanDelete = false;
		if(uFeed != null) {
			isCanDelete = uFeed.getUsername().equals(userName);
		}
		
		if(isCanDelete) {
			feedbackMap.remove(id);
		}
		
		return isCanDelete;
	}

	@Override
	public UserFeedback getFeedback(int id) {
		return feedbackMap.get(id);
	}

}
