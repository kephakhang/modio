package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OpenTime  implements Serializable {

	String mon ;
	String tue ;
	String wed ;
	String thu ;
	String fri ;
	String sat ;
	String sun ;
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getTue() {
		return tue;
	}
	public void setTue(String tue) {
		this.tue = tue;
	}
	public String getWed() {
		return wed;
	}
	public void setWed(String wed) {
		this.wed = wed;
	}
	public String getThu() {
		return thu;
	}
	public void setThu(String thu) {
		this.thu = thu;
	}
	public String getFri() {
		return fri;
	}
	public void setFri(String fri) {
		this.fri = fri;
	}
	public String getSat() {
		return sat;
	}
	public void setSat(String sat) {
		this.sat = sat;
	}
	public String getSun() {
		return sun;
	}
	public void setSun(String sun) {
		this.sun = sun;
	}
}
