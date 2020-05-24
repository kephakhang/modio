package com.youngplussoft.modio.jpa.controller;

import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.common.Sms;
import com.youngplussoft.modio.jpa.entity.MobileConfirm;
import com.youngplussoft.modio.jpa.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Controller
public class FacebookApiController extends ApiBaseController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MobileConfirmApiController.class);



	@ApiOperation(value = "", notes = "facebook login 결과 redirection uri", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/facebook/login",  produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> login(HttpServletResponse response,
							   @ApiParam(value = "facebook login 정보", required = true) @RequestBody String facebookLogin,
							   SessionStatus sessionStatus

	) {
		
		try {

			LOGGER.debug("facebook login : " + facebookLogin);
			/*
			Document user = userTemplate.findByMobile(mobile) ;

			if( user != null ) {
				throw new Exception(message.getMessage("error.mobile.already.used", mobile));
			}

			String authcode = null ;
			if( (authcode=randomSend(mobile)) != null ) {

				LOGGER.debug("mobile, authcode : " + mobile + "," + authcode) ;
				return new ResponseEntity<Object>("{\"success\":true}", HttpStatus.OK);
			}
			else {
				throw new Exception(message.getMessage("error.sms.send"));
			}
			*/
			return new ResponseEntity<Object>("{\"success\":true}", HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}
}

