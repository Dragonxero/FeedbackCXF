package com.rjoseph.feedback.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "userfeedback")
public class UserFeedback {
	
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(canBeNull = false)
    private String username;
    
    @DatabaseField(canBeNull = false)
    private String review;
    
    @DatabaseField(canBeNull = false)
    private int score;
    
    //Constructor - No-args for ORMLite
    public UserFeedback() {
    }

	public int getId() {
		return id;
	}

    public void setId(int id) {
		this.id = id;
	}

	@NotEmpty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    @NotEmpty
	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
    
    @Range(min=1, max=10)
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return getClass().getName() + " -> id:" + id + " Name: " + username + " Score: " + score + " Feedback: "
				+ review;
	}
}