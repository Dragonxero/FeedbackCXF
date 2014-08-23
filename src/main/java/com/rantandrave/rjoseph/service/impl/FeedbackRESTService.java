package com.rantandrave.rjoseph.service.impl;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.rantandrave.rjoseph.model.FeedbackResult;
import com.rantandrave.rjoseph.model.UserFeedback;

/*
 * If direct access to an HttpServletResponse necessary, add a parameter of 
 * '@[javax.ws.rs.core.]Context final HttpServletResponse response' to your web service method(s)
 */

@Service
@Path("/")
public class FeedbackRESTService {

	@Inject String injectedString;
	
	/*
	 * As status code manipulation is required, added ServletResponse INSTEAD of
	 * returning javax.ws.rs.core.Response See explanation(s) @
	 * http://stackoverflow
	 * .com/questions/4687271/jax-rs-how-to-return-json-and-http
	 * -status-code-together
	 */
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Valid UserFeedback feedback) {
		Response.Status responseStatus = Response.Status.BAD_REQUEST;
		
		FeedbackResult feedbackResult = new FeedbackResult(10);
		
		// set HTTP code to "201 Created"
		responseStatus = Response.Status.CREATED;
		
		// Return the ID of the newly created Feedback entry.
		return Response.status(responseStatus).entity(feedbackResult).build();
	}

	@GET
	@Path("/getbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id") @Min(-999) int id) {
		Response.Status responseStatus = Response.Status.BAD_REQUEST;
		
		UserFeedback userFeedback = new UserFeedback();
		
		userFeedback.setReview(this.injectedString);
		
		// set HTTP code to "201 Created"
		responseStatus = Response.Status.ACCEPTED;
		
		// Return the requested Feedback entry.
		return Response.status(responseStatus).entity(userFeedback).build();
	}
}