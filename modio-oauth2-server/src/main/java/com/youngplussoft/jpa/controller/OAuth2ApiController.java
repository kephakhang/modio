package com.youngplussoft.jpa.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletResponse;

import com.youngplussoft.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.youngplussoft.jpa.repository.TokenRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.jpa.entity.Token ;
import com.youngplussoft.jpa.entity.Error ;


@Controller
public class OAuth2ApiController extends BaseController{


	final String basePath = "/oauth2";
	
	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	Message message ;

	@ApiOperation(value = "", notes = "Check `oauth2 client`  입력은  client & secret 정보 값이다. 리턴 값은 Token class  값을 반환해 준다 .  ", response = Token.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Token.class),
							@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
							@ApiResponse(code = 500, message = "Internal Error", response = Error.class) })
	@RequestMapping(value = basePath + "/token", produces = { "application/json", "text/html" }, method = RequestMethod.GET)
	ResponseEntity<Object> getAccessToken(HttpServletResponse response,
			@ApiParam(value = "client app id", required = true) @RequestParam(value = "client", required = true) String client,
			@ApiParam(value = "client app secret", required = true) @RequestParam(value = "secret", required = true) String secret,
			SessionStatus sessionStatus

	) {

		try {
			
			Token token = tokenRepository.findByClientAndClientSecret(client, secret) ;
			
			if( token == null ) {
				throw new Exception(message.getMessage("error.oauth2.unknown.client")) ;
			}
			
			token.setId("");
			
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	
	@ApiOperation(value = "", notes = "Check `oauth2 token`  입력은  token 정보 값이다. 리턴 값은 Token class 값을 반환해 준다 .  ", response = Token.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Token.class),
							@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
							@ApiResponse(code = 500, message = "Internal Error", response = Error.class) })
	@RequestMapping(value = basePath + "/token/check", produces = { "application/json", "text/html" }, method = RequestMethod.GET)
	ResponseEntity<Object> checkAccessToken(HttpServletResponse response,
			@ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String accessToken,
			SessionStatus sessionStatus

	) {

		try {
			
			Token token = tokenRepository.findByToken(accessToken) ;
			
			if( token == null ) {
				throw new Exception(message.getMessage("error.oauth2.unknown.client")) ;
			}
			
			token.setId("");
			
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@ApiOperation(value = "", notes = "Refresh `oauth2 token key`  입력은  client, secret & refreshToken 정보 값이다. 리턴 값은 AccessToken  값을 반환해 준다 .  ", response = Token.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Token.class),
							@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
							@ApiResponse(code = 500, message = "Internal Error", response = Error.class) })
	@RequestMapping(value = basePath + "/refresh", produces = { "application/json", "text/html" }, method = RequestMethod.GET)
	ResponseEntity<Object> refreshAccessToken(HttpServletResponse response,
			@ApiParam(value = "client app id", required = true) @RequestParam(value = "client", required = true) String client,
			@ApiParam(value = "client app secret", required = true) @RequestParam(value = "secret", required = true) String secret,
			@ApiParam(value = "refresh token", required = true) @RequestParam(value = "refreshToken", required = true) String refreshToken,
			SessionStatus sessionStatus

	) {

		try {
			
			Token token = tokenRepository.findByClientAndClientSecret(client, secret) ;
			
			if( token == null ) {
				throw new Exception(message.getMessage("error.oauth2.unknown.client")) ;
			}
			
			Calendar c = new GregorianCalendar();
			c.set(Calendar.DATE, 0);
			
			if( ! refreshToken.equals(token.getRefreshToken()) ||
				token.getRefreshExpireDate().getTime() < c.getTime().getTime() 	) {
				throw new Exception(message.getMessage("error.oauth2.refresh.token")) ;
			}
			
			
			
			
			token.setToken(KeyGenerator.generateKey());
			token.setRefreshToken(KeyGenerator.generateKey());
				
				
			c.set(Calendar.DATE, 30);
			Date d=c.getTime();
			token.setExpireDate(d);
			
			c.set(Calendar.DATE, 365);
			d = c.getTime();
			token.setRefreshExpireDate(d);
			
			tokenRepository.save(token) ;	
			
			token.setId("");
			
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}
