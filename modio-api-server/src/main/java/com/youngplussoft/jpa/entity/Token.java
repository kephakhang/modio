package com.youngplussoft.jpa.entity;

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
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection="token")
public class Token {
	
	@Id
	@Field("_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	
	private String client ;
	private String clientSecret ;
	private String token ;
	private String refreshToken ;
	private Date expireDate ;
	private Date refreshExpireDate ;
	private String scope ;
	private String tokenType ;
	private String apiHostUrl ;
	private String baseUri ;

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Token(String id, String client, String clientSecret, String token, String refreshToken,
			Date expireDate) {
		super();
		this.id = id;
		this.client = client;
		this.clientSecret = clientSecret;
		this.token = token;
		this.refreshToken = refreshToken;
		this.expireDate = expireDate;
	}
	public Date getRefreshExpireDate() {
		return refreshExpireDate;
	}
	public void setRefreshExpireDate(Date refreshExpireDate) {
		this.refreshExpireDate = refreshExpireDate;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getApiHostUrl() {
		return apiHostUrl;
	}
	public void setApiHostUrl(String apiHostUrl) {
		this.apiHostUrl = apiHostUrl;
	}
}
