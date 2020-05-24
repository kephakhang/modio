package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.springframework.data.mongodb.core.mapping.Document;

@Embeddable
@Document
public class Name  implements Serializable {
	
	String kr ;
	String en ;
	public String getKr() {
		return kr;
	}
	public void setKr(String kr) {
		this.kr = kr;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
}
