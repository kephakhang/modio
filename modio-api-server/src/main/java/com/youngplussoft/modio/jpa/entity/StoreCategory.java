package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Document(collection="storeCategory")
public class StoreCategory  implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
	@Embedded
	Name name ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
}
