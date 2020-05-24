
package com.youngplussoft.modio.common;

import java.util.List;

public class Login {

	String id ;
	String userId ;
	String name ;
	String password ;
	int level=0 ;
	boolean idSave ;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public boolean isIdSave() {
		return idSave;
	}
	public void setIdSave(boolean idSave) {
		this.idSave = idSave;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
