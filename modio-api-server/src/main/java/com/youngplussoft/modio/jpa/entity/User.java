package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;
/*
 * @Project : Real3D
 * @Class Name : UserVO.java
 * @Description : TB_MEMBER 테이블과 매핑되는 VO
 * @author Peter Khang
 * @since 2016.8.13
 * @version 1.0
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Entity
@Document(collection="user")
public class User  implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

	@Indexed(unique = true)
	String email = null;

	Integer level = null;
	String name = null;
	String password = null;

	@Indexed(unique = true)
	String mobile = null;

	@Indexed(unique = true)
	String mobileKey = null ;

	Boolean isMobileAuth = null ;
	String birth = null;

	@Indexed(unique = true)
	String accessToken = null ;

	Boolean isEmailAuth = null;
	Boolean isAvail = null;

	@Indexed(unique = true)
	String emailKey = null;
	Date regtime = null;
	Date updtime = null;

	Double point = 0.0 ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	public Date getUpdtime() {
		return updtime;
	}
	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}
	public Boolean getIsEmailAuth() {
		return isEmailAuth;
	}
	public void setIsEmailAuth(Boolean isEmailAuth) {
		this.isEmailAuth = isEmailAuth;
	}
	public Boolean getIsAvail() {
		return isAvail;
	}
	public void setIsAvail(Boolean isAvail) {
		this.isAvail = isAvail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getMobileKey() {
		return mobileKey;
	}
	public void setMobileKey(String mobileKey) {
		this.mobileKey = mobileKey;
	}
	public Boolean getIsMobileAuth() {
		return isMobileAuth;
	}
	public void setIsMobileAuth(Boolean isMobileAuth) {
		this.isMobileAuth = isMobileAuth;
	}

	public Boolean getMobileAuth() {
		return isMobileAuth;
	}

	public void setMobileAuth(Boolean mobileAuth) {
		isMobileAuth = mobileAuth;
	}

	public Boolean getEmailAuth() {
		return isEmailAuth;
	}

	public void setEmailAuth(Boolean emailAuth) {
		isEmailAuth = emailAuth;
	}

	public Boolean getAvail() {
		return isAvail;
	}

	public void setAvail(Boolean avail) {
		isAvail = avail;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
}
