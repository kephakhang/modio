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
@Document(collection="buyAtStore")
public class BuyAtStore implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
	private String sort;
	
	private String userId ;

	@DBRef
	private Store store ;
	
	private Integer count ;
	
	private Date buytime ;

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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}
}
