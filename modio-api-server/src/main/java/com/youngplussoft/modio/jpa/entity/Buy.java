package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;
/*
 * @Project :  Modio
 * @Class Name : GoodsVO.java
 * @Description : TB_GOODS 테이블과 매핑되는 VO
 * @author Peter Khang
 * @since 2017.8.10
 * @version 1.0
 */
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection="buy")
public class Buy implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

	private String orderNo ;
	
	private String sort;
	
	private String userId ;

	private String storeId ;

	private List<Product> products ;

	private Double price ;

	private Date  paytime ;

	private Date buytime ;

	private String progress ;  //PAY/ORDER/MAKE/DONE/GET


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
