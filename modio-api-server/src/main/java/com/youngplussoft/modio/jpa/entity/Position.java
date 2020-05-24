package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Position implements Serializable  {

	double lat ;
	double lon ;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
}
