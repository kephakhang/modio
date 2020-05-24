package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/*
 * @Project : Real3D
 * @Class Name : UserVO.java
 * @Description : TB_MEMBER 테이블과 매핑되는 VO
 * @author Peter Khang
 * @since 2016.8.13
 * @version 1.0
 */


@Entity
public class Login implements Serializable {

	String email = null;
	String password = null;

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
}
