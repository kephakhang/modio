package com.youngplussoft.modio.jpa.entity;

/*
 * @Project :  Modio
 * @Class Name : MobileConfirm.java
 * @Description : mobileConfirm 테이블과 매핑되는 Entity
 * @author Peter Khang
 * @since 2017.8.10
 * @version 1.0
 */


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Document(collection="mobileConfirm")
public class MobileConfirm implements Serializable {

	@Id
	private String id;
	private String authcode ;
	private Date   updtime ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public Date getUpdtime() {
		return updtime;
	}

	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}
}
