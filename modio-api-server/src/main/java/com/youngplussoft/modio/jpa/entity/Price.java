package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Document
public class Price implements Serializable {
	
	String name ;
	double value ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
