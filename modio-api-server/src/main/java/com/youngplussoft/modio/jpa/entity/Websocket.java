package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
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
@Document(collection="websocket")
public class Websocket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Indexed(unique = true)
	String devId = null;

	String socketId = null;

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getSocketId() {
		return socketId;
	}

	public void setSocketId(String socketId) {
		this.socketId = socketId;
	}
}
