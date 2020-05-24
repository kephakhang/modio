package com.youngplussoft.modio.jpa.entity;


/*
 * @Project : Real3D
 * @Class Name : MenuVO.java
 * @Description : TB_MENU 테이블과 매핑되는 ValueObject
 * @author Peter Khang
 * @since 2016.8.13
 * @version 1.0
 */

public class Menu extends Search {

	int    id = 0 ;
	String code = "" ;
	String name = "" ;
	String link = "";
	String target = "";
	int order = 0  ;
	int use = 1 ;
	int mobile_use =1 ;
	public int getId() {
		return id;
	}
	public void setId(int sn) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getUse() {
		return use;
	}
	public void setUse(int use) {
		this.use = use;
	}
	public int getMobile_use() {
		return mobile_use;
	}
	public void setMobile_use(int mobile_use) {
		this.mobile_use = mobile_use;
	}
}
