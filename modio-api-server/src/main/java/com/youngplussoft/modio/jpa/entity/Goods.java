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
@Document(collection="goods")
public class Goods implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
	private String storeId ;

	@DBRef
	private GoodsCategory category ;

	
	@Embedded
	private Name name ;

	@Embedded
	private Name description ;

	@Embedded
	private  List<Price> price ;

	@Embedded
	private  List<Price> options ;


	private String image ;

	private Date regtime ;
	private Date updtime ;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public GoodsCategory getCategory() {
		return category;
	}

	public void setCategory(GoodsCategory category) {
		this.category = category;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
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

	public Name getDescription() {
		return description;
	}

	public void setDescription(Name description) {
		this.description = description;
	}

	public List<Price> getPrice() {
		return price;
	}

	public void setPrice(List<Price> price) {
		this.price = price;
	}

	public List<Price> getOptions() {
		return options;
	}

	public void setOptions(List<Price> options) {
		this.options = options;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
