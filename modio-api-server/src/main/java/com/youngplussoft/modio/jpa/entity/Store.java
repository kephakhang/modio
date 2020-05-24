package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

/*
 * @Project :  Modio
 * @Class Name : Store.java
 * @Description : TB_STORE 테이블과 매핑되는 Entity
 * @author Peter Khang
 * @since 2017.8.10
 * @version 1.0
 */

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;




@Entity
@Document(collection="store")
public class Store implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Embedded
	private Name name ;

	private String chain ;

	@Id
	private String chainParentId ;

	@Embedded
	private Name description ;

	private String address ;

	private String addressDetail ;

	private String besinessNo ;

	@JsonDeserialize(as = Point.class)
	private Point position ;

	private String userId ;
	
	@DBRef
	private StoreCategory category ;

	private String phone ;
	
	@Embedded
	private OpenTime openTime ;
	
	@ElementCollection
	private List<Date> closedDay;

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	Date regtime ;
	Date updtime ;

	List<String> images ;

	@Embedded
	BankBook bankBook ;

	public void setName(Name name) {
		this.name = name;
	}

	public void setDescription(Name description) {
		this.description = description;
	}

	public String getBesinessNo() {
		return besinessNo;
	}

	public void setBesinessNo(String besinessNo) {
		this.besinessNo = besinessNo;
	}

	public BankBook getBankBook() {
		return bankBook;
	}

	public void setBankBook(BankBook bankBook) {
		this.bankBook = bankBook;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public OpenTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(OpenTime openTime) {
		this.openTime = openTime;
	}

	public List<Date> getClosedDay() {
		return closedDay;
	}

	public void setClosedDay(List<Date> closedDay) {
		this.closedDay = closedDay;
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

	public StoreCategory getCategory() {
		return category;
	}

	public void setCategory(StoreCategory category) {
		this.category = category;
	}

	public Name getName() {
		return name;
	}

	public Name getDescription() {
		return description;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
	}

	public String getChainParentId() {
		return chainParentId;
	}

	public void setChainParentId(String chainParentId) {
		this.chainParentId = chainParentId;
	}
}
