package com.youngplussoft.modio.jpa.entity;


/*
 * @Project : Real3D
 * @Class Name : MenuVO.java
 * @Description : TB_MENU 테이블과 매핑되는 ValueObject
 * @author Peter Khang
 * @since 2016.8.13
 * @version 1.0
 */

public class LastEmail extends Search {

	long lastTime ;
	String lastHash ;
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastHash() {
		return lastHash;
	}
	public void setLastHash(String lastHash) {
		this.lastHash = lastHash;
	}
}
