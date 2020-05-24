package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Document
public class Booking implements Serializable {
	
	String userId;
	int hour  ;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
}
