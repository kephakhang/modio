package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.springframework.data.mongodb.core.mapping.Document;



@Embeddable
@Document
public class EmbedImage implements Serializable{

	boolean share ;
	String fname ;
	String uuid ;
	String ext ;
	String yyyymm ;
	
	public boolean isShare() {
		return share;
	}
	public void setShare(boolean share) {
		this.share = share;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}
}
