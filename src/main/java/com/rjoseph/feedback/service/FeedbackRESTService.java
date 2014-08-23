package com.rjoseph.feedback.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import com.rjoseph.feedback.model.FeedbackResult;
import com.rjoseph.feedback.model.UserFeedback;
import com.rjoseph.feedback.persist.FeedbackDAOIntf;

/*
 * If direct access to an HttpServletResponse necessary, add a parameter of 
 * '@[javax.ws.rs.core.]Context final HttpServletResponse response' to your web service method(s)
 */

@Service
@Path("/")
public class FeedbackRESTService {

	//Just testing JSR-330-style Spring bean injection (@OInject)
	//@Inject String injectedString;
	
	@Inject @Named("feedbackDAOSimple")
	private FeedbackDAOIntf feedbackDAO;
	
	/*
	 * As status code manipulation is required, added ServletResponse INSTEAD of
	 * returning javax.ws.rs.core.Response See explanation(s) @
	 * http://stackoverflow
	 * .com/questions/4687271/jax-rs-how-to-return-json-and-http
	 * -status-code-together
	 */
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Valid UserFeedback feedback) {
		Response.Status responseStatus = Response.Status.BAD_REQUEST;
		
		//Save feedback
		int feedbackId = feedbackDAO.save(feedback);
		
		FeedbackResult feedbackResult = new FeedbackResult(feedbackId);
		
		// set HTTP code to "201 Created"
		responseStatus = Response.Status.CREATED;
		
		// Return the ID of the newly created Feedback entry.
		return Response.status(responseStatus).entity(feedbackResult).build();
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeedback(@PathParam("id") @Min(-999) int id) {		
		Response.Status responseStatus;
		
		UserFeedback userFeedbackRTN = null;
		
		//Retrieve the feedback request feedback by id
		if(feedbackDAO.getFeedback(id) != null && (userFeedbackRTN = feedbackDAO.getFeedback(id)) != null) {
			// set HTTP code to "200 Accepted"
			responseStatus = Response.Status.ACCEPTED;
		} else {
			responseStatus = Response.Status.BAD_REQUEST;
		}
		
		// Update client regarding deletion request
		return Response.status(responseStatus).entity(userFeedbackRTN).build();
	}

	@DELETE
	@Path("/delete/{userName}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeedback(@PathParam("userName") @NotEmpty String userName, @PathParam("id") @Min(-999) int id) {
		Map<String, Object> deleteNotification = new HashMap<>();
		deleteNotification.put("id", id);	//Id of attempted Feedback deletion
		deleteNotification.put("userName", userName);	//User name of attempted Feedback deletion
		
		Response.Status responseStatus;
		
		//Try to delete the feedback
		if(feedbackDAO.getFeedback(id) != null && feedbackDAO.delete(userName, id)) {
			// set HTTP code to "200 Accepted"
			responseStatus = Response.Status.ACCEPTED;
			deleteNotification.put("deleted", true);
		} else {
			responseStatus = Response.Status.BAD_REQUEST;
			deleteNotification.put("deleted", false);
		}
		
		// Update client regarding deletion request
		return Response.status(responseStatus).entity(deleteNotification).build();
	}
}